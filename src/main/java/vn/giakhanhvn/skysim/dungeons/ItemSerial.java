/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.dungeons;

import java.util.ArrayList;
import java.util.List;
import vn.giakhanhvn.skysim.item.SItem;

public class ItemSerial {
    private double damage = 0.0;
    private double strength = 0.0;
    private double critchance = 0.0;
    private double critdamage = 0.0;
    private double ferocity = 0.0;
    private double intelligence = 0.0;
    private double atkSpeed = 0.0;
    private double speed = 0.0;
    private double health = 0.0;
    private double defense = 0.0;
    private double magicFind = 0.0;
    private List<Double> intarr = new ArrayList<Double>();

    public ItemSerial(double damage, double strength, double critdamage, double critchance, double ferocity, double intelligence, double speed, double atkSpeed, double health, double defense, double magicFind) {
        this.damage = damage;
        this.strength = strength;
        this.critdamage = critdamage;
        this.critchance = critchance;
        this.ferocity = ferocity;
        this.intelligence = intelligence;
        this.speed = speed;
        this.atkSpeed = atkSpeed;
        this.health = health;
        this.defense = defense;
        this.magicFind = magicFind;
    }

    public void saveTo(SItem sitem) {
        this.intarr.add(this.damage);
        this.intarr.add(this.strength);
        this.intarr.add(this.critdamage);
        this.intarr.add(this.critchance);
        this.intarr.add(this.ferocity);
        this.intarr.add(this.intelligence);
        this.intarr.add(this.speed);
        this.intarr.add(this.atkSpeed);
        this.intarr.add(this.health);
        this.intarr.add(this.defense);
        this.intarr.add(this.magicFind);
        StringBuilder sb = new StringBuilder();
        sb.append("ItemBoost[");
        for (int j = 0; j < this.intarr.size(); ++j) {
            if (j == this.intarr.size() - 1) {
                sb.append(this.intarr.get(j));
                continue;
            }
            sb.append(this.intarr.get(j) + ",");
        }
        sb.append("]");
        sitem.setDataString("boost", sb.toString());
        sitem.setDataBoolean("dungeons_item", true);
    }

    public static ItemSerial getItemBoostStatistics(SItem sitem) {
        String s = sitem.getDataString("boost");
        if (!s.contains("ItemBoost")) {
            return ItemSerial.createBlank();
        }
        s = s.replace("ItemBoost[", "");
        s = s.replace("]", "");
        String[] sta = s.split(",");
        float[] f = new float[11];
        for (int i = 0; i < sta.length; ++i) {
            try {
                f[i] = Float.parseFloat(sta[i]);
                continue;
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return new ItemSerial(f[0], f[1], f[2], f[3], f[4], f[5], f[6], f[7], f[8], f[9], f[10]);
    }

    public static ItemSerial createBlank() {
        float[] f = new float[11];
        return new ItemSerial(f[0], f[1], f[2], f[3], f[4], f[5], f[6], f[7], f[8], f[9], f[10]);
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getDamage() {
        return this.damage;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public double getStrength() {
        return this.strength;
    }

    public void setCritchance(double critchance) {
        this.critchance = critchance;
    }

    public double getCritchance() {
        return this.critchance;
    }

    public void setCritdamage(double critdamage) {
        this.critdamage = critdamage;
    }

    public double getCritdamage() {
        return this.critdamage;
    }

    public void setFerocity(double ferocity) {
        this.ferocity = ferocity;
    }

    public double getFerocity() {
        return this.ferocity;
    }

    public void setIntelligence(double intelligence) {
        this.intelligence = intelligence;
    }

    public double getIntelligence() {
        return this.intelligence;
    }

    public void setAtkSpeed(double atkSpeed) {
        this.atkSpeed = atkSpeed;
    }

    public double getAtkSpeed() {
        return this.atkSpeed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getHealth() {
        return this.health;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public double getDefense() {
        return this.defense;
    }

    public void setMagicFind(double magicFind) {
        this.magicFind = magicFind;
    }

    public double getMagicFind() {
        return this.magicFind;
    }
}

