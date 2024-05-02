/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.gui;

import java.util.UUID;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.gui.GUI;
import vn.giakhanhvn.skysim.gui.GUIClickableItem;

public interface GUISignItem
extends GUIClickableItem {
    public GUI onSignClose(String var1, Player var2);

    public UUID inti();
}

