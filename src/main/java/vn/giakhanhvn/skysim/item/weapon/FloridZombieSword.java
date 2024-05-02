/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.ChatColor
 *  org.bukkit.Sound
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 */
package vn.giakhanhvn.skysim.item.weapon;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import vn.giakhanhvn.skysim.Repeater;
import vn.giakhanhvn.skysim.ZSHash;
import vn.giakhanhvn.skysim.item.Ability;
import vn.giakhanhvn.skysim.item.GenericItemType;
import vn.giakhanhvn.skysim.item.MaterialFunction;
import vn.giakhanhvn.skysim.item.Rarity;
import vn.giakhanhvn.skysim.item.SItem;
import vn.giakhanhvn.skysim.item.SpecificItemType;
import vn.giakhanhvn.skysim.item.ToolStatistics;
import vn.giakhanhvn.skysim.user.PlayerUtils;
import vn.giakhanhvn.skysim.util.DefenseReplacement;
import vn.giakhanhvn.skysim.util.ManaReplacement;
import vn.giakhanhvn.skysim.util.SUtil;

public class FloridZombieSword
implements ToolStatistics,
MaterialFunction,
Ability {
    @Override
    public int getBaseDamage() {
        return 150;
    }

    @Override
    public double getBaseStrength() {
        return 80.0;
    }

    @Override
    public double getBaseIntelligence() {
        return 100.0;
    }

    @Override
    public String getDisplayName() {
        return "Florid Zombie Sword";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.WEAPON;
    }

    @Override
    public SpecificItemType getSpecificType() {
        return SpecificItemType.SWORD;
    }

    @Override
    public String getLore() {
        return null;
    }

    @Override
    public String getAbilityName() {
        return "Instant Heal";
    }

    @Override
    public String getAbilityDescription() {
        return "" + ChatColor.translateAlternateColorCodes((char)'&', (String)"&7Heal yourself for &c168 &7+ &c5%\u2764 &7and players within &a7 &7blocks for &c56\u2764");
    }

    @Override
    public boolean displayUsage() {
        return false;
    }

    @Override
    public void onAbilityUse(Player player1, SItem sItem) {
        if (!ZSHash.Charges.containsKey(player1.getUniqueId())) {
            ZSHash.Charges.put(player1.getUniqueId(), 5);
        }
        if (ZSHash.Charges.get(player1.getUniqueId()) > 0) {
            int manaPool = SUtil.blackMagic(PlayerUtils.STATISTICS_CACHE.get(player1.getUniqueId()).getIntelligence().addAll() + 100.0);
            int manaCost = 70;
            final int cost = PlayerUtils.getFinalManaCost(player1, sItem, manaCost);
            boolean take = PlayerUtils.takeMana(player1, cost);
            if (!take) {
                player1.playSound(player1.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, -4.0f);
                final long c = System.currentTimeMillis();
                Repeater.MANA_REPLACEMENT_MAP.put(player1.getUniqueId(), new ManaReplacement(){

                    @Override
                    public String getReplacement() {
                        return "" + ChatColor.RED + ChatColor.BOLD + "NOT ENOUGH MANA";
                    }

                    @Override
                    public long getEnd() {
                        return c + 1500L;
                    }
                });
                return;
            }
            final long c = System.currentTimeMillis();
            Repeater.DEFENSE_REPLACEMENT_MAP.put(player1.getUniqueId(), new DefenseReplacement(){

                @Override
                public String getReplacement() {
                    return ChatColor.AQUA + "-" + cost + " Mana (" + ChatColor.GOLD + FloridZombieSword.this.getAbilityName() + ChatColor.AQUA + ")";
                }

                @Override
                public long getEnd() {
                    return c + 2000L;
                }
            });
            ZSHash.Charges.put(player1.getUniqueId(), ZSHash.Charges.get(player1.getUniqueId()) - 1);
            if (ZSHash.Charges.get(player1.getUniqueId()) == 0) {
                ZSHash.Cooldown.put(player1.getUniqueId(), 15);
            }
            player1.playSound(player1.getLocation(), Sound.ZOMBIE_REMEDY, 0.5f, 1.0f);
            player1.playSound(player1.getLocation(), Sound.SUCCESSFUL_HIT, 1.0f, 1.0f);
            double healamount = 168.0 + player1.getMaxHealth() * 5.0 / 100.0;
            player1.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)("&a&lYou healed yourself for " + SUtil.commaify((int)healamount) + " health!")));
            if (player1.getMaxHealth() < player1.getHealth() + healamount) {
                player1.setHealth(player1.getMaxHealth());
            } else {
                player1.setHealth(player1.getHealth() + healamount);
            }
            for (Entity e : player1.getNearbyEntities(8.0, 8.0, 8.0)) {
                if (!(e instanceof LivingEntity) || !(e instanceof Player)) continue;
                if (((LivingEntity)e).getMaxHealth() < ((LivingEntity)e).getHealth() + 48.0) {
                    ((LivingEntity)e).setHealth(((LivingEntity)e).getMaxHealth());
                } else {
                    ((LivingEntity)e).setHealth(player1.getHealth() + 56.0);
                }
                ((LivingEntity)e).sendMessage("" + ChatColor.GREEN + ChatColor.BOLD + player1.getName() + " healed you for 56 health!");
            }
        } else {
            player1.sendMessage(ChatColor.translateAlternateColorCodes((char)'&', (String)("&cNo more charges, next one in &e" + ZSHash.Cooldown.get(player1.getUniqueId()) + "s")));
        }
    }

    @Override
    public int getAbilityCooldownTicks() {
        return 0;
    }

    @Override
    public int getManaCost() {
        return 0;
    }
}

