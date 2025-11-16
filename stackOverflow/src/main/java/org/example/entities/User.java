package org.example.entities;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class User {
    private Long id;
    private String username;
    private AtomicLong reputation;

    public User(Long id, String username){
        this.id = id;
        this.username = username;
        reputation = new AtomicLong(0L);
    }

    public Long getId() {
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
}
