/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.comphenix.protocol.ProtocolLibrary
 *  com.comphenix.protocol.events.PacketContainer
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.extra.protocol;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import java.lang.reflect.InvocationTargetException;
import org.bukkit.entity.Player;

public class WrappedBeamPacket {
    private final PacketContainer handle;

    public WrappedBeamPacket(PacketContainer container) {
        this.handle = container;
    }

    public void send(Player receiver) {
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(receiver, this.handle);
        }
        catch (InvocationTargetException ex) {
            throw new RuntimeException("son of a bitch, did you actually fuck sth up????.", ex);
        }
    }

    public PacketContainer getHandle() {
        return this.handle;
    }
}

