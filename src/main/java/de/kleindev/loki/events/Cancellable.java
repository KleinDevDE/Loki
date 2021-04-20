package de.kleindev.loki.events;

public interface Cancellable {
    boolean isCancelled();

    void setCancelled(boolean cancel);
}
