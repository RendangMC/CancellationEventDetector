package org.stegripe.cancellationeventdetector;

import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class CancellationEventDetector extends JavaPlugin implements Listener {
    private CancellationDetector entityBreedEventDetector = new CancellationDetector(EntityBreedEvent.class);
    private CancellationDetector creatureSpawnEventDetector = new CancellationDetector(CreatureSpawnEvent.class);
    private CancellationDetector entitySpawnEventDetector = new CancellationDetector(EntitySpawnEvent.class);
    private CancellationDetector entityDamageEventDetector = new CancellationDetector(EntityDamageEvent.class);
    private CancellationDetector entityDamageByEntityEventDetector = new CancellationDetector(EntityDamageByEntityEvent.class);
    private CancellationDetector entityDeathEventDetector = new CancellationDetector(EntityDeathEvent.class);
    private CancellationDetector entityDamageByBlockEventDetector = new CancellationDetector(EntityDamageByBlockEvent.class);

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
        entityDamageEventDetector.addListener((plugin, event) -> {
            getLogger().info("EntityDamageEvent is canceled by " + plugin.getName());
        });
        entityDamageByEntityEventDetector.addListener((plugin, event) -> {
            getLogger().info("EntityDamageByEntityEvent is canceled by " + plugin.getName());
        });
        entityDeathEventDetector.addListener((plugin, event) -> {
            getLogger().info("EntityDeathEvent is canceled by " + plugin.getName());
        });
        entityDamageByBlockEventDetector.addListener((plugin, event) -> {
            getLogger().info("EntityDamageByBlockEvent is canceled by " + plugin.getName());
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        entityBreedEventDetector.close();
        creatureSpawnEventDetector.close();
        entitySpawnEventDetector.close();
        entityDamageEventDetector.close();
        entityDamageByEntityEventDetector.close();
        entityDeathEventDetector.close();
    }
}
