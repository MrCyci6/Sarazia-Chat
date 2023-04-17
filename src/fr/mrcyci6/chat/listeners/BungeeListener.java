package fr.mrcyci6.chat.listeners;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import fr.mrcyci6.chat.MainBungee;
import fr.mrcyci6.chat.utils.ConfigManager;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.ArrayList;


public class BungeeListener implements Listener {

    private MainBungee plugin;
    public ArrayList<String> sList = new ArrayList<>();

    @EventHandler
    public void onMessaging(PluginMessageEvent event) {
        init();

        if(!(event.getTag().equalsIgnoreCase("BungeeCord"))) return;

        ByteArrayDataInput in = ByteStreams.newDataInput(event.getData());
        String data = in.readUTF();

        if(data.equalsIgnoreCase("chat")) {

            String message = in.readUTF();
            String formatedMessage = in.readUTF();

            TextComponent msg = new TextComponent("⚠");
            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§eCliquez ici pour signaler ce message à l'administration").create()));
            msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/msgreport monsieur666 " + event.getReceiver() + " " + message));
            TextComponent messages = new TextComponent(formatedMessage);

            for(ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {

                String serverName = player.getServer().getInfo().getName();

                if (sList.contains(serverName)) {
                    player.sendMessage(msg, messages);
                }
            }
        }
    }

    public void init() {
        if(sList.contains("Lobby1")) return;
        if(sList.contains("Lobby2")) return;

        sList.add("Lobby1");
        sList.add("Lobby2");
    }
}
