package InterviewProblems.HashMapProblems.IsAnagram;

import java.util.HashMap;

class IsAnagram {

    public static void main(String[] args) {
        System.out.println(isAnagram("race", "care"));  // true
        System.out.println(isAnagram("deer", "dare"));  // false
    }

    /**
     * Return true iff word1 is an anagram of word2.
     *
     * word1 is an anagram of word2 iff the letters of word1 can be rearranged to
     * form word2.
     *
     * Precondition: word1 and word2 are dictionary English words.
     */
    private static boolean isAnagram(String word1, String word2) {
        // count the number of occurrences of each letter in word1 and word2,
        // and check if they are all equal.
        HashMap<Character, Integer> letterCountsOfWord1 = countLettersInWord(word1);
        HashMap<Character, Integer> letterCountsOfWord2 = countLettersInWord(word2);
        return letterCountsOfWord1.equals(letterCountsOfWord2);
    }

    private static HashMap<Character, Integer> countLettersInWord(String word) {
        HashMap<Character, Integer> letterCounts = new HashMap<>();
        for (char character : word.toCharArray()) {
            if (!letterCounts.containsKey(character)) {
                letterCounts.put(character, 1);
            } else {
                letterCounts.put(character, letterCounts.get(character) + 1);
            }
        }
        return letterCounts;
    }
}