package com.thequietdeveloper.mc.playereventsdiscord.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.awt.*;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DiscordHelper {
    private String discordWebHookURL;

    public DiscordHelper(String discordWebHookURL) {
        this.discordWebHookURL = discordWebHookURL;
    }

    public void playerJoined(Player player) {
        var webhook = getDiscordWebhook();
        webhook.setTts(true);
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setTitle(player.getDisplayName() + " is now playing!")
                .setColor(Color.GREEN)
                .setThumbnail(getAvatarForPlayer(player))
                .addField("Online players:", getOnlinePlayerUsernames(Bukkit.getOnlinePlayers().stream()), true));
        sendWebhookMessage(webhook);
    }

    public void playerLeft(Player player) {
        var webhook = getDiscordWebhook();
        webhook.setTts(true);
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setTitle(player.getDisplayName() + " has left")
                .setColor(Color.GRAY)
                .setThumbnail(getAvatarForPlayer(player))
                .addField("Online players:", getOnlinePlayerUsernames(Bukkit.getOnlinePlayers().stream().filter(onlinePlayer -> !onlinePlayer.equals(player))), true));

        sendWebhookMessage(webhook);
    }

    public void playerDied(Player player, String reason) {
        var webhook = getDiscordWebhook();
        webhook.setTts(true);
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setTitle(player.getDisplayName() + " has died :(")
                .setColor(Color.RED)
                .setThumbnail(getAvatarForPlayer(player))
                .addField("Reason: ", reason, true));

        sendWebhookMessage(webhook);
    }

    private String getOnlinePlayerUsernames(Stream<? extends Player> playerStream) {
        var usernames = playerStream.map(Player::getDisplayName).collect(Collectors.joining(", "));
        if (!usernames.isEmpty()) {
            return usernames;
        } else {
            return "Nobody";
        }
    }

    private DiscordWebhook getDiscordWebhook() {
        return new DiscordWebhook(this.discordWebHookURL);
    }

    private void sendWebhookMessage(DiscordWebhook webhook) {
        try {
            webhook.execute(); //Handle exception
        } catch (IOException ioException) {
            Bukkit.getLogger().info("Could not send message to discord: " + ioException.getMessage());
        }
    }

    private static String getAvatarForPlayer(Player player) {
        return "https://minotar.net/avatar/" + player.getUniqueId() + "/100.png";
    }
}
