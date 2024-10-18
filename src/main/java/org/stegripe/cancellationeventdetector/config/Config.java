package org.stegripe.cancellationeventdetector.config;

import org.stegripe.cancellationeventdetector.CancellationEventDetector;
import java.util.List;

public class Config {

    final public CancellationEventDetector plugin;
    final public String[] eventClasses;

    public Config(CancellationEventDetector plugin) {
        this.plugin = plugin;
        plugin.reloadConfig();
        var config = plugin.getConfig();
        config.options().copyDefaults(true);
        for(ConfigType type : ConfigType.values()){
            config.addDefault(type.path, type.defaultValue);
        }
        plugin.saveConfig();
        eventClasses = getStringList(ConfigType.EVENT_CLASSES).toArray(new String[0]);
    }

    public boolean getBoolean(ConfigType type){
        return plugin.getConfig().getBoolean(type.path, (Boolean) type.defaultValue);
    }

    public List<String> getStringList(ConfigType type){
        return plugin.getConfig().getStringList(type.path);
    }
}
