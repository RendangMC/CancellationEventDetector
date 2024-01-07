package org.stegripe.cancellationeventdetector;

import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class CancellationEventDetector extends JavaPlugin implements Listener {
    private CancellationDetector<EntityBreedEvent> entityBreedEventDetector = new CancellationDetector<>(EntityBreedEvent.class);
    private CancellationDetector<CreatureSpawnEvent> creatureSpawnEventDetector = new CancellationDetector<>(CreatureSpawnEvent.class);
    private CancellationDetector<EntitySpawnEvent> entitySpawnEventDetector = new CancellationDetector<>(EntitySpawnEvent.class);
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        entityBreedEventDetector.addListener((plugin, event) -> {
            getLogger().info("EntityBreedEvent is canceled by " + plugin.getName());
        });
        creatureSpawnEventDetector.addListener((plugin, event) -> {
            getLogger().info("CreatureSpawnEvent is canceled by " + plugin.getName());
        });
        entitySpawnEventDetector.addListener((plugin, event) -> {
            getLogger().info("EntitySpawnEvent is canceled by " + plugin.getName());
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        entityBreedEventDetector.close();
        creatureSpawnEventDetector.close();
        entitySpawnEventDetector.close();
    }
}
