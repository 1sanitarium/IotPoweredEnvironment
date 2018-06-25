package net.metro.iot.monitoring.iotMonitoring.persistence.config;

public enum DbSchemaTags {
    KEYSPACE("keyspace"), USE("use"), TABLE("table"), INDEX("index"), SQL("sql");

    private String tagName;

    DbSchemaTags(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }
}
