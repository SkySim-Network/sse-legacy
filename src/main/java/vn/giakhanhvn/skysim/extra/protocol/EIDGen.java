/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.extra.protocol;

class EIDGen {
    private static int lastIssuedEID = 2000000000;

    EIDGen() {
    }

    static int generateEID() {
        return lastIssuedEID++;
    }
}

