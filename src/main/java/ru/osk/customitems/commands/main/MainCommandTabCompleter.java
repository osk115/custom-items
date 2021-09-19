package ru.osk.customitems.commands.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class MainCommandTabCompleter implements TabCompleter {

    private final HashMap<String, ItemStack> items;

    public MainCommandTabCompleter(HashMap<String, ItemStack> items) {
        this.items = items;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (!sender.hasPermission("customitems.use"))
            return Collections.singletonList("Недостаточно прав");

        if (args.length == 1)
            return Arrays.asList("give", "list");

        if (args[0].equalsIgnoreCase("give") && args.length == 2)
            return new ArrayList<>(items.keySet());
        else if (args.length == 4)
            return Arrays.asList("1", "8", "16", "32", "64");

        return null;
    }

}
