package org.example.strategy;

import org.example.entities.Question;

import java.util.List;
import java.util.stream.Collectors;

public class TagSearchStrategy implements SearchStrategy {
    private final String tagName;
    public TagSearchStrategy(String tagName) {
        this.tagName = tagName;
    }
    @Override
    public List<Question> filter(List<Question> questions){
        return questions.stream().filter(question -> {
            return question.getTags().stream().anyMatch(tag -> {
                return tag.getName().equals(tagName);
            });
        }).collect(Collectors.toList());
    }
}
