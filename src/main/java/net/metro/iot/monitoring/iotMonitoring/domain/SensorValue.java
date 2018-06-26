package net.metro.iot.monitoring.iotMonitoring.domain;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Table;

import net.metro.iot.monitoring.iotMonitoring.dto.SensorConfigDto;

@Table(name = "sensor_config", readConsistency = "QUORUM", writeConsistency = "QUORUM")
public class SensorValue {

    @Column(name = "id")
    private String id;

    @Column(name = "sensor")
    private String sensor;

    @Column(name = "location")
    private String location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public SensorConfigDto toDto() {
        return new SensorConfigDto(this.id, this.sensor, this.location);
    }

    @Override
    public String toString() {
        return "SensorConfig [id=" + id + ", sensor=" + sensor + ", location=" + location + "]";
    }

}
