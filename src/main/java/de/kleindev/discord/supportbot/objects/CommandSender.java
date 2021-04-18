package de.kleindev.discord.supportbot.objects;

import lombok.Getter;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.user.User;

@Getter
public class CommandSender {
    private User user;
    private TextChannel textChannel;

    public CommandSender(User user, TextChannel textChannel){
        this.user = user;
        this.textChannel = textChannel;
    }

    //TODO Add things like sendMessageToChannel(String message, int deleteAfterSeconds);
}
