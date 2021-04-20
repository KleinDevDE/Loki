package de.kleindev.loki.objects;

import de.kleindev.loki.utils.MessageTools;
import lombok.Getter;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

@Getter
public class CommandSender {
    private User user;
    private TextChannel textChannel;
    private Message message;
    private Server server;

    public CommandSender(Message message){
        this.user = message.getUserAuthor().get();
        this.textChannel = message.getChannel();
        this.message = message;
        this.server = message.getServer().get();
    }

    public void sendMessageToChannel(String message, int deleteAfterSeconds){
        MessageTools.deleteMessageLater(textChannel.sendMessage(message).join(), deleteAfterSeconds);
    }
    public void sendMessageToChannel(String message){
        textChannel.sendMessage(message);
    }

    public void sendMessageToChannel(EmbedBuilder embedBuilder, int deleteAfterSeconds){
        MessageTools.deleteMessageLater(textChannel.sendMessage(embedBuilder).join(), deleteAfterSeconds);
    }
    public void sendMessageToChannel(EmbedBuilder embedBuilder){
        textChannel.sendMessage(embedBuilder);
    }


    public void sendPrivateMessage(String message){
        if (user.getPrivateChannel().isPresent()){
            user.getPrivateChannel().get().sendMessage(message);
        } else {
            user.openPrivateChannel().join().sendMessage(message);
        }
    }

    public void sendPrivateMessage(EmbedBuilder embedBuilder){
        if (user.getPrivateChannel().isPresent()){
            user.getPrivateChannel().get().sendMessage(embedBuilder);
        } else {
            user.openPrivateChannel().join().sendMessage(embedBuilder);
        }
    }

    //TODO Add things like sendMessageToChannel(String message, int deleteAfterSeconds);
}
