package com.thequietdeveloper.mc.playereventsdiscord;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.configuration.file.FileConfiguration;


public class PluginConfig {
    private boolean onPlayerJoins, onPlayerLeaves, onPlayerDies = false;
    private final String discordWebHookURL;

    PluginConfig(FileConfiguration fileConfig) {
        discordWebHookURL = fileConfig.getString("discordWebHookURL");
        if (discordWebHookURL == null || discordWebHookURL.isEmpty()) {
            Bukkit.getLogger().info(Color.RED + "Discord webhook url not supplied, will not send any event to Discord!");
            return;
        }

        onPlayerJoins = fileConfig.getBoolean("onPlayerJoins");
        onPlayerLeaves = fileConfig.getBoolean("onPlayerLeaves");
        onPlayerDies = fileConfig.getBoolean("onPlayerDies");
    }

    public static PluginConfig getPluginConfig(FileConfiguration fileConfig) {
        return new PluginConfig(fileConfig);
    }

    public boolean reactToOnPlayerJoins() {
        return onPlayerJoins;
    }

    public boolean reactToOnPlayerLeaves() {
        return onPlayerLeaves;
    }

    public boolean reactToOnPlayerDies() {
        return onPlayerDies;
    }

    public String getDiscordWebHookURL() {
        return discordWebHookURL;
    }
}
