package de.kleindev.discord.supportbot.listeners.discord_internal.message;


import com.vdurmont.emoji.EmojiParser;
import de.kleindev.discord.supportbot.SupportBot;
import de.kleindev.discord.supportbot.commands.Command;
import de.kleindev.discord.supportbot.events.discord.message.MessageEditedEvent;
import de.kleindev.discord.supportbot.logging.Logger;
import de.kleindev.discord.supportbot.objects.CommandSender;
import de.kleindev.discord.supportbot.utils.MessageTools;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageEditEvent;
import org.javacord.api.listener.message.MessageEditListener;

import java.awt.*;

public class AMessageEditListener implements MessageEditListener {
    @Override
    public void onMessageEdit(MessageEditEvent e) {
        SupportBot.getInstance().getEventManager().callEvent(new MessageEditedEvent(e));


        if (e.getMessage().isEmpty() || e.getMessageAuthor().isEmpty() || e.getMessageContent().isPresent())
            return;
        if (e.getMessageAuthor().get().isBotUser() || e.getMessageAuthor().get().isWebhook() || e.getMessageAuthor().get().isYourself())
            return;
        if (e.getMessage().get().getCreationTimestamp().toEpochMilli() > (System.currentTimeMillis() + 7000))
            return;

        //TODO Check for spam
        if (e.getMessage().get().getReadableContent().startsWith(SupportBot.getInstance().getSupportBotConfiguration().commandPrefix)){
            String[] messageSplitted = e.getMessage().get().getReadableContent().split(" ");
            String[] args = new String[messageSplitted.length - 1];
            System.arraycopy(messageSplitted, 1, args, 0, args.length);
            Command command = SupportBot.getInstance().getCommandManager().getCommand(messageSplitted[0]);
            if (command == null){
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setColor(Color.GRAY);
                embedBuilder.setAuthor(SupportBot.getInstance().getDiscordApi().getYourself());
                embedBuilder.setTimestampToNow();
                embedBuilder.setDescription("\""+e.getMessage().get().getReadableContent().substring(0, 15)+"...\"\nIch kenne diesen Befehl nicht " + EmojiParser.parseToUnicode(":face_with_monocle:"));
                MessageTools.deleteMessageLater(e.getMessage().get().getChannel().sendMessage(embedBuilder).join(), 5000);
                return;
            }
            Logger.info("Command \""+messageSplitted[0]+"\" executed by \""+e.getMessage().get().getAuthor().getName()+"\"");
            command.execute(new CommandSender(e.getMessage().get().getUserAuthor().get(), e.getMessage().get().getChannel()), args);
        }
    }
}