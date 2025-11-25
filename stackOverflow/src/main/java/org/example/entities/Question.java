package org.example.entities;

import org.example.enums.EventType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Question extends Post {
    private final List<Answer> answers;
    private Answer selectedAnswer;
    private final Set<Tag> tags;
    private String title;

    Question(String body, User author, String title, Set<Tag> tags) {
        super(body, author);
        answers = new ArrayList<Answer>();
        this.tags = tags;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void addTag(Tag tag) { tags.add(tag); }

    public synchronized void acceptAnswer(Answer answer) {
        if (!this.author.getId().equals(answer.getAuthor().getId()) && this.selectedAnswer == null) {
            this.selectedAnswer = answer;
            answers.add(answer);
            notifyObservers(new Event(EventType.ACCEPTED_ANSWER, this, answer.getAuthor()));
        }
    }
}
