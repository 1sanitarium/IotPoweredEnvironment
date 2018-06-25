package net.metro.iot.monitoring.iotMonitoring.dto;

public class SensorConfigDto {

    private String id;

    private String sensor;

    private String location;

    public SensorConfigDto() {
    }

    public SensorConfigDto(String id, String sensor, String location) {
        this.id = id;
        this.sensor = sensor;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getSensor() {
        return sensor;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "SensorConfigDto [id=" + id + ", sensor=" + sensor + ", location=" + location + "]";
    }
}
