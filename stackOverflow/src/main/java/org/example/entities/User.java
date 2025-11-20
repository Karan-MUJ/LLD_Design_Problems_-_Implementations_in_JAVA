package org.example.entities;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class User {
    private String id;
    private String username;
    private AtomicLong reputation;

    public User(String username){
        this.id = UUID.randomUUID().toString();
        this.username = username;
        reputation = new AtomicLong(0L);
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void updateReputation(Long changeValueBy) {
        reputation.addAndGet(changeValueBy);
    }

    public Long getReputation() { return reputation.get(); }
}
