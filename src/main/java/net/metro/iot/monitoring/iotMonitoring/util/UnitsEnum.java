package net.metro.iot.monitoring.iotMonitoring.util;

public enum UnitsEnum {

    TEMPERATURE("C"), PRESSURE("hPa"), HUMIDITY("%"), GAS("kohms"), ALTITUDE("m"), ECO2("ppm"), TVOC("ppb/t");

    private String unit;

    UnitsEnum(String unit) {
        this.unit = unit;
    }

    public static String getUnit(String type) {
        return getEnum(type).getUnit();
    }

    public static UnitsEnum getEnum(String type) {
        return UnitsEnum.valueOf(type);
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
