package org.example.observer;

import org.example.entities.Event;
import org.example.enums.EventType;

public class ReputationManager implements PostObserver{
    private static final Long QUESTION_UPVOTE_REP = 5L;
    private static final Long ANSWER_UPVOTE_REP = 10L;
    private static final Long ACCEPTED_ANSWER_REP = 15L;
    private static final Long QUESTION_DOWNVOTE_REP = -5L;
    private static final Long ANSWER_DOWNVOTE_REP = -10L;

    @Override
    public void onPostEvent(Event event) {
        switch (event.getEventType()) {
            case EventType.DOWNVOTE_ANSWER -> event.getActor().updateReputation(ANSWER_DOWNVOTE_REP);
            case EventType.DOWNVOTE_QUESTION -> event.getActor().updateReputation(QUESTION_DOWNVOTE_REP);
            case EventType.UPVOTE_ANSWER -> event.getActor().updateReputation(ANSWER_UPVOTE_REP);
            case EventType.UPVOTE_QUESTION -> event.getActor().updateReputation(QUESTION_UPVOTE_REP);
            case EventType.ACCEPTED_ANSWER -> event.getActor().updateReputation(ACCEPTED_ANSWER_REP);
        }
    }
}
