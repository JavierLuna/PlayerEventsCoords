package com.thequietdeveloper.mc.playereventsdiscord.listeners;

import com.thequietdeveloper.mc.playereventsdiscord.PluginConfig;
import com.thequietdeveloper.mc.playereventsdiscord.utils.DiscordHelper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class PlayerEventListener implements Listener {
    private final DiscordHelper discordHelper;
    private final PluginConfig pluginConfig;

    public PlayerEventListener(PluginConfig pluginConfig) {
        this.pluginConfig = pluginConfig;
        discordHelper = new DiscordHelper(this.pluginConfig.getDiscordWebHookURL());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (pluginConfig.reactToOnPlayerJoins()) {
            Player player = event.getPlayer();
            discordHelper.playerJoined(player);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (pluginConfig.reactToOnPlayerLeaves()) {
            Player player = event.getPlayer();
            discordHelper.playerLeft(player);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (pluginConfig.reactToOnPlayerDies()) {
            Player player = event.getEntity();
            discordHelper.playerDied(player, event.getDeathMessage());
        }
    }
}
