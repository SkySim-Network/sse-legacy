/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim;

import vn.giakhanhvn.skysim.util.SkyEncryption;

public class ServerVersion {
    private String z;
    private int a;
    private int b;
    private int c;
    private int d;
    private byte[] finalResult;

    public ServerVersion(String stage, int a, int b, int c, int d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.z = stage;
        try {
            this.finalResult = new SkyEncryption().encrypt(this.z + "-" + this.a + "." + this.b + "." + this.c + "." + this.d).getBytes();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readableString() throws Exception {
        return new SkyEncryption().decrypt(new String(this.finalResult));
    }

    public byte[] getEncryptedByteArray() {
        return this.finalResult;
    }

    public byte[] getDecryptedByteArray() throws Exception {
        return new SkyEncryption().decrypt(new String(this.finalResult)).getBytes();
    }
}

