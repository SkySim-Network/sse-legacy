/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.World
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.command;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;

@CommandParameters(description="Gets the NBT of your current item.", aliases="kam", permission="spt.item")
public class KillAllMobs
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        Player player = sender.getPlayer();
        if (player == null) {
            this.send(ChatColor.RED + "You can't use this command here!");
            return;
        }
        if (player.isOp()) {
            World world = player.getWorld();
            for (Entity entity : world.getEntities()) {
                if (entity.getType() == EntityType.PLAYER || entity.getType() == EntityType.ITEM_FRAME || entity.getType() == EntityType.MINECART || entity.hasMetadata("pets") || entity.hasMetadata("NPC") || entity.hasMetadata("ss_drop") || entity.hasMetadata("Ire") || entity.hasMetadata("inv")) continue;
                entity.remove();
            }
            this.send(ChatColor.WHITE + "You removed" + ChatColor.YELLOW + " ALL" + ChatColor.RESET + " the mobs in this world.");
        } else {
            this.send(ChatColor.RED + "You can't use this, lol.");
        }
    }
}

