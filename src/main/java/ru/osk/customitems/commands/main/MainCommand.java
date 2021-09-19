package ru.osk.customitems.commands.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class MainCommand implements CommandExecutor {

    private final HashMap<String, ItemStack> items;

    public MainCommand(HashMap<String, ItemStack> items) {
        this.items = items;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("customitems.use")) {
            sender.sendMessage(ChatColor.RED + "Недостаточно прав.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.GRAY + "Доступные команды: /ci give [id] [игрок] (количество), /ci list");
            return true;
        }

        if (args[0].equalsIgnoreCase("give")) {

            if (args.length < 3) {
                sender.sendMessage(ChatColor.GRAY + "Использование команды: /ci give [id] [игрок] (количество)");
                return true;
            }

            String id = args[1];
            if (!items.containsKey(id)) {
                sender.sendMessage(ChatColor.GRAY + "Предмет с таким id не найден.");
                sender.sendMessage(ChatColor.GRAY + "Доступные предметы: " + items.keySet());
                return true;
            }

            Player player = Bukkit.getPlayer(args[2]);
            if (player == null) {
                sender.sendMessage(ChatColor.GRAY + String.format("Игрок %s не найден на сервере.", args[2]));
                return true;
            }

            if (args.length < 4) {
                player.getInventory().addItem(items.get(id));
                return true;
            }

            int amount = 1;
            try {
                amount = Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.GRAY + "Введеный агумент не является числом, поэтому количество установлено как 1.");
            }

            ItemStack itemStack = items.get(id);
            itemStack.setAmount(amount);
            player.getInventory().addItem(itemStack);

        } else if (args[0].equalsIgnoreCase("list")) {
            sender.sendMessage(ChatColor.GRAY + "Доступные предметы: " + items.keySet());
        }

        return true;
    }

}
