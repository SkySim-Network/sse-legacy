/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.plugin.Plugin
 */
package vn.giakhanhvn.skysim.nms.nmsutil.apihelper;

import java.util.HashSet;
import java.util.Set;
import org.bukkit.plugin.Plugin;
import vn.giakhanhvn.skysim.nms.nmsutil.apihelper.API;
import vn.giakhanhvn.skysim.nms.nmsutil.apihelper.exception.HostRegistrationException;
import vn.giakhanhvn.skysim.nms.nmsutil.apihelper.exception.MissingHostException;

public class RegisteredAPI {
    protected final API api;
    protected final Set<Plugin> hosts = new HashSet<Plugin>();
    protected boolean initialized = false;
    protected Plugin initializerHost;
    protected boolean eventsRegistered = false;

    public RegisteredAPI(API api) {
        this.api = api;
    }

    public void registerHost(Plugin host) throws HostRegistrationException {
        if (this.hosts.contains(host)) {
            throw new HostRegistrationException("API host '" + host.getName() + "' for '" + this.api.getClass().getName() + "' is already registered");
        }
        this.hosts.add(host);
    }

    public Plugin getNextHost() throws MissingHostException {
        if (this.api instanceof Plugin && ((Plugin)this.api).isEnabled()) {
            return (Plugin)this.api;
        }
        if (this.hosts.isEmpty()) {
            throw new MissingHostException("API '" + this.api.getClass().getName() + "' is disabled, but no other Hosts have been registered");
        }
        for (Plugin host : this.hosts) {
            if (!host.isEnabled()) continue;
            return host;
        }
        throw new MissingHostException("API '" + this.api.getClass().getName() + "' is disabled and all registered Hosts are as well");
    }

    public void init() {
        if (this.initialized) {
            return;
        }
        this.initializerHost = this.getNextHost();
        this.api.init(this.initializerHost);
        this.initialized = true;
    }

    public void disable() {
        if (!this.initialized) {
            return;
        }
        this.api.disable(this.initializerHost);
        this.initialized = false;
    }
}

