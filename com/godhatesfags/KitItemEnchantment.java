package com.godhatesfags;

import org.bukkit.enchantments.Enchantment;

/**
 * Created by Nigger on 8/24/2015.
 */
public class KitItemEnchantment {
    public KitItemEnchantment(Enchantment enchantment, int level){
        this.enchantment = enchantment;
        this.level = level;
    }

    Enchantment enchantment;
    int level;

    public Enchantment getEnchantment() {
        return enchantment;
    }

    public int getLevel() {
        return level;
    }
}
