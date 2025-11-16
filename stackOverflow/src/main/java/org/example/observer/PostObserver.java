package org.example.observer;

import org.example.entities.Event;
import org.example.entities.Post;
import org.example.entities.User;

public interface PostObserver {
    public void onPostEvent(Event event);
}
