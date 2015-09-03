package com.godhatesfags;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.godhatesfags.KitItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

/**
 * Created by Nigger on 8/24/2015.
 */
public class Kit {
    public Kit(String name){
        this.name = name;
    }

    public String name;

    private ArrayList<KitItem> armorItems = new ArrayList<KitItem>(),
            items = new ArrayList<KitItem>();


    public ArrayList<KitItem> getArmorItems(){
        return armorItems;
    }

    public ArrayList<KitItem> getItems(){
        return items;
    }

    public enum ArmorType
    {
        HEAD, CHEST, LEGS, FEET, NONE
    }

    public ArmorType getArmorType(ItemStack armorItem){
        Material[] headMaterials = {Material.DIAMOND_HELMET, Material.IRON_HELMET, Material.CHAINMAIL_HELMET,
                                    Material.LEATHER_HELMET, Material.GOLD_HELMET},
                   chestMaterials = {Material.DIAMOND_CHESTPLATE, Material.IRON_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE,
                                     Material.LEATHER_CHESTPLATE, Material.GOLD_CHESTPLATE},
                   legMaterials = {Material.DIAMOND_LEGGINGS, Material.IRON_LEGGINGS, Material.CHAINMAIL_LEGGINGS,
                                   Material.LEATHER_LEGGINGS, Material.GOLD_LEGGINGS},
                   feetMaterials = {Material.DIAMOND_BOOTS, Material.IRON_BOOTS, Material.CHAINMAIL_BOOTS,
                                    Material.LEATHER_BOOTS, Material.GOLD_BOOTS};
        for(Material material : headMaterials)
            if(armorItem.getType() == material)
                return ArmorType.HEAD;
        for(Material material : chestMaterials)
            if(armorItem.getType() == material)
                return ArmorType.CHEST;
        for(Material material : legMaterials)
            if(armorItem.getType() == material)
                return ArmorType.LEGS;
        for(Material material : feetMaterials)
            if(armorItem.getType() == material)
                return ArmorType.FEET;
        return ArmorType.NONE;
    }

    public static Object[] reverseArray(Object[] arr) {
        List<Object> list = Arrays.asList(arr);
        Collections.reverse(list);
        return list.toArray();
    }

    public void giveToPlayer(Player player){
        if(getArmorItems().size() > 4){
            Bukkit.getLogger().log(Level.SEVERE, "There is too much armor in the kit " + name);
            return;
        }
        if(getItems().size() > 36){
            Bukkit.getLogger().log(Level.SEVERE, "There are too many items in the kit " + name);
            return;
        }

        ItemStack[] armorItemsBuf = new ItemStack[4];
        for(KitItem armorItem : getArmorItems()){
            switch(getArmorType(armorItem.getItemStack())){
                case HEAD:
                    armorItemsBuf[0] = armorItem.getItemStack();
                    break;
                case CHEST:
                    armorItemsBuf[1] = armorItem.getItemStack();
                    break;
                case LEGS:
                    armorItemsBuf[2] = armorItem.getItemStack();
                    break;
                case FEET:
                    armorItemsBuf[3] = armorItem.getItemStack();
                    break;
            }
        }
        for(int i = 0; i < getArmorItems().size(); i++)
            armorItemsBuf[i] = getArmorItems().get(i).getItemStack();

        player.getInventory().setArmorContents((ItemStack[]) reverseArray(armorItemsBuf));

        int row1Quant = 0, row2Quant = 0, row3Quant = 0, hotbarQuant = 0; //Here so we don't overwrite items

        for(KitItem kitItem : getItems()){
            switch(kitItem.getRow()){
                case 1:
                    player.getInventory().setItem(9 + row1Quant, kitItem.getItemStack());
                    row1Quant++;
                    break;
                case 2:
                    player.getInventory().setItem(18 + row2Quant, kitItem.getItemStack());
                    row2Quant++;
                    break;
                case 3:
                    player.getInventory().setItem(27 + row3Quant, kitItem.getItemStack());
                    row3Quant++;
                    break;
                case 4:
                    player.getInventory().setItem(hotbarQuant, kitItem.getItemStack());
                    hotbarQuant++;
                    break;
            }
        }
    }
}
