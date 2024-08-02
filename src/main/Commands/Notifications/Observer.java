package main.Commands.Notifications;

import java.util.LinkedHashMap;

public interface Observer {
    /**
     * Updates the observer.
     *
     * @param notification The notification.
     */
    void update(LinkedHashMap<String, String> notification);
}
