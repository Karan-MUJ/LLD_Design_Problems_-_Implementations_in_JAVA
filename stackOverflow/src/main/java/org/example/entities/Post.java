package org.example.entities;

import org.example.enums.EventType;
import org.example.enums.VoteType;
import org.example.observer.PostObserver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Post extends Content{
    private final AtomicInteger votes;
    private final List<PostObserver> observers;
    private final Map<User, VoteType> voters;
    private final List<Comment> comments;
    public Post(String body, User author) {
        super(body, author);
        voters = new ConcurrentHashMap<User, VoteType>();
        votes = new AtomicInteger(0);
        observers = new CopyOnWriteArrayList<PostObserver>();
        comments = new CopyOnWriteArrayList<Comment>();
    }
    public void subscribe(PostObserver oberver) {
        observers.add(oberver);
    }
    public void unsubscribe(PostObserver observer) {
        observers.remove(observer);
    }
    public void notifyObservers(Event event) {
        for (PostObserver observer: observers) {
            observer.onPostEvent(event);
        }
    }
    public synchronized void vote(User user, VoteType vote) {
        if (voters.get(user) == vote) {
            return;
        }
        int scoreChange = 0;
        if (voters.containsKey(user)) {
            scoreChange = voters.get(user) == VoteType.DOWNVOTE? 2: -2;
        } else {
            scoreChange = vote == VoteType.UPVOTE? 1: -1;
        }
        voters.put(user, vote);
        votes.addAndGet(scoreChange);
        EventType eventType = null;
        if (this instanceof Question) {
            eventType = vote.equals(VoteType.UPVOTE)? EventType.UPVOTE_QUESTION: EventType.DOWNVOTE_QUESTION;
        } else {
            eventType = vote.equals(VoteType.UPVOTE)? EventType.UPVOTE_ANSWER: EventType.DOWNVOTE_ANSWER;
        }
        notifyObservers(new Event(eventType, this, user));
    }
}
