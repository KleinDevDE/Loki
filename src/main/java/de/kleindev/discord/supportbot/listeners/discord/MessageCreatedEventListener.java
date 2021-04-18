package de.kleindev.discord.supportbot.listeners.discord;

import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.commands.Command;
import de.kleindev.discord.supportbot.events.discord.message.MessageCreatedEvent;
import de.kleindev.discord.supportbot.listeners.EventHandler;
import de.kleindev.discord.supportbot.listeners.Listener;
import de.kleindev.discord.supportbot.objects.CommandSender;
import de.kleindev.discord.supportbot.utils.MessageTools;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.awt.*;

public class MessageCreatedEventListener implements Listener {
    @EventHandler
    public void onMessage(MessageCreatedEvent e){
        if (e.getMessage().getContent().startsWith("!")){
            String[] messageSplitted = e.getMessage().getContent().split(" ");
            String[] args = new String[messageSplitted.length - 1];
            System.arraycopy(messageSplitted, 1, args, 0, args.length);
            Command command = SupportBot.getInstance().getCommandManager().getCommand(messageSplitted[0]);
            if (command == null){
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setColor(Color.GRAY);
                embedBuilder.setAuthor(SupportBot.getInstance().getDiscordApi().getYourself());
                embedBuilder.setTimestampToNow();
                embedBuilder.setDescription("Command \""+messageSplitted[0]+"\" not found! ");
                MessageTools.deleteMessageLater(e.getMessage().getChannel().sendMessage(embedBuilder).join(), 5000);
                return;
            }

            command.execute(new CommandSender(e.getMessage().getUserAuthor().get(), e.getMessage().getChannel()), args);
        }
    }
}
