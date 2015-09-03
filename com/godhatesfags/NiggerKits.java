package com.godhatesfags;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Nigger on 8/24/2015.
 */
// I'm not actually sure that this is working. Haven't tested it yet.
public class NiggerKits extends JavaPlugin {
    Logger logger = Bukkit.getLogger();

    Kit[] kits;

    @Override
    public void onEnable(){
        logger.log(Level.INFO, "Kits has been enabled! :)");
        File configFile = new File(getDataFolder()+ File.separator, "config.yml");
        if(!(configFile.exists())){
            try{
                getLogger().info("Creating new config.yml for NiggerKits");
                configFile.createNewFile();
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        kits = new YMLKitParser(this).parse();
        this.getCommand("kit").setExecutor(new KitExecutor(this));
    }

    @Override
    public void onDisable(){
        logger.log(Level.INFO, "Kits has been disabled. :(");
    }

    public Kit[] getKits(){
        return kits;
    }
}
