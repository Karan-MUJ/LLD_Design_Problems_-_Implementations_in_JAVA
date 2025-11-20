package org.example;

import org.example.entities.Answer;
import org.example.entities.Question;
import org.example.entities.Tag;
import org.example.entities.User;
import org.example.enums.VoteType;
import org.example.strategy.SearchStrategy;
import org.example.strategy.TagSearchStrategy;
import org.example.strategy.UserSearchStrategy;

import java.util.List;
import java.util.Set;

public class StackOverflowDemo {
    public static void main(String[] args) {
        StackOverflowService service = new StackOverflowService();

        // 1. Create Users
        User alice = service.registerUser("Alice");
        User bob = service.registerUser("Bob");
        User charlie = service.registerUser("Charlie");

        // 2. Alice posts a question
        System.out.println("--- Alice posts a question ---");
        Tag javaTag = new Tag("java");
        Tag designPatternsTag = new Tag("design-patterns");
        Set<Tag> tags = Set.of(javaTag, designPatternsTag);
        Question question = service.postQuestion(alice.getId(), "How to implement Observer Pattern?", "Details about Observer Pattern...", tags);
        System.out.println("alice's question ID: " + question.getId());
        printReputations(alice, bob, charlie);

        // 3. Bob and Charlie post answers
        System.out.println("\n--- Bob and Charlie post answers ---");
        System.out.println("Question id: " + question.getId());
        Answer bobAnswer = service.postAnswer("You can use the java.util.Observer interface.", bob.getId(), question.getId());
        Answer charlieAnswer = service.postAnswer("A better way is to create your own Observer interface.", charlie.getId(), question.getId());
        printReputations(alice, bob, charlie);

        // 4. Voting happens
        System.out.println("\n--- Voting Occurs ---");
        service.voteOnPost(alice, question.getId(), VoteType.UPVOTE); // Alice upvotes her own question
        service.voteOnPost(bob, charlieAnswer.getId(), VoteType.UPVOTE); // Bob upvotes Charlie's answer
        service.voteOnPost(alice, bobAnswer.getId(), VoteType.DOWNVOTE); // Alice downvotes Bob's answer
        printReputations(alice, bob, charlie);

        // 5. Alice accepts Charlie's answer
        System.out.println("\n--- Alice accepts Charlie's answer ---");
        service.acceptAnswer(question.getId(), charlieAnswer.getId());
        printReputations(alice, bob, charlie);

        // 6. Search for questions
        System.out.println("\n--- (C) Combined Search: Questions by 'Alice' with tag 'java' ---");
        List<SearchStrategy> filtersC = List.of(
                new UserSearchStrategy(alice),
                new TagSearchStrategy("javaTag")
        );
        List<Question> searchResults = service.searchQuestions(filtersC);
        searchResults.forEach(q -> System.out.println("  - Found: " + q.getTitle()));
    }

    private static void printReputations(User... users) {
        System.out.println("--- Current Reputations ---");
        for(User user : users) {
            System.out.printf("%s: %d\n", user.getUsername(), user.getReputation());
        }
    }
}