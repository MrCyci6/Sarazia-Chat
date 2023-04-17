package fr.mrcyci6.chat;

import fr.mrcyci6.chat.commands.ReportCommand;
import fr.mrcyci6.chat.listeners.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class MainSpigot extends JavaPlugin {

    private MainSpigot instance;

    public void onEnable() {
        instance = this;

        // CONFIG
        saveDefaultConfig();

        // LISTENER
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.getPluginManager().registerEvents((Listener) new ChatListener(this), (Plugin) this);

        // COMMAND
        getCommand("msgreport").setExecutor((CommandExecutor) new ReportCommand(this));

        sendLog("§aPlugin is enabled");
    }

    public void onDisable() { sendLog("§cPlugin is disabled"); }

    public void sendLog(String string) {
        Bukkit.getConsoleSender().sendMessage("§c[§4CHAT§c] §r" + string);
    }
}
