/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.dungeons;

public class WrappedStats {
    private float health;
    private float healthRegen;
    private float strength;
    private float critDamage;
    private float intelligence;
    private float speed;
    private float defense;
    private float[] defaultArray;

    public WrappedStats(float[] z) {
        this.health = z[0];
        this.healthRegen = z[1];
        this.strength = z[2];
        this.critDamage = z[3];
        this.intelligence = z[4];
        this.speed = z[5];
        this.defense = z[6];
        this.defaultArray = z;
    }

    public float getHealth() {
        return this.health;
    }

    public float getHealthRegen() {
        return this.healthRegen;
    }

    public float getStrength() {
        return this.strength;
    }

    public float getCritDamage() {
        return this.critDamage;
    }

    public float getIntelligence() {
        return this.intelligence;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getDefense() {
        return this.defense;
    }

    public float[] getDefaultArray() {
        return this.defaultArray;
    }
}

