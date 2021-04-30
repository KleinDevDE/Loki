package de.kleindev.loki.commands.discord;

import de.kleindev.loki.Loki;
import de.kleindev.loki.commands.Command;
import de.kleindev.loki.commands.CommandInfo;
import de.kleindev.loki.commands.Response;
import de.kleindev.loki.objects.CommandSender;
import de.kleindev.loki.utils.DevTweaks;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CommandInfo(cmd = "todo", description = "Add things to ur todo")
public class TodoCommand extends Command {
    private HashMap<Long, List<ToDoEntry>> toDoEntryHashMap = new HashMap<>();

    @Override
    public void executeDiscord(CommandSender commandSender, String[] args) {
        if (args.length == 0){
            Response response = new Response(commandSender.getMessage());
            response.setDeletionTime(-1);
            response.setTitle("Your Todo List");

            StringBuilder stringBuilder = new StringBuilder();
            List<ToDoEntry> toDoEntryList = toDoEntryHashMap.getOrDefault(commandSender.getUser().getId(), new ArrayList<>());
            for(int i = 0; i < toDoEntryList.size(); i++){
                ToDoEntry toDoEntry = toDoEntryList.get(i);
                if (toDoEntry.serverID != commandSender.getServer().getId()){
                    continue;
                }
                stringBuilder.append("**").append(i).append("** `").append(toDoEntry.message).append("`\n");
            }
            response.setText(stringBuilder.toString());
            response.send(false);
        } else if (args.length == 1 && args[0].equalsIgnoreCase("clear")){
            List<ToDoEntry> toDoEntryList = toDoEntryHashMap.getOrDefault(commandSender.getUser().getId(), new ArrayList<>());
            List<ToDoEntry> toKeep = new ArrayList<>();
            for (ToDoEntry toDoEntry : toDoEntryList) {
                if (toDoEntry.serverID == commandSender.getServer().getId()) {
                    toKeep.add(toDoEntry);
                }
            }
            toDoEntryHashMap.put(commandSender.getUser().getId(), toKeep);
        } else if (args.length == 2 && args[0].equalsIgnoreCase("del")){
            if (!DevTweaks.isInteger(args[1])){
                Response response = new Response(commandSender);
                response.setColor(Color.RED);
                response.setText("Ungüötiges Argument! \""+args[1]+"\"\n"
                        + "`"+Loki.getInstance().getLokiConfiguration().commandPrefix+"todo del <Nummer>`");
                response.send(true);
                return;
            }

            List<ToDoEntry> toDoEntryList = toDoEntryHashMap.getOrDefault(commandSender.getUser().getId(), new ArrayList<>());

            if (toDoEntryList.size() < (Integer.parseInt(args[1]) -1)){
                Response response = new Response(commandSender);
                response.setColor(Color.RED);
                response.setText("Ungültiges Argument! \""+args[1]+"\"\n"
                        + "`"+Loki.getInstance().getLokiConfiguration().commandPrefix+"todo del <Nummer>`");
                response.send(true);
                return;
            }

            toDoEntryList.remove(Integer.parseInt(args[1]));
            toDoEntryHashMap.put(commandSender.getUser().getId(), toDoEntryList);
        } else {
            String message = String.join(" ", args);
            ToDoEntry toDoEntry = new ToDoEntry();
            toDoEntry.serverID = commandSender.getServer().getId();
            toDoEntry.userID = commandSender.getUser().getId();
            toDoEntry.message = message;
            List<ToDoEntry> toDoEntryList = toDoEntryHashMap.getOrDefault(commandSender.getUser().getId(), new ArrayList<>());
            toDoEntryList.add(toDoEntry);
            toDoEntryHashMap.put(commandSender.getUser().getId(), toDoEntryList);

            Response response = new Response(commandSender);
            response.setColor(Color.GREEN);
            response.setText("TOdo erfolgreich gespeichert!\n\n**Beachte** dass deine ToDos nach einem neustart gelöscht werden.\nEine Dauerhafte Speicherung ist noch in Arbeit");
            response.send(true);
        }
    }

    private static class ToDoEntry{
        public long serverID;
        public long userID;
        public String message;
    }
}
