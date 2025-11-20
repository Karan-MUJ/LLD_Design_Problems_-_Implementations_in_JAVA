package org.example.strategy;

import org.example.entities.Question;
import org.example.entities.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserSearchStrategy implements SearchStrategy {
    private final User user;
    public UserSearchStrategy(User user) {
        this.user = user;
    }
    @Override
    public List<Question> filter(List<Question> questions) {
        return questions.stream().filter(question -> {
            return question.getAuthor().equals(user);
        }).collect(Collectors.toList());
    }
}
