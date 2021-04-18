package de.kleindev.discord.supportbot.listeners.discord_internal.message;


import com.vdurmont.emoji.EmojiParser;
import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.commands.Command;
import de.kleindev.discord.supportbot.events.discord.message.MessageCreatedEvent;
import de.kleindev.discord.supportbot.logging.Logger;
import de.kleindev.discord.supportbot.managers.PermissionManager;
import de.kleindev.discord.supportbot.objects.CommandSender;
import de.kleindev.discord.supportbot.utils.MessageTools;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.util.Arrays;

public class AMessageCreateListener implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new MessageCreatedEvent(e));

        if (e.getMessageAuthor().isBotUser() || e.getMessageAuthor().isWebhook() || e.getMessageAuthor().isYourself())
            return;

        if (e.getMessage().getReadableContent().startsWith(SupportBot.getInstance().getSupportBotConfiguration().commandPrefix)){
            Logger.trace("MessageCreateEvent | Command detected -> \""+e.getMessageContent()+"\"");
            String[] messageSplitted = e.getMessage().getReadableContent().split(" ");
            Logger.trace("MessageCreateEvent | messageSplitted -> " + Arrays.toString(messageSplitted));
            String[] args = new String[messageSplitted.length - 1];
            Logger.trace("MessageCreateEvent | args -> " + Arrays.toString(args));
            System.arraycopy(messageSplitted, 1, args, 0, args.length);
            Logger.trace("MessageCreateEvent | args #2 -> " + Arrays.toString(args));
            Command command = SupportBot.getInstance().getCommandManager().getCommand(messageSplitted[0].substring(1));
            MessageTools.deleteMessageLater(e.getMessage(), 5);

            if (command == null){
                Logger.trace("MessageCreateEvent | command is NULL");
                sendCommandNotFound(e.getChannel(), e.getMessageContent());
                return;
            }

            if (!SupportBot.getInstance().getPermissionManager().hasPermission(e.getServer().get(), e.getMessageAuthor().asUser().get(), command.getPermission())){
                Logger.trace("MessageCreateEvent | user has no permisision");
                sendNoPermission(e.getChannel(), e.getMessageContent());
                return;
            }

            Logger.trace("MessageCreateEvent | execute command \""+command.getCommand()+"\" ...");
            Logger.info("Command \""+messageSplitted[0].substring(1)+"\" executed by \""+e.getMessage().getAuthor().getName()+"\"");
            command.execute(new CommandSender(e.getMessage().getUserAuthor().get(), e.getMessage().getChannel()), args);
        }
    }

    private void sendCommandNotFound(TextChannel textChannel, String command){
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(Color.GRAY);
        embedBuilder.setAuthor(SupportBot.getInstance().getDiscordApi().getYourself());
        embedBuilder.setTimestampToNow();
        if (command.length() > 15){
            command = command.substring(0, 15)+"...";
        }
        embedBuilder.setDescription("**"+command+"**\nIch kenne diesen Befehl nicht " + EmojiParser.parseToUnicode(":face_with_monocle:"));
        MessageTools.deleteMessageLater(textChannel.sendMessage(embedBuilder).join(), 5);
    }

    private void sendNoPermission(TextChannel textChannel, String command){
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(Color.RED);
        embedBuilder.setAuthor(SupportBot.getInstance().getDiscordApi().getYourself());
        embedBuilder.setTimestampToNow();
        if (command.length() > 15){
            command = command.substring(0, 15)+"...";
        }
        embedBuilder.setDescription("**"+command+"**\nDu hast keine Rechte für diesen Befehl! " + EmojiParser.parseToUnicode(":no_entry:"));
        MessageTools.deleteMessageLater(textChannel.sendMessage(embedBuilder).join(), 5);
    }
}