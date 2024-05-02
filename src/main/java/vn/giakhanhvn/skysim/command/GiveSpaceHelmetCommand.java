/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.command.ConsoleCommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package vn.giakhanhvn.skysim.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import vn.giakhanhvn.skysim.command.CommandFailException;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.user.User;

@CommandParameters(description="bruhbu", aliases="gsh")
public class GiveSpaceHelmetCommand
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        Player player = sender.getPlayer();
        if (!player.isOp()) {
            return;
        }
        if (sender instanceof ConsoleCommandSender) {
            throw new CommandFailException("Console senders cannot use this command!");
        }
        User user = sender.getUser();
        if (args.length == 0) {
            this.send(ChatColor.RED + "Invaild Syntax!");
            return;
        }
        String pgv = args[0];
        String lore = args[1];
        SItem sitem = SItem.of(SMaterial.HIDDEN_DONATOR_HELMET);
        sitem.setDataString("p_given", player.getName());
        if (Bukkit.getPlayer((String)pgv) == null) {
            return;
        }
        sitem.setDataString("p_rcv", pgv);
        if (args[1] != null) {
            sitem.setDataString("lore_d", lore);
        } else {
            sitem.setDataString("lore_d", "null");
        }
        player.getInventory().addItem(new ItemStack[]{sitem.getStack()});
        this.send(ChatColor.GREEN + "Done!");
    }
}

