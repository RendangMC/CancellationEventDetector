package org.stegripe.cancellationeventdetector;

import me.dlands.dcommandlib.api.DCommandLibrary;
import org.bukkit.plugin.java.JavaPlugin;
import org.stegripe.cancellationeventdetector.command.Command;
import org.stegripe.cancellationeventdetector.config.Config;
import java.util.ArrayList;
import java.util.List;

public final class CancellationEventDetector extends JavaPlugin {

    private final Config pluginConfig;
    private boolean detectorEnabled = false;

    final List<CancellationDetector> detectors = new ArrayList<>();

    public CancellationEventDetector(){
        super();
        pluginConfig = new Config(this);
        loadDetector();
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        DCommandLibrary.getInstance().getCommandManager().registerCommand(this, new Command(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        unloadDetector();
        getLogger().info("Unload detector.");
    }

    public void unloadDetector() {
        List<CancellationDetector> toRemove = new ArrayList<>();
        for(CancellationDetector detector : detectors) {
            detector.close();
            getLogger().info("Unloaded detector for " + detector.getEventClazz().getName());
            toRemove.add(detector);
        }
        detectors.removeAll(toRemove);
    }

    public void loadDetector(){
        for(String className: pluginConfig.eventClasses) {
            try {
                Class<?> clazz = Class.forName(className);
                CancellationDetector detector = new CancellationDetector(clazz);
                detector.addListener((plugin, event) -> {
                    if(detectorEnabled){
                        getLogger().info( event.getEventName() + " is canceled by " + plugin.getName());
                    }
                });
                getLogger().info("Loaded detector for " + className);
                detectors.add(detector);
            } catch (ClassNotFoundException e) {
                getLogger().warning("Class not found: " + className);
            }
        }
    }

    public void setDetectorEnabled(boolean detectorEnabled) {
        this.detectorEnabled = detectorEnabled;
    }
}
