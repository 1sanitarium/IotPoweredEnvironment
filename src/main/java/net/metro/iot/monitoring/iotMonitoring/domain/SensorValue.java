package net.metro.iot.monitoring.iotMonitoring.domain;

import java.util.Date;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Table;

@Table(name = "sensor_values", readConsistency = "QUORUM", writeConsistency = "QUORUM")
public class SensorValue {

    @Column(name = "id")
    private String id;

    @Column(name = "value")
    private String value;

    @Column(name = "time")
    private Date time;

    @Column(name = "unit")
    private String unit;

    @Column(name = "type")
    private String type;

    public SensorValue(String id, String value, Date time, String unit, String type) {
        this.id = id;
        this.value = value;
        this.time = time;
        this.unit = unit;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SensorValue [id=" + id + ", value=" + value + ", time=" + time + ", unit=" + unit + ", type=" + type + "]";
    }

}
