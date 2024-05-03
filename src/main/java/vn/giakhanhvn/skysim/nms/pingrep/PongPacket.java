/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.nms.pingrep;

import vn.giakhanhvn.skysim.nms.pingrep.PingEvent;

public abstract class PongPacket {
    private PingEvent event;

    public PongPacket(PingEvent event) {
        this.event = event;
    }

    public abstract void send();

    public PingEvent getEvent() {
        return this.event;
    }

    public void setEvent(PingEvent event) {
        this.event = event;
    }
}

