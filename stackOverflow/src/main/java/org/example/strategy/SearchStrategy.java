package org.example.strategy;

import org.example.entities.Question;

import java.util.List;

public interface SearchStrategy {
    List<Question> filter(List<Question> questions);
}
