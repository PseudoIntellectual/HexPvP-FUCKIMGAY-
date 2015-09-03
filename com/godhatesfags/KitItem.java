package com.godhatesfags;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Nigger on 8/24/2015.
 */
public class KitItem {
    public KitItem(ItemStack itemStack, int row){
        this.itemStack = itemStack;
        this.row = row;
    }

    public KitItem(ItemStack itemStack){
        this.itemStack = itemStack;
        this.row = 4;
    }

    int row;
    ItemStack itemStack;


    public int getRow(){
        return row;
    }
    public ItemStack getItemStack(){
        if(itemStack == null)
            return new ItemStack(Material.AIR);
        return itemStack;
    }
}
