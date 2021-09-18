package ru.osk.customitems.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Colorizer {

    public static String colorizeString(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public static List<String> colorizeList(List<String> inputList) {

        List<String> outputList = new ArrayList<>();
        for (String line : inputList) {
            outputList.add(colorizeString(line));
        }

        return outputList;

    }

}
