package net.metro.iot.monitoring.iotMonitoring.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.datastax.driver.mapping.Mapper;

import net.metro.iot.monitoring.iotMonitoring.domain.SensorConfig;

@Repository
public class SensorConfigDao {

    @Autowired
    private SensorConfigManager manager;

    private Mapper<SensorConfig> mapper;

    private SensorConfigAccessor accessor;

    @PostConstruct
    public void initialize() {
        mapper = manager.getMapper();
        accessor = manager.getAccessor();
    }

    public void save(SensorConfig sensorConfig) {
        mapper.save(sensorConfig);
    }

    public SensorConfig getByPrimaryKey(String id) throws Exception {
        List<SensorConfig> sensors = accessor.getById(id).all();
        if (sensors.isEmpty()) {
            throw new Exception("Sensor with id: " + id + " doesn't exist");
        }
        return sensors.get(0);
    }

    public List<SensorConfig> getAll() {
        return accessor.getAll().all();
    }

}
