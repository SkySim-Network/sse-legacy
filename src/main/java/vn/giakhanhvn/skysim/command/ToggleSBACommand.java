/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.command;

import java.util.UUID;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.Repeater;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;
import vn.giakhanhvn.skysim.util.Sputnik;

@CommandParameters(description="Spec test command.", aliases="tsba")
public class ToggleSBACommand
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        Player player = sender.getPlayer();
        if (player != null) {
            UUID uuid = player.getUniqueId();
            if (Repeater.SBA_MAP.get(uuid).booleanValue()) {
                Repeater.SBA_MAP.put(uuid, false);
                this.send(Sputnik.trans("&cNo longer support SkyblockAddons Mods!"));
            } else if (!Repeater.SBA_MAP.get(uuid).booleanValue()) {
                Repeater.SBA_MAP.put(uuid, true);
                this.send(Sputnik.trans("&aNow supporting SBA Mods! Please rejoin!"));
                this.send(Sputnik.trans("&ePlease note that it &6may not working properly &esince this is SkySim, not Hypixel!"));
            }
        } else {
            this.send(Sputnik.trans("&cYou must be a player to execute this command!"));
        }
    }
}

