package de.kleindev.loki.utils;

import de.kleindev.loki.Loki;
import de.kleindev.loki.logging.Logger;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;

import java.util.ArrayList;

public class MessageTools {
    private static ArrayList<Long> messagesWhoDontNeedToBeLogged = new ArrayList<>();

    static {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5184000);
                    Logger.debug("Clearing \"messagesWhoDontNeedToBeLogged\" list...");
                    messagesWhoDontNeedToBeLogged.clear();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
		}).start();
	}
	
    public static void deleteMessageLater(TextChannel channel, long messageID, long seconds){
        if(seconds <= -1)
            return;
        messagesWhoDontNeedToBeLogged.add(messageID);
        new Thread(() -> {
            try {
                if(channel.getId() == 585562006023569418L)
                    return;
                Thread.sleep(1000*seconds);
                Loki.getInstance().getDiscordApi().getMessageById(messageID, channel).join().delete();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void deleteMessageLater(Message message, long seconds){
        if(seconds <= -1)
            return;
        messagesWhoDontNeedToBeLogged.add(message.getId());
        new Thread(() -> {
            try {
                Thread.sleep(1000*seconds);
                message.getMessageDeleteListeners().forEach(message::removeMessageAttachableListener);
                message.getMessageEditListeners().forEach(message::removeMessageAttachableListener);
                message.getCachedMessagePinListeners().forEach(message::removeMessageAttachableListener);
                message.getCachedMessageUnpinListeners().forEach(message::removeMessageAttachableListener);
                message.getReactionAddListeners().forEach(message::removeMessageAttachableListener);
                message.getReactionRemoveListeners().forEach(message::removeMessageAttachableListener);
                message.getReactionRemoveAllListeners().forEach(message::removeMessageAttachableListener);
                message.delete();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static String getMessageURL(Message message){
        // Server https://discord.com/channels/787468489484271640/829113809054072882/835284423729217556
        // Private https://discord.com/channels/@me/792847931341537310/835474579504889866

        if (message.isServerMessage()){
            return "https://discord.com/channels/"+message.getServer().get().getId()+"/"+message.getChannel().getId()+"/"+message.getIdAsString();
        } else {
            return "https://discord.com/channels/@me/"+message.getChannel().getId()+"/"+message.getIdAsString();
        }
    }

    public static Message getMessageByURL(String url){
        return null;
    }
    
    public static void preventMessageFromDeleteLogging(long messageID) {
    	messagesWhoDontNeedToBeLogged.add(messageID);
    }
    
    public static void preventMessageFromDeleteLogging(Message message) {
    	preventMessageFromDeleteLogging(message.getId());
    }
    
    public static boolean isMessageDeletionIgnored(long messageID) {
    	return messagesWhoDontNeedToBeLogged.contains(messageID);
    }
    
    public static boolean isMessageDeletionIgnored(Message message) {
    	return isMessageDeletionIgnored(message.getId());
    }
    
    public static void removeMessageFromDeletionLogging(long messageID) {
    	messagesWhoDontNeedToBeLogged.remove(messageID);
    }
    
    public static void removeMessageFromDeletionLogging(Message message) {
    	removeMessageFromDeletionLogging(message.getId());
    }
}
