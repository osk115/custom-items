package ru.osk.customitems;

import org.bukkit.plugin.java.JavaPlugin;
import ru.osk.customitems.commands.main.MainCommand;
import ru.osk.customitems.commands.main.MainCommandTabCompleter;
import ru.osk.customitems.config.ConfigManager;

import java.io.File;

public final class CustomItems extends JavaPlugin {

    @Override
    public void onEnable() {

        createConfig();
        ConfigManager configManager = new ConfigManager(this.getConfig());

        this.getCommand("customitems").setExecutor(new MainCommand(configManager.getItemsManager().getItems()));
        this.getCommand("customitems").setTabCompleter(new MainCommandTabCompleter(configManager.getItemsManager().getItems()));

    }

    private void createConfig(){
        File config = new File(getDataFolder() + File.pathSeparator + "config.yml");
        if(!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
        reloadConfig();
    }

}
