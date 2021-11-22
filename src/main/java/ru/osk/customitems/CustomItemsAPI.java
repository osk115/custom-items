package ru.osk.customitems;

import org.bukkit.inventory.ItemStack;

public class CustomItemsAPI {

    private final CustomItems plugin;

    CustomItemsAPI(CustomItems plugin) {
        this.plugin = plugin;
    }

    public ItemStack getItem(String id) {
        return plugin.getConfigManager().getItemsManager().getItems().get(id);
    }

    public boolean hasItem(String id) {
        return plugin.getConfigManager().getItemsManager().getItems().containsKey(id);
    }

}
