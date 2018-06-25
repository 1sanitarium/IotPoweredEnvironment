package net.metro.iot.monitoring.iotMonitoring.dao;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;

import net.metro.iot.monitoring.iotMonitoring.domain.SensorConfig;

@Accessor
public interface SensorConfigAccessor {

    @Query("SELECT * FROM pemp.sensor_config")
    Result<SensorConfig> getAll();

    @Query("SELECT * FROM pemp.sensor_config where id=?")
    Result<SensorConfig> getById(String Id);
}
