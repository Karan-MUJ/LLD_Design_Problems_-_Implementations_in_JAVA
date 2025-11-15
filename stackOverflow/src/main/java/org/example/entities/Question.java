package org.example.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Question extends Content {
    private final List<Comment> comments;
    private final List<Answer> answers;
    private Answer selectedAnswer;
    private final Set<Tag> tags;
    private String title;

    Question(String body, User author){
        super(body, author);
        comments = new ArrayList<Comment>();
        answers = new ArrayList<Answer>();
        tags = new HashSet<Tag>();
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

    public synchronized void acceptAnswer(Answer answer) {
        if (!this.author.getId().equals(answer.getAuthor().getId()) && this.selectedAnswer == null) {
            this.selectedAnswer = answer;
            answers.add(answer);

        }
    }
}
