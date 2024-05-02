/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.World
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Monster
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.command;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;
import vn.giakhanhvn.skysim.util.Sputnik;

@CommandParameters(description="Gets the NBT of your current item.", aliases="kamh", permission="spt.item")
public class KillAllHostileMobs
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        Player player = sender.getPlayer();
        if (player == null) {
            this.send(ChatColor.RED + "You can't use this command here!");
        }
        if (player.isOp()) {
            World world = player.getWorld();
            for (Entity entity : world.getEntities()) {
                if (!(entity instanceof Monster) || entity.hasMetadata("pets") || entity.hasMetadata("Ire")) continue;
                entity.remove();
            }
            this.send(ChatColor.WHITE + "You removed all" + ChatColor.RED + " HOSTILE" + ChatColor.RESET + " mobs in this world.");
        } else {
            this.send(Sputnik.trans("&cYou cant use this, lol."));
        }
    }
}

