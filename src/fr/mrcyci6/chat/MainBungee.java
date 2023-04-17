package fr.mrcyci6.chat;

import fr.mrcyci6.chat.listeners.BungeeListener;
import fr.mrcyci6.chat.utils.ConfigManager;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import java.util.ArrayList;

public class MainBungee extends Plugin {

    private MainBungee instance;
    
    public String prefix = "§c[§4CHAT§c]";

    public void onEnable() {
        instance = this;

        // LISTENER
        PluginManager pm = this.getProxy().getPluginManager();
        pm.registerListener(this, new BungeeListener());

        sendLog("§aPlugin is enabled");
    }

    public void onDisable() {
        sendLog("§cPlugin is disabled");
    }


    public void sendLog(String string) {
         System.out.println(prefix + " §r" + string);
    }

}
