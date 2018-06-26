package net.metro.iot.monitoring.iotMonitoring.dao;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datastax.driver.mapping.Mapper;

import net.metro.iot.monitoring.iotMonitoring.domain.SensorValue;
import net.metro.iot.monitoring.iotMonitoring.persistence.config.CassandraPersistenceService;

@Repository
public class SensorValueDao {

    private Mapper<SensorValue> mapper;

    @Autowired
    private CassandraPersistenceService persistenceService;

    @PostConstruct
    public void initialize() {
        mapper = persistenceService.getMappingManager().mapper(SensorValue.class);
    }

    public void save(SensorValue sensorValue) {
        mapper.save(sensorValue);
    }

}
