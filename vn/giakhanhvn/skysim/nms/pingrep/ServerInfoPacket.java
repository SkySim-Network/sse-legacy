/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.nms.pingrep;

import vn.giakhanhvn.skysim.nms.pingrep.PingReply;

public abstract class ServerInfoPacket {
    private PingReply reply;

    public ServerInfoPacket(PingReply reply) {
        this.reply = reply;
    }

    public abstract void send();

    public PingReply getReply() {
        return this.reply;
    }

    public void setReply(PingReply reply) {
        this.reply = reply;
    }
}

