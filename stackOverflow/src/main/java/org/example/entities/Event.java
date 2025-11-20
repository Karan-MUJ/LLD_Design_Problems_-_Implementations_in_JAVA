package org.example.entities;

import org.example.enums.EventType;

public class Event {
    private final EventType eventType;
    private final Post targetPost;
    private final User actor;
    public Event(EventType eventType, Post targetPost, User actor) {
        this.eventType = eventType;
        this.targetPost = targetPost;
        this.actor = actor;
    }
    public EventType getEventType() {
        return eventType;
    }
    public Post getTargetPost() {
        return targetPost;
    }
    public User getActor() {
        return actor;
    }
}
