package de.kleindev.loki.listeners.discord_internal.message;


import com.vdurmont.emoji.EmojiParser;
import de.kleindev.loki.Loki;
import de.kleindev.loki.commands.AsyncCommand;
import de.kleindev.loki.commands.Command;
import de.kleindev.loki.events.discord.message.MessageCreatedEvent;
import de.kleindev.loki.logging.Logger;
import de.kleindev.loki.objects.CommandSender;
import de.kleindev.loki.utils.MessageTools;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.util.Arrays;

public class AMessageCreateListener implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent e) {
        Loki.getInstance().getEventManager().callEvent(new MessageCreatedEvent(e));

        if (e.getMessageAuthor().isBotUser() || e.getMessageAuthor().isWebhook() || e.getMessageAuthor().isYourself())
            return;

        if (shouldReact(e.getMessage())) {
            String message = getCleanMessage(e.getMessageContent());
            Logger.trace("MessageCreateEvent | message -> " + message);
            String[] messageSplitted = message.split(" ");
            Logger.trace("MessageCreateEvent | messageSplitted -> " + Arrays.toString(messageSplitted));
            Logger.trace("MessageCreateEvent | messageSplitted.length -> " + messageSplitted.length);
            String[] args = new String[messageSplitted.length - 1];
            Logger.trace("MessageCreateEvent | #1 args -> " + Arrays.toString(args));
            Logger.trace("MessageCreateEvent | #1 args.length -> " + args.length);
            System.arraycopy(messageSplitted, 1, args, 0, args.length);
            Logger.trace("MessageCreateEvent | #2 args -> " + Arrays.toString(args));
            Logger.trace("MessageCreateEvent | #2 args.length -> " + args.length);
            String cmdString = startsWithMentionOfBot(e.getMessageContent()) ? messageSplitted[0].toLowerCase() : messageSplitted[0].substring(Loki.getInstance().getLokiConfiguration().commandPrefix.length()).toLowerCase();
            Logger.trace("MessageCreateEvent | cmdString -> " + cmdString);

            Command command = Loki.getInstance().getCommandManager().getCommand(cmdString);
            if (command == null) {
                Logger.trace("MessageCreateEvent | command is NULL");
                sendCommandNotFound(e.getChannel(), e.getMessageContent());
                return;
            }

            if (!Loki.getInstance().getPermissionManager().hasPermission(e.getServer().get(), e.getMessageAuthor().asUser().get(), command.getPermission())) {
                Logger.trace("MessageCreateEvent | user has no permisision");
                sendNoPermission(e.getChannel(), e.getMessageContent());
                return;
            }

            Logger.trace("MessageCreateEvent | execute command \"" + command.getCommand() + "\" ...");
            Logger.info("Command \"" + cmdString + "\" executed by \"" + e.getMessage().getAuthor().getName() + "\"");
            if (command.getClass().isAnnotationPresent(AsyncCommand.class)) {
                Thread thread = new Thread(() -> {
                    command.executeDiscord(new CommandSender(e.getMessage()), args);
                });
                thread.start();
            } else {
                command.executeDiscord(new CommandSender(e.getMessage()), args);
            }

        }
    }

    private void sendCommandNotFound(TextChannel textChannel, String command) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(Color.GRAY);
        embedBuilder.setAuthor(Loki.getInstance().getDiscordApi().getYourself());
        embedBuilder.setTimestampToNow();
        if (command.length() > 15) {
            command = command.substring(0, 15) + "...";
        }
        embedBuilder.setDescription("**" + command + "**\nIch kenne diesen Befehl nicht.. " + EmojiParser.parseToUnicode(":face_with_monocle:"));
        MessageTools.deleteMessageLater(textChannel.sendMessage(embedBuilder).join(), 5);
    }

    private void sendNoPermission(TextChannel textChannel, String command) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setColor(Color.RED);
        embedBuilder.setAuthor(Loki.getInstance().getDiscordApi().getYourself());
        embedBuilder.setTimestampToNow();
        if (command.length() > 15) {
            command = command.substring(0, 15) + "...";
        }
        embedBuilder.setDescription("**" + command + "**\nDu hast keine Rechte für diesen Befehl! " + EmojiParser.parseToUnicode(":no_entry:"));
        MessageTools.deleteMessageLater(textChannel.sendMessage(embedBuilder).join(), 5);
    }

    private boolean shouldReact(Message message) {
        if (!Loki.getInstance().getLokiConfiguration().commandPrefix.equals("") && message.getContent().startsWith(Loki.getInstance().getLokiConfiguration().commandPrefix)) {
            Logger.trace("MessageCreateEvent | Starts with command prefix");
            return true;
        } else if (startsWithMentionOfBot(message.getContent())) {
            Logger.trace("MessageCreateEvent | Starts with mention of bot");
            return true;
        }

        return false;
    }

    private boolean startsWithMentionOfBot(String string) {
        return string.startsWith(Loki.getInstance().getDiscordApi().getYourself().getMentionTag()) || string.startsWith(Loki.getInstance().getDiscordApi().getYourself().getNicknameMentionTag());
    }

    private String getCleanMessage(String string) {
        return string
                .replace(Loki.getInstance().getDiscordApi().getYourself().getMentionTag(), "")
                .replace(Loki.getInstance().getDiscordApi().getYourself().getNicknameMentionTag(), "")
                .replaceFirst("^\\s*", "");
    }
}