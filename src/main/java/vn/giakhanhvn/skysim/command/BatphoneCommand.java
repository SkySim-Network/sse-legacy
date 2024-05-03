/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.command.ConsoleCommandSender
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.command;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.command.CommandFailException;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;
import vn.giakhanhvn.skysim.gui.GUI;
import vn.giakhanhvn.skysim.gui.GUIType;
import vn.giakhanhvn.skysim.item.oddities.MaddoxBatphone;
import vn.giakhanhvn.skysim.util.SUtil;

@CommandParameters(description="Hidden command for Maddox Batphone.")
public class BatphoneCommand
extends SCommand {
    public static final UUID ACCESS_KEY = UUID.randomUUID();
    public static final List<String> KEYS = new ArrayList<String>();

    @Override
    public void run(CommandSource sender, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            throw new CommandFailException("Console senders cannot use this command!");
        }
        if (!args[0].equals(ACCESS_KEY.toString())) {
            return;
        }
        if (!KEYS.contains(args[1])) {
            throw new CommandFailException(ChatColor.RED + "\u2706 It's too late now, the phone line is off! Call again!");
        }
        Player player = sender.getPlayer();
        MaddoxBatphone.CALL_COOLDOWN.add(player.getUniqueId());
        SUtil.delay(() -> MaddoxBatphone.CALL_COOLDOWN.remove(player.getUniqueId()), 400L);
        GUI gui = GUIType.SLAYER.getGUI();
        gui.open(player);
    }
}

