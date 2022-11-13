package InterviewProblems.MediumQuestions.PriorityQueue.TopKFrequentWords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentWords {

    // Let n = the length of the words array.
    // Let k <= n be the number of most frequent words to return.
    //
    // Time: O(n log n)
    // --> O(n) to build the hashmap of word frequencies
    // --> O(n log n) to build the priority queue of word frequencies
    // --> O(k log n) to extract the top k most frequent words
    // Space: O(n)
    // --> O(n) for the hashmap of word frequencies
    // --> O(n) for the priority queue of word frequencies

    public static void main(String[] args) {
        TopKFrequentWords topKFrequentWords = new TopKFrequentWords();

        // Input: words = ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
        // Output: ["the", "is", "sunny", "day"]
        // Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
        // with the number of occurrence being 4, 3, 2 and 1 respectively.
        String[] words1 = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        List<String> topKFrequentWords1 = topKFrequentWords.topKFrequent(words1, 4);
        System.out.println(topKFrequentWords1);  // [the, is, sunny, day]

        // Input: words = ["i", "love", "leetcode", "i", "love", "coding"], k = 2
        // Output: ["i", "love"]
        // Explanation: "i" and "love" are the two most frequent words.
        // Note that "i" comes before "love" due to a lower alphabetical order.
        String[] words2 = {"i", "love", "leetcode", "i", "love", "coding"};
        List<String> topKFrequentWords2 = topKFrequentWords.topKFrequent(words2, 2);
        System.out.println(topKFrequentWords2);  // [i, love]
    }

    /**
     * Given an array of strings words and an integer k, return the k most
     * frequent strings.
     *
     * Return the answer sorted by the frequency from highest to lowest.
     * Sort the words with the same frequency by their lexicographical order.
     *
     * Preconditions:
     * - 1 <= words.length <= 500
     * - 1 <= words[i].length <= 10
     * - words[i] consists of lowercase English letters.
     * - k is in the range [1, The number of unique words[i]]
     */
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> wordFrequencies = getWordFrequencies(words);

        // add words to maximum priority queue
        PriorityQueue<String> mostFrequentWords = getWordsByFrequency(wordFrequencies);
        for (String word : wordFrequencies.keySet()) {
            mostFrequentWords.add(word);
        }

        // remove words from maximum priority queue
        List<String> topKFrequentWords = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            String mostFrequentWord = mostFrequentWords.poll();
            topKFrequentWords.add(mostFrequentWord);
        }
        return topKFrequentWords;
    }

    private Map<String, Integer> getWordFrequencies(String[] words) {
        Map<String, Integer> wordFrequencies = new HashMap<>();
        for (String word : words) {
            wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1);
        }
        return wordFrequencies;
    }

    private PriorityQueue<String> getWordsByFrequency(Map<String, Integer> wordFrequencies) {
        PriorityQueue<String> mostFrequentWords = new PriorityQueue<>(
            (word1, word2) -> {
                int frequencyOfWord1 = wordFrequencies.get(word1);
                int frequencyOfWord2 = wordFrequencies.get(word2);
                if (frequencyOfWord1 == frequencyOfWord2) {
                    // sort words in lexicographical order.
                    return word1.compareTo(word2);
                } else {
                    // sort frequencies in decreasing order.
                    return frequencyOfWord2 - frequencyOfWord1;
                }
            }
        );
        return mostFrequentWords;
    }
}
