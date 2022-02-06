package com.thequietdeveloper.mc.playereventsdiscord;
import com.thequietdeveloper.mc.playereventsdiscord.listeners.PlayerEventListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    FileConfiguration fileConfig = getConfig();

    @Override
    public void onEnable() {
        Bukkit.getLogger().info(this.getName() + " enabled");
        getServer().getPluginManager().registerEvents(new PlayerEventListener(getPluginConfig()), this);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info(this.getName() + " disabled");
    }

    private PluginConfig getPluginConfig() {
        this.saveDefaultConfig();
        return PluginConfig.getPluginConfig(fileConfig);
    }
}
