package de.kleindev.discord.supportbot.events;

public interface Cancellable {
    boolean isCancelled();

    void setCancelled(boolean cancel);
}
