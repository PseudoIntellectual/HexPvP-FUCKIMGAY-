package com.godhatesfags;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Nigger on 8/24/2015.
 */
public class KitExecutor implements CommandExecutor {
    public KitExecutor(NiggerKits plugin){
        this.plugin = plugin;
    }
    NiggerKits plugin;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length != 1){
            return false;
        }else{
            for(Kit kit : plugin.getKits()){
                if(kit.name.equalsIgnoreCase(strings[0]) && commandSender instanceof Player) {
                    Player sender = (Player) commandSender;
                    kit.giveToPlayer(sender);
                }
            }
            return true;
        }
    }
}
