package org.example.entities;

import java.util.ArrayList;
import java.util.List;

public class Answer extends Post {
    Boolean isAccepted;
    public Answer(String body, User author) {
        super(body, author);
        isAccepted = false;
    }
    public void setIsAccepted(Boolean isAccepted) {
        this.isAccepted = isAccepted;
    }
    public Boolean getIsAccepted() {
        return isAccepted;
    }
}
