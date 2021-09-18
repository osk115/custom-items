package ru.osk.customitems.config;

import org.bukkit.configuration.Configuration;
import ru.osk.customitems.config.items.ItemsManager;

public class ConfigManager {

    private final ItemsManager itemsManager;

    public ConfigManager(Configuration config) {
        itemsManager = new ItemsManager(config);
    }

    public ItemsManager getItemsManager() {
        return itemsManager;
    }

}
