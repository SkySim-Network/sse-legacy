/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Location
 *  org.bukkit.World
 *  org.bukkit.block.Block
 */
package vn.giakhanhvn.skysim.dimoon;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

public class Altar {
    public static double[] pAe = new double[]{234668.5, 159.0, 236545.5, 234665.5, 159.0, 236545.5};
    public static double[] pAd = new double[]{234731.5, 159.0, 236481.5, 234731.5, 159.0, 236484.5};
    public static double[] pAr = new double[]{234670.5, 159.0, 236418.5, 234673.5, 159.0, 236418.5};
    public static double[] pAg = new double[]{234604.5, 159.0, 236482.5, 234604.5, 159.0, 236479.5};
    public static World w = Bukkit.getWorld((String)"arena");
    public static Block em1 = w.getBlockAt(new Location(w, pAe[0], pAe[1], pAe[2]));
    public static Block em2 = w.getBlockAt(new Location(w, pAe[3], pAe[4], pAe[5]));
    public static Block di1 = w.getBlockAt(new Location(w, pAd[0], pAd[1], pAd[2]));
    public static Block di2 = w.getBlockAt(new Location(w, pAd[3], pAd[4], pAd[5]));
    public static Block rb1 = w.getBlockAt(new Location(w, pAr[0], pAr[1], pAr[2]));
    public static Block rb2 = w.getBlockAt(new Location(w, pAr[3], pAr[4], pAr[5]));
    public static Block gl1 = w.getBlockAt(new Location(w, pAg[0], pAg[1], pAg[2]));
    public static Block gl2 = w.getBlockAt(new Location(w, pAg[3], pAg[4], pAg[5]));
    public static Block[] altarList = new Block[]{di1, di2, em1, em2, gl1, gl2, rb1, rb2};
}

