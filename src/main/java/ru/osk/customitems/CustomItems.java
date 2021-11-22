package ru.osk.customitems;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;
import ru.osk.customitems.commands.main.MainCommand;
import ru.osk.customitems.commands.main.MainCommandTabCompleter;
import ru.osk.customitems.config.ConfigManager;

import java.io.File;

public final class CustomItems extends JavaPlugin {

    private ConfigManager configManager;
    private static CustomItemsAPI customItemsAPI;

    @Override
    public void onEnable() {

        createConfig();
        this.configManager = new ConfigManager(this.getConfig());

        this.getCommand("customitems").setExecutor(new MainCommand(configManager.getItemsManager().getItems()));
        this.getCommand("customitems").setTabCompleter(new MainCommandTabCompleter(configManager.getItemsManager().getItems()));

        customItemsAPI = new CustomItemsAPI(this);

    }

    private void createConfig(){
        File config = new File(getDataFolder() + File.pathSeparator + "config.yml");
        if(!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
        reloadConfig();
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public static CustomItemsAPI getAPI() {
        return customItemsAPI;
    }

}
