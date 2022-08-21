package InterviewProblems.Hashmaps.BestPokerHand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BestPokerHand {

    // Time: O(1)
    // --> O(1) to create both hashmaps
    // --> O(1) to determine the best poker hand
    // Space: O(1)
    // --> We only need to store two hashmaps, both containing O(1) entries.

    public static void main(String[] args) {
        BestPokerHand bestPokerHand = new BestPokerHand();

        // Input: ranks = [1, 2, 3, 4, 5], suits = ["a", "b", "c", "d", "a"]
        // Output: "High Card"
        int[] ranks1 = {1, 2, 3, 4, 5};
        char[] suits1 = {'a', 'b', 'c', 'd', 'a'};
        String bestPokerHand1 = bestPokerHand.bestHand(ranks1, suits1);
        System.out.println(bestPokerHand1);  // "High Card"

        // Input: ranks = [10, 10, 2, 12, 9], suits = ["a", "b", "c", "a", "d"]
        // Output: "Pair"
        // Explanation: The hand with the first and second card consists of 2 cards with the same rank,
        // so we have a "Pair".
        // Note that we cannot make a "Flush" or a "Three of a Kind".
        int[] ranks2 = {10, 10, 2, 12, 9};
        char[] suits2 = {'a', 'b', 'c', 'a', 'd'};
        String bestPokerHand2 = bestPokerHand.bestHand(ranks2, suits2);
        System.out.println(bestPokerHand2);  // "Pair"

        // Input: ranks = [4, 4, 2, 4, 4], suits = ["d", "a", "a", "b", "c"]
        // Output: "Three of a Kind"
        // Explanation: The hand with the first, second, and fourth card consists of 3 cards with the
        // same rank, so we have a "Three of a Kind".
        // Note that we could also make a "Pair" hand but "Three of a Kind" is a better hand.
        // Also note that other cards could be used to make the "Three of a Kind" hand.
        int[] ranks3 = {4, 4, 2, 4, 4};
        char[] suits3 = {'d', 'a', 'a', 'b', 'c'};
        String bestPokerHand3 = bestPokerHand.bestHand(ranks3, suits3);
        System.out.println(bestPokerHand3);  // "Three of a Kind"

        // Input: ranks = [13, 2, 3, 1, 9], suits = ["a", "a", "a", "a", "a"]
        // Output: "Flush"
        // Explanation: The hand with all the cards consists of 5 cards with the same suit, so we
        // have a "Flush".
        // Note that we cannot make a "Three of a Kind" or a "Pair".
        int[] ranks4 = {13, 2, 3, 1, 9};
        char[] suits4 = {'a', 'a', 'a', 'a', 'a'};
        String bestPokerHand4 = bestPokerHand.bestHand(ranks4, suits4);
        System.out.println(bestPokerHand4);  // "Flush"
    }

    /**
     * You are given an integer array ranks and a character array suits.<br>
     * You have 5 cards where the ith card has a rank of ranks[i] and a suit of suits[i].<br><br>
     *
     * The following are the types of poker hands you can make from best to worst:<br>
     * 1. "Flush": Five cards of the same suit.<br>
     * 2. "Three of a Kind": Three cards of the same rank.<br>
     * 3. "Pair": Two cards of the same rank.<br>
     * 4. "High Card": Any single card.<br><br>
     *
     * Return a string representing the best type of poker hand you can make with the given cards.<br><br>
     *
     * Note that the return values are case-sensitive.<br><br>
     *
     * Preconditions:<br>
     * - ranks.length == suits.length == 5.<br>
     * - 1 <= ranks[i] <= 13.<br>
     * - 'a' <= suits[i] <= 'd'.<br>
     * - No two cards have the same rank and suit.
     */
    public String bestHand(int[] ranks, char[] suits) {
        Map<Integer, List<Character>> ranksToSuits = populateRanksToSuits(ranks, suits);
        Map<Character, List<Integer>> suitsToRanks = populateSuitsToRanks(ranks, suits);
        if (isFlush(suitsToRanks)) {
            return "Flush";
        } else if (isThreeOfAKind(ranksToSuits)) {
            return "Three of a Kind";
        } else if (isPair(ranksToSuits)) {
            return "Pair";
        } else {
            return "High Card";
        }
    }

    private Map<Integer, List<Character>> populateRanksToSuits(int[] ranks, char[] suits) {
        Map<Integer, List<Character>> ranksToSuits = new HashMap<>();
        for (int i = 0; i < ranks.length; i++) {
            int rank = ranks[i];
            char suit = suits[i];
            if (ranksToSuits.containsKey(rank)) {
                ranksToSuits.get(rank).add(suit);
            } else {
                List<Character> suitsArray = new ArrayList<>();
                suitsArray.add(suit);
                ranksToSuits.put(rank, suitsArray);
            }
        }
        return ranksToSuits;
    }

    private Map<Character, List<Integer>> populateSuitsToRanks(int[] ranks, char[] suits) {
        Map<Character, List<Integer>> suitsToRanks = new HashMap<>();
        for (int i = 0; i < ranks.length; i++) {
            int rank = ranks[i];
            char suit = suits[i];
            if (suitsToRanks.containsKey(suit)) {
                suitsToRanks.get(suit).add(rank);
            } else {
                List<Integer> ranksArray = new ArrayList<>();
                ranksArray.add(rank);
                suitsToRanks.put(suit, ranksArray);
            }
        }
        return suitsToRanks;
    }

    private boolean isFlush(Map<Character, List<Integer>> suitsToRanks) {
        return suitsToRanks.size() == 1 && suitsToRanks.values().iterator().next().size() == 5;
    }

    private boolean isThreeOfAKind(Map<Integer, List<Character>> ranksToSuits) {
        for (Map.Entry<Integer, List<Character>> entry : ranksToSuits.entrySet()) {
            if (entry.getValue().size() >= 3) {
                return true;
            }
        }
        return false;
    }

    private boolean isPair(Map<Integer, List<Character>> ranksToSuits) {
        for (Map.Entry<Integer, List<Character>> entry : ranksToSuits.entrySet()) {
            if (entry.getValue().size() == 2) {
                return true;
            }
        }
        return false;
    }
}
