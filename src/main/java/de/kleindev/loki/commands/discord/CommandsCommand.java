package de.kleindev.loki.commands.discord;

import de.kleindev.loki.Loki;
import de.kleindev.loki.commands.*;
import de.kleindev.loki.objects.CommandSender;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@CommandInfo(cmd = "commands", description = "List all commands", permission = "*")
public class CommandsCommand extends Command {
    @Override
    public void executeDiscord(CommandSender commandSender, String[] args) {
        Response response = new Response(commandSender.getMessage());
        response.setDeletionTime(10);
        Response.EmbedListBuilder embedListBuilder = response.getEmbedListBuilder("Command", "Beschreibung");
        Stream<Command> commandStream = Loki.getInstance().getCommandManager().getCommands();
        AtomicInteger count = new AtomicInteger();

        commandStream.forEach(command -> {
            count.getAndIncrement();
            embedListBuilder.addLine(command.getCommand(), command.getDescription());
        });
        response = embedListBuilder.build();
        response.setTitle("Commands ("+count+"):");
        response.send(false);
    }
}
