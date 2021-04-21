package de.kleindev.loki.commands.discord;

import de.kleindev.loki.commands.*;
import de.kleindev.loki.objects.CommandSender;
import org.javacord.api.entity.user.User;

import java.text.SimpleDateFormat;
import java.util.List;

@CommandInfo(cmd = "userinfo", description = "Get informations about an user", permission = "*")
public class UserInfoCommand extends Command {
    private final SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    @Override
    public void executeDiscord(CommandSender commandSender, String[] args) {
        List<User> mentionedUsers = commandSender.getMessage().getMentionedUsers();
        if (mentionedUsers.size() == 0){
            printUserInfo(commandSender, commandSender.getUser());
            return;
        }

        for (User user : mentionedUsers){
            if (user.isYourself() && mentionedUsers.size() != 1){
                continue;
            }
            printUserInfo(commandSender, user);
        }
    }

    private void printUserInfo(CommandSender commandSender, User user){
        Response response = new Response(commandSender.getMessage());
        response.setTitle("Informationen von " + user.getDisplayName(commandSender.getServer()));
        response.setThumbnail(user.getAvatar());
        response.addField("**Discord ID**", String.valueOf(user.getId()), true);
        response.addField("**Discord Nutzer seit**",  getRegisteredSince(user), true);
        response.setDeletionTime(20);
        response.send(true);
    }

    private String getRegisteredSince(User user){
        String binary = "00" + Long.toBinaryString(user.getId()); //DiscordID to binary
        String subString = binary.substring(0, 38);
        long temp_time = Long.parseLong(subString,2); //Convert first 38 chars from binary to long
        long time = temp_time + 1420070400000L; // Milliseconds since Discord Epoch, the first second of 2015
        final long finalTime = time;
        return SDF.format(finalTime);
    }
}
