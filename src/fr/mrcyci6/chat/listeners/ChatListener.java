package fr.mrcyci6.chat.listeners;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.mrcyci6.chat.MainSpigot;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private MainSpigot plugin;

    public ChatListener(MainSpigot plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {

        if(this.plugin.getConfig().getBoolean("ChatInterServeur") == true) {

            if (!(event.getPlayer() instanceof Player)) return;
            Player player = (Player) event.getPlayer();

            event.setCancelled(true);

            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("chat");
            out.writeUTF(event.getMessage());
            out.writeUTF(PlaceholderAPI.setPlaceholders(player, this.plugin.getConfig().getString("format").replace("{MESSAGE}", event.getMessage()).replace("&", "§")));

            player.sendPluginMessage(this.plugin, "BungeeCord", out.toByteArray());
            Bukkit.getConsoleSender().sendMessage(PlaceholderAPI.setPlaceholders(player, "⚠" + this.plugin.getConfig().getString("format").replace("{MESSAGE}", event.getMessage()).replace("&", "§")));

        } else {

            Player player = event.getPlayer();

            TextComponent msg = new TextComponent("⚠");
            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§eCliquez ici pour signaler ce message à l'administration").create()));
            msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/msgreport monsieur666 " + player.getName() + " " + event.getMessage()));
            TextComponent message = new TextComponent(PlaceholderAPI.setPlaceholders(player, this.plugin.getConfig().getString("format").replace("{MESSAGE}", event.getMessage()).replace("&", "§")));


            event.setCancelled(true);
            Bukkit.spigot().broadcast(msg, message);
            Bukkit.getConsoleSender().sendMessage(PlaceholderAPI.setPlaceholders(player, "⚠" + this.plugin.getConfig().getString("format").replace("{MESSAGE}", event.getMessage()).replace("&", "§")));
        }
    }
}
