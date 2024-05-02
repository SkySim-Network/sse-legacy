/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 */
package vn.giakhanhvn.skysim.command;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import vn.giakhanhvn.skysim.command.CommandParameters;
import vn.giakhanhvn.skysim.command.CommandSource;
import vn.giakhanhvn.skysim.command.SCommand;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SMaterial;
import vn.giakhanhvn.skysim.util.Sputnik;

@CommandParameters(description="", aliases="smd", permission="sse.cc")
public class StackMyDimoon
extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
        int stg = 0;
        Player player = sender.getPlayer();
        ItemStack[] iss = player.getInventory().getContents();
        for (int i = 0; i < player.getInventory().getContents().length; ++i) {
            ItemStack is = iss[i];
            if (SItem.find(is) == null || SItem.find(is).getType() != SMaterial.HIDDEN_DIMOON_FRAG) continue;
            stg += is.getAmount();
            player.getInventory().setItem(i, null);
        }
        if (stg > 0) {
            ItemStack is2 = SItem.of(SMaterial.HIDDEN_DIMOON_FRAG).getStack();
            is2.setAmount(stg);
            Sputnik.smartGiveItem(is2, player);
            player.sendMessage(Sputnik.trans("&aStacked all your fragments which have been broken before! Have fun!"));
        }
    }
}

