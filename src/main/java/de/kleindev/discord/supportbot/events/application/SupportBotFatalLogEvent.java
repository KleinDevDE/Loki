package de.kleindev.discord.supportbot.events.application;

import de.kleindev.discord.supportbot.events.Event;
import lombok.Getter;

public class SupportBotFatalLogEvent extends Event {
    @Getter private String message;

    public SupportBotFatalLogEvent(String message){
        this.message = message;
    }
}
