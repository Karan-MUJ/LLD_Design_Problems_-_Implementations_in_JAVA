package org.example.entities;

import java.util.ArrayList;
import java.util.List;

public class Answer extends Content {
    private final List<Comment> comments;
    Answer(String body, User author) {
        super(body, author);
        comments = new ArrayList<Comment>();
    }
}
