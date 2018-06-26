package net.metro.iot.monitoring.iotMonitoring.dao;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

import net.metro.iot.monitoring.iotMonitoring.domain.SensorConfig;
import net.metro.iot.monitoring.iotMonitoring.persistence.config.CassandraPersistenceService;

@Component
public class SensorConfigManager {

    @Autowired
    private CassandraPersistenceService persistenceService;

    private Mapper<SensorConfig> mapper;
    private SensorConfigAccessor accessor;

    @PostConstruct
    public void initialize() {
        MappingManager mappingManager = persistenceService.getMappingManager();
        mapper = mappingManager.mapper(SensorConfig.class);
        accessor = mappingManager.createAccessor(SensorConfigAccessor.class);
    }

    public Mapper<SensorConfig> getMapper() {
        return mapper;
    }

    public SensorConfigAccessor getAccessor() {
        return accessor;
    }
}
