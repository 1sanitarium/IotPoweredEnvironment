package net.metro.iot.monitoring.iotMonitoring.dto;

public class SensorValueDto {

    private String deviceId;

    private String temperature;

    private String pressure;

    private String humidity;

    private String gas;

    private String altitude;

    private String tvoc;

    private String eco2;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getTvoc() {
        return tvoc;
    }

    public void setTvoc(String tvoc) {
        this.tvoc = tvoc;
    }

    public String getEco2() {
        return eco2;
    }

    public void setEco2(String eco2) {
        this.eco2 = eco2;
    }

    @Override
    public String toString() {
        return "SensorValueDto [deviceId=" + deviceId + ", temperature=" + temperature + ", pressure=" + pressure + ", humidity=" + humidity + ", gas=" + gas
                + ", altitude=" + altitude + ", tvoc=" + tvoc + ", eco2=" + eco2 + "]";
    }

}