package InterviewProblems.Arrays.RedistributeCharactersToMakeAllStringsEqual;

public class CanRedistributeCharacters {

    // Let n = the number of characters across all words.
    //
    // Time: O(n)
    // --> We must process every character of every word to determine if we can redistribute
    // the characters to make all words equal.
    // Space: O(1)
    // --> We only store records for up to 26 lowercase English letters.

    public static void main(String[] args) {
        CanRedistributeCharacters canRedistributeCharacters = new CanRedistributeCharacters();

        // Input: words = ["a"]
        // Output: true
        String[] words1 = {"a"};
        boolean canRedistributeCharacters1 = canRedistributeCharacters.makeEqual(words1);
        System.out.println(canRedistributeCharacters1);  // true

        // Input: words = ["abc", "aabc", "bc"]
        // Output: true
        // Explanation: Move the first 'a' in words[1] to the front of words[2],
        // to make words[1] = "abc" and words[2] = "abc".
        // All the strings are now equal to "abc", so return true.
        String[] words2 = {"abc", "aabc", "bc"};
        boolean canRedistributeCharacters2 = canRedistributeCharacters.makeEqual(words2);
        System.out.println(canRedistributeCharacters2);  // true

        // Input: words = ["ab", "a"]
        // Output: false
        // Explanation: It is impossible to make all the strings equal using the operation.
        String[] words3 = {"ab", "a"};
        boolean canRedistributeCharacters3 = canRedistributeCharacters.makeEqual(words3);
        System.out.println(canRedistributeCharacters3);  // false

        // Input: words = ["abbccc", "abbccc", "abbccc"]
        // Output: true
        String[] words4 = {"abbccc", "abbccc", "abbccc"};
        boolean canRedistributeCharacters4 = canRedistributeCharacters.makeEqual(words4);
        System.out.println(canRedistributeCharacters4);  // true
    }

    /**
     * You are given an array of strings words (0-indexed).<br><br>
     *
     * In one operation, pick two distinct indices i and j, where words[i] is a non-empty
     * string, and move any character from words[i] to any position in words[j].<br><br>
     *
     * Return true iff you can make every string in words equal using any number of operations.<br><br>
     *
     * Preconditions:<br>
     * - 1 <= words.length <= 100.<br>
     * - 1 <= words[i].length <= 100.<br>
     * - words[i] consists of lowercase English letters.
     */
    public boolean makeEqual(String[] words) {
        int[] charactersToFrequencies = getCharacterCounts(words);
        return compareFrequencies(charactersToFrequencies, words.length);
    }

    private int[] getCharacterCounts(String[] words) {
        int[] charactersToFrequencies = new int[26];
        for (String word : words) {
            for (char c : word.toCharArray()) {
                charactersToFrequencies[c - 'a']++;
            }
        }
        return charactersToFrequencies;
    }

    private boolean compareFrequencies(int[] charactersToFrequencies, int numWords) {
        for (char c = 'a'; c <= 'z'; c++) {
            if (charactersToFrequencies[c - 'a'] % numWords != 0) {
                return false;
            }
        }
        return true;
    }
}
