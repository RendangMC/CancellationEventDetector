package org.stegripe.cancellationeventdetector.command;

import me.dlands.dcommandlib.api.command.CommandEvent;
import me.dlands.dcommandlib.api.command.DCommands;
import me.dlands.dcommandlib.api.command.annotation.AutoComplete;
import me.dlands.dcommandlib.api.command.annotation.CommandExecute;
import org.stegripe.cancellationeventdetector.CancellationEventDetector;
import java.util.List;

public class Command implements DCommands {

    private final CancellationEventDetector plugin;

    public Command(CancellationEventDetector plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getCommandName() {
        return "cancellactioneventdetector";
    }

    @CommandExecute(command = "active", description = "Active the detector", usages = "<true|false>")
    public boolean active(CommandEvent event) {
        if(event.getArgs().length < 2) {
            return false;
        }
        boolean active = Boolean.parseBoolean(event.getArgs()[1]);
        plugin.setDetectorEnabled(active);
        event.getSender().sendMessage("Detector is now " + (active ? "enabled" : "disabled\n" +
                "Information will be printed to the console when an event is cancelled."));
        return true;
    }

    @AutoComplete(command = "active")
    public List<String> activeAutoComplete(CommandEvent event) {
        switch (event.getArgs().length) {
            case 2:
                return List.of("true", "false");
            default:
                return List.of();
        }
    }
}
