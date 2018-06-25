package net.metro.iot.monitoring.iotMonitoring.persistence.config;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.datastax.driver.core.*;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.exceptions.NoHostAvailableException;
import com.datastax.driver.core.exceptions.QueryExecutionException;
import com.datastax.driver.core.exceptions.QueryValidationException;
import com.datastax.driver.mapping.MappingManager;

@Service
public class CassandraPersistenceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CassandraPersistenceService.class);

    private static final String CASSANDRA_EXCEPTION = "Cassandra exception {}";

    @Value("${cassandra.host}")
    private String cassandraHost;
    @Value("${cassandra.user}")
    private String cassandraUser;
    @Value("${cassandra.pass}")
    private String cassandraPass;
    @Value("${cassandra.auth.required}")
    private String cassandraAuthRequired;
    @Value("${cassandra.port.native}")
    private int cassandraPort;
    @Value("classpath:dbSchema.xml")
    private Resource dbSchemaResource;
    @Value("${cassandra.replication}")
    private String replication;
    @Value("${strategy.class}")
    private String strategyClass;
    @Value("${strategy.option}")
    private String strategyOption;
    @Value("${cassandra.write.consistency.level}")
    private String cassandraWriteConsistency;
    @Value("${cassandra.read.consistency.level}")
    private String cassandraReadConsistency;

    private Session session;
    private Document document;
    private ConsistencyLevel WRITE_CONSISTENCY_LEVEL;
    private ConsistencyLevel READ_CONSISTENCY_LEVEL;
    private MappingManager mappingManager; //used for JPA-like annotations

    public static final String KEYSPACE = "pemp";

    @PostConstruct
    public void connectDataBase() {
        try {
            WRITE_CONSISTENCY_LEVEL = ConsistencyLevel.valueOf(cassandraWriteConsistency);
            READ_CONSISTENCY_LEVEL = ConsistencyLevel.valueOf(cassandraReadConsistency);

            //set the default cluster level consistency; this is the consistency that will be used for both read and writes 
            //this consistency level can be overridden on Query level
            QueryOptions queryOptions = new QueryOptions();
            queryOptions.setConsistencyLevel(READ_CONSISTENCY_LEVEL);

            Builder clusterBuilder = Cluster.builder().addContactPoint(cassandraHost).withPort(cassandraPort).withQueryOptions(queryOptions);
            if (Boolean.valueOf(cassandraAuthRequired)) {
                clusterBuilder.withCredentials(cassandraUser, cassandraPass);
            }
            Cluster cluster = clusterBuilder.build();
            cluster.getConfiguration();
            session = cluster.connect();

            mappingManager = new MappingManager(session);

            initDatabase();

        } catch (Exception e) {
            LOGGER.error("Could not connect to the database cluster {}", e.getMessage());
        }

    }

    private void initDatabase() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(dbSchemaResource.getInputStream());
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOGGER.error("Could not initialize database", e);
            return;
        }

        DbSchemaTags[] xmlTagsToBeProcessed = DbSchemaTags.values();
        for (DbSchemaTags tag : xmlTagsToBeProcessed) {
            NodeList statementListBasedOnTagName = document.getElementsByTagName(tag.getTagName());
            for (int i = 0; i < statementListBasedOnTagName.getLength(); i++) {
                if (!statementListBasedOnTagName.item(i).getTextContent().trim().isEmpty()) {
                    String statementToBeExecuted = statementListBasedOnTagName.item(i).getTextContent().replaceAll("\\s+", " ");
                    SimpleStatement fileStatement = new SimpleStatement(statementToBeExecuted);
                    try {
                        execute(fileStatement);
                    } catch (Exception e) {
                        LOGGER.error("Could not run query " + statementToBeExecuted, e);
                    }
                }
            }
        }
    }

    public void execute(Statement statement) {
        try {
            session.execute(statement);
        } catch (NoHostAvailableException e) {
            LOGGER.error(CASSANDRA_EXCEPTION, e);
            throw e;
        } catch (QueryExecutionException | QueryValidationException e) {
            LOGGER.error(CASSANDRA_EXCEPTION, e);
            throw e;
        }
    }

    @PreDestroy
    public void disconnectDatabase() {
        try {
            session.close();
        } catch (Exception e) {
            LOGGER.error("Could not close database exception {}", e.getMessage());
        }
    }

    public boolean checkConnection() {
        return !session.isClosed();
    }

}
