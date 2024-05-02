/*
 * Decompiled with CFR 0.152.
 */
package vn.giakhanhvn.skysim.command;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value=RetentionPolicy.RUNTIME)
public @interface CommandParameters {
    public String description() default "";

    public String usage() default "/<command>";

    public String aliases() default "";

    public String permission() default "";
}

