package ru.osk.customitems.config.items;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.Configuration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ru.osk.customitems.utils.Colorizer;

import java.util.*;

public class ItemsManager {

    private final Configuration config;
    private final HashMap<String, ItemStack> items = new HashMap<>();

    public ItemsManager(Configuration config) {

        this.config = config;

        for (String itemId : config.getConfigurationSection("items").getKeys(false))
            items.put(itemId, generateItemStack("items." + itemId));

        for (String itemId : config.getConfigurationSection("potions").getKeys(false))
            items.put(itemId, generatePotion("potions." + itemId));

    }

    private ItemStack generateItemStack(String path) {

        ItemStack itemStack = new ItemStack(Material.matchMaterial(config.getString(path + ".material", "STONE")));
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(Colorizer.colorizeString(config.getString(path + ".displayName", "&7")));
        itemMeta.setLore(Colorizer.colorizeList(config.getStringList(path + ".lore")));

        itemMeta.setUnbreakable(config.getBoolean(path + ".unbreakable"));

        for (String enchant : config.getStringList(path + ".enchantments")) {
            String[] args = enchant.split(";");
            itemMeta.addEnchant(Enchantment.getByName(args[0]), Integer.parseInt(args[1]), true);
        }

        if (config.getBoolean(path + ".flags.hideAttributes", false))
            itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        if (config.getBoolean(path + ".flags.hideDestroys", false))
            itemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);

        if (config.getBoolean(path + ".flags.hideEnchants", false))
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        if (config.getBoolean(path + ".flags.hidePlacedOn", false))
            itemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);

        if (config.getBoolean(path + ".flags.hidePotionEffects", false))
            itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        if (config.getBoolean(path + ".flags.hideUnbreakable", false))
            itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        if (config.contains(path + ".attributes")) {

            for (String attributeId : config.getConfigurationSection(path + ".attributes").getKeys(false)) {

                AttributeModifier modifier = new AttributeModifier(
                        UUID.randomUUID(),
                        Objects.requireNonNull(config.getString(path + ".attributes." + attributeId + ".name")),
                        config.getDouble(path + ".attributes." + attributeId + ".amount"),
                        AttributeModifier.Operation.valueOf(config.getString(path + ".attributes." + attributeId + ".operation")),
                        EquipmentSlot.valueOf(config.getString(path + ".attributes." + attributeId + ".equipmentSlot")));

                itemMeta.addAttributeModifier(Attribute.valueOf(config.getString(path + ".attributes." + attributeId + ".attribute")),
                        modifier);

            }

        }

        itemStack.setItemMeta(itemMeta);
        return itemStack;

    }

    private ItemStack generatePotion(String path) {

        ItemStack itemStack = new ItemStack(Material.matchMaterial(config.getString(path + ".material", "POTION")));
        PotionMeta potionMeta = (PotionMeta) itemStack.getItemMeta();

        potionMeta.setDisplayName(Colorizer.colorizeString(config.getString(path + ".displayName", "")));
        potionMeta.setLore(Colorizer.colorizeList(config.getStringList(path + ".lore")));
        potionMeta.setColor(Color.fromRGB(
                config.getInt(path + ".color.r"), config.getInt(path + ".color.g"), config.getInt(path + ".color.b")));

        for (String effectId : config.getConfigurationSection(path + ".effects").getKeys(false)) {

            potionMeta.addCustomEffect(
                    new PotionEffect(PotionEffectType.getByName(config.getString(path + ".effects." + effectId + ".effect")),
                            config.getInt(path + ".effects." + effectId + ".duration"),
                            config.getInt(path + ".effects." + effectId + ".amplifier")),
                    true);

        }

        itemStack.setItemMeta(potionMeta);
        return itemStack;

    }

    public HashMap<String, ItemStack> getItems() {
        return items;
    }
}
