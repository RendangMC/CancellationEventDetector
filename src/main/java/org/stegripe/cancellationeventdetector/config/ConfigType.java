package org.stegripe.cancellationeventdetector.config;

public enum ConfigType {
    EVENT_CLASSES("event-classes", new String[]{}),
    ;
    public final String path;
    public final Object defaultValue;

    ConfigType(String path, Object defaultValue) {
        this.path = path;
        this.defaultValue = defaultValue;
    }
}
