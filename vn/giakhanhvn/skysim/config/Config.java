/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.configuration.file.YamlConfiguration
 */
package vn.giakhanhvn.skysim.config;

import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;
import vn.giakhanhvn.skysim.SkySimEngine;

public class Config
extends YamlConfiguration {
    private final SkySimEngine plugin = SkySimEngine.getPlugin();
    private final File file;

    public Config(File parent, String name) {
        this.file = new File(parent, name);
        if (!this.file.exists()) {
            this.options().copyDefaults(true);
            this.plugin.saveResource(name, false);
        }
        this.load();
    }

    public Config(String name) {
        this(SkySimEngine.getPlugin().getDataFolder(), name);
    }

    public void load() {
        try {
            super.load(this.file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            super.save(this.file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

