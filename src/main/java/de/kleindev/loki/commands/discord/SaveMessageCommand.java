package de.kleindev.loki.commands.discord;

import de.kleindev.loki.commands.Command;
import de.kleindev.loki.commands.CommandInfo;
import de.kleindev.loki.objects.CommandSender;
import de.kleindev.loki.utils.DevTweaks;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageSet;
import org.javacord.api.entity.user.User;

@CommandInfo(cmd = "savemessage", description = "Save an message before it get's deleted by the user itself.", aliase = {"save", "sm"})
public class SaveMessageCommand extends Command {
    @Override
    public void executeDiscord(CommandSender commandSender, String[] args) {
        // savemessage <User-Mention> (saves the latest message of user X)
        // savemessage <Link-to-message> (saves message which link are redirects to)
        // savemessage [as replie on the specific message] (save the message which the command replied to)

        if (commandSender.getMessage().getReferencedMessage().isPresent()){
            saveMessage(commandSender.getMessage().getReferencedMessage().get());
            return;
        }

        if (args.length == 1){
            if (DevTweaks.isValidURL(args[0])){
                //TODO parse message


            } else {
                User mentionedUSer = commandSender.getMessage().getMentionedUsers().get(commandSender.getMessage().getMentionedUsers().size()-1);
                if (mentionedUSer.isYourself()){
                    return;
                }

                MessageSet messages = commandSender.getTextChannel().getMessages(100).join();
                messages.forEach(message -> {
                    if (message.getAuthor().getId() == mentionedUSer.getId()){
                        saveMessage(message);
                        return;
                    }
                });
            }
        }
    }

    private void saveMessage(Message message){
        message.toMessageBuilder().send(message.getChannel());
    }
}
