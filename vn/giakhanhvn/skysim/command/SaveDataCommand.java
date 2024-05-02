/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;
import vn.giakhanhvn.skysim.user.User;
import vn.giakhanhvn.skysim.util.SLog;
import vn.giakhanhvn.skysim.util.Sputnik;

@CommandParameters(description="Spec test command.", aliases="fsd")
public class SaveDataCommand
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        Player player = sender.getPlayer();
        if (player != null) {
            if (player.isOp()) {
                this.send(ChatColor.GRAY + "Performing save action, please wait...");
                SLog.info("[SYSTEM] Saving players data, this action was performed by " + player.getName() + "...");
                for (Player p : Bukkit.getOnlinePlayers()) {
                    User user = User.getUser(p.getUniqueId());
                    if (user == null) continue;
                    user.saveCookie();
                    user.save();
                    user.saveAllVanillaInstances();
                }
                Bukkit.broadcastMessage((String)Sputnik.trans("&b[SKYSIM D.C] &aAll players data have been saved! Action performed by " + player.getDisplayName() + "&a!"));
            }
        } else {
            SLog.info("[SYSTEM] Saving players data, this action was performed by CONSOLE...");
            for (Player p : Bukkit.getOnlinePlayers()) {
                User user = User.getUser(p.getUniqueId());
                if (user == null) continue;
                user.saveCookie();
                user.save();
                user.saveAllVanillaInstances();
            }
            Bukkit.broadcastMessage((String)Sputnik.trans("&b[SKYSIM D.C] &aAll players data have been saved! Action performed by &cCONSOLE&a!"));
        }
    }
}

