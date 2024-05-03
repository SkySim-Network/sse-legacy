/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.util;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.entity.Player;

public class TradeUtil {
    private static HashMap<UUID, UUID> tradeReq = new HashMap();
    public static final HashMap<UUID, Boolean> trading = new HashMap();

    public static boolean hasRequest(Player p, Player requester) {
        return tradeReq.containsKey(requester.getUniqueId()) && tradeReq.get(requester.getUniqueId()) == p.getUniqueId();
    }

    public static void requestTrade(Player requester, Player p) {
        tradeReq.put(requester.getUniqueId(), p.getUniqueId());
    }

    public static void resetTrade(Player requester) {
        tradeReq.remove(requester.getUniqueId());
    }

    public static boolean isTrading(Player req) {
        if (!trading.containsKey(req.getUniqueId())) {
            trading.put(req.getUniqueId(), false);
        }
        return trading.get(req.getUniqueId());
    }
}

