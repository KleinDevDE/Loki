package de.kleindev.loki.events.application;

import de.kleindev.loki.events.Event;
import lombok.Getter;

public class SupportBotFatalLogEvent extends Event {
    @Getter
    private String message;

    public SupportBotFatalLogEvent(String message) {
        this.message = message;
    }
}
