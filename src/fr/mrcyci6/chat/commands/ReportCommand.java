package fr.mrcyci6.chat.commands;

import fr.mrcyci6.chat.MainSpigot;
import fr.mrcyci6.chat.utils.Web;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;

public class ReportCommand implements CommandExecutor {

    private MainSpigot plugin;

    public ReportCommand(MainSpigot plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String arg, String[] args) {
        if(!(arg.equalsIgnoreCase("msgreport"))) return false;
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cVous n'êtes pas un joueur");
            return false;
        } else {
            Player player = (Player)sender;
            if(!args[0].equalsIgnoreCase("monsieur666")) {
                player.sendMessage("§cErreur !");
                return false;
            }
            if(args.length < 3) {
                player.sendMessage("§cErreur !");
                return false;
            }

            if(args[1].equalsIgnoreCase(player.getName())) {
                player.sendMessage("§cVous ne pouvez pas vous signaler vous même !");
                return false;
            }

            try {
                String name = args[1];
                String finalMessage = "";
                for(int i = 2; i < args.length; i++) {

                    finalMessage = finalMessage + args[i] + " ";
                }

                Web webhook = new Web(this.plugin.getConfig().getString("webhook"));
                webhook.setContent("");
                webhook.setUsername("Sarazia");
                webhook.setTts(false);
                webhook.setAvatarUrl("https://cdn.discordapp.com/attachments/1073353815027372114/1084257451135750224/saraziaicone.png");
                webhook.addEmbed((new Web.EmbedObject())
                        .setTitle("SIGNALEMENT")
                        .setColor(Color.RED)
                        .setDescription(player.getName() + " à signalé un message")
                        .addField("Joueur", name, false)
                        .addField("Message", finalMessage, false)
                );
                webhook.execute();
                player.sendMessage("§eVotre signalement à été pris en compte !");

            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage(String.valueOf(e));
                player.sendMessage("§c§lUne erreur est survenue, merci de contacter un administrateur");
            }

            return true;
        }
    }
}
