/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.bukkit.Location
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.extra.beam;

import com.google.common.base.Preconditions;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.extra.protocol.PacketFactory1_8_R3;
import vn.giakhanhvn.skysim.extra.protocol.WrappedBeamPacket;

public class LocationTargetBeam {
    private final WrappedBeamPacket packetSquidSpawn;
    private final WrappedBeamPacket packetSquidMove;
    private final WrappedBeamPacket packetGuardianSpawn;
    private final WrappedBeamPacket packetGuardianMove;
    private final WrappedBeamPacket packetRemoveEntities;

    public LocationTargetBeam(Location startingPosition, Location endingPosition) {
        Preconditions.checkNotNull((Object)startingPosition, (Object)"startingPosition cannot be null");
        Preconditions.checkNotNull((Object)endingPosition, (Object)"endingPosition cannot be null");
        Preconditions.checkState((boolean)startingPosition.getWorld().equals(endingPosition.getWorld()), (Object)"startingPosition and endingPosition must be in the same world");
        this.packetSquidSpawn = PacketFactory1_8_R3.createPacketSquidSpawn(endingPosition);
        this.packetSquidMove = PacketFactory1_8_R3.createPacketEntityMove(this.packetSquidSpawn);
        this.packetGuardianSpawn = PacketFactory1_8_R3.createPacketGuardianSpawn(startingPosition, this.packetSquidSpawn);
        this.packetGuardianMove = PacketFactory1_8_R3.createPacketEntityMove(this.packetGuardianSpawn);
        this.packetRemoveEntities = PacketFactory1_8_R3.createPacketRemoveEntities(this.packetSquidSpawn, this.packetGuardianSpawn);
    }

    public void start(Player player) {
        this.packetSquidSpawn.send(player);
        this.packetGuardianSpawn.send(player);
    }

    public void setStartingPosition(Player player, Location location) {
        PacketFactory1_8_R3.modifyPacketEntitySpawn(this.packetSquidSpawn, location);
        PacketFactory1_8_R3.modifyPacketEntityMove(this.packetSquidMove, location).send(player);
    }

    public void setEndingPosition(Player player, Location location) {
        PacketFactory1_8_R3.modifyPacketEntitySpawn(this.packetGuardianSpawn, location);
        PacketFactory1_8_R3.modifyPacketEntityMove(this.packetGuardianMove, location).send(player);
    }

    public void cleanup(Player player) {
        this.packetRemoveEntities.send(player);
    }
}

