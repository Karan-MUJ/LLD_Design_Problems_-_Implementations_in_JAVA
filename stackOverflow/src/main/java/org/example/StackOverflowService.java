package org.example;

import org.example.entities.*;
import org.example.enums.VoteType;
import org.example.observer.PostObserver;
import org.example.observer.ReputationManager;
import org.example.strategy.SearchStrategy;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class StackOverflowService {
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<String, Question> questions = new ConcurrentHashMap<>();
    private final Map<String, Answer> answers = new ConcurrentHashMap<>();
    private final PostObserver postObserver = new ReputationManager();

    public Question postQuestion(String title, String userId, String body, Set<Tag> tags) {
        Question question = new Question(body, users.get(userId), title, tags);
        question.subscribe(postObserver);
        questions.put(question.getId(), question);
        return question;
    }

    public Answer postAnswer(String body, String userId, String questionId) {
        Answer answer = new Answer(body, users.get(userId));
        questions.get(questionId).addAnswer(answer);
        answer.subscribe(postObserver);
        answers.put(answer.getId(), answer);
        return answer;
    }

    public Comment addComment(String body, String userId, String postId) {
        Comment comment = new Comment(body, users.get(userId));
        questions.get(postId).addComment(comment);
        return comment;
    }

    public void acceptAnswer(String answerId, String questionId) {
        Answer answer = answers.get(answerId);
        questions.get(questionId).acceptAnswer(answer);
        answer.subscribe(postObserver);
    }

    public User registerUser(String userName) {
        User user = new User(userName);
        users.put(user.getId(), user);
        return user;
    }

    public void voteOnPost(User actor, String postId, VoteType voteType) {
        Post post = getPostById(postId);
        User user = users.get(actor.getId());
        post.vote(user, voteType);
    }

    public Post getPostById(String postId) {
        Post post = questions.get(postId);
        if (post == null) {
            post = answers.get(postId);
        }
        return post;
    }

    public List<Question> searchQuestions(List<SearchStrategy> strategy) {
        List<Question> filteredQuestions = questions.values().stream().toList();
        for (SearchStrategy searchStrategy : strategy) {
            filteredQuestions = searchStrategy.filter(filteredQuestions);
        }
        return filteredQuestions;
    }

    public User getUser(String userId) {
        return users.get(userId);
    }
}
