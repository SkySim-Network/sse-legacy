/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.AtomicDouble
 *  org.bukkit.entity.Entity
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 */
package vn.giakhanhvn.skysim.item.pet;

import com.google.common.util.concurrent.AtomicDouble;
import java.util.List;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import vn.giakhanhvn.skysim.item.SItem;

public interface PetAbility {
    public String getName();

    public List<String> getDescription(SItem var1);

    default public void onHurt(EntityDamageByEntityEvent e, Entity damager) {
    }

    default public void onDamage(EntityDamageByEntityEvent e) {
    }

    default public void onZealotAttempt(AtomicDouble chance) {
    }
}

