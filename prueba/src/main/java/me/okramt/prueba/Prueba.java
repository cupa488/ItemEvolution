package me.okramt.prueba;

import org.bukkit.plugin.java.JavaPlugin;

import javax.xml.crypto.Data;

public final class Prueba extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Database.registerDatabase(getDataFolder());
        Database.initializeDatabase();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
