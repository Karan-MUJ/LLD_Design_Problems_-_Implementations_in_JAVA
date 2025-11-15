package org.example.entities;

import java.util.Date;
import java.util.UUID;

public class Content {
    protected final String id;
    protected String body;
    protected final User author;
    protected final Date createdAt;

    public Content(String body, User author){
        this.body = body;
        this.author = author;
        this.createdAt = new Date();
        this.id = UUID.randomUUID().toString();
    }
    public String getBody() {
        return body;
    }
    public String getId() {
        return id;
    }
    public User getAuthor() {
        return author;
    }
}
