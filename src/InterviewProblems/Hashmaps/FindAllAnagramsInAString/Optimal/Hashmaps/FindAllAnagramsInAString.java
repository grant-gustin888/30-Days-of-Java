package InterviewProblems.ACurrentlyDoingTheseQuestions.FindAllAnagramsInAString.Optimal.Hashmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllAnagramsInAString {

    // Let n = s.length(), m = p.length(), where m <= n.
    //
    // Time: O(n)
    // --> O(m) for extracting the substring given by p
    // --> O(m) to get hashmap of character counts for string s
    // --> O(n) to get hashmap of character counts for substring p
    // --> O(n - m + 1) because there are n - m + 1 windows of length m in string s,
    // and checking each window against string s for an anagram takes O(1) time.
    // Space: O(n)
    // --> O(1) to create the pCharacterCounts of up to size 26.
    // --> O(1) to create the substringCharacterCounts of up to size 26.
    // --> O(1) to create variables startIndex, and endIndex.
    // --> O(n - m + 1) = O(n) to store up to n - m + 1 anagrams.

    public static void main(String[] args) {
        FindAllAnagramsInAString findAllAnagramsInAString = new FindAllAnagramsInAString();

        // Input: s = "cbaebabacd", p = "abc"
        // Output: [0, 6]
        // Explanation:
        // The substring with start index = 0 is "cba", which is an anagram of "abc".
        // The substring with start index = 6 is "bac", which is an anagram of "abc".
        String s1 = "cbaebabacd";
        String p1 = "abc";
        List<Integer> anagrams1 = findAllAnagramsInAString.findAnagrams(s1, p1);
        System.out.println(anagrams1);  // [0, 6]

        // Input: s = "abab", p = "ab"
        // Output: [0, 1, 2]
        // Explanation:
        // The substring with start index = 0 is "ab", which is an anagram of "ab".
        // The substring with start index = 1 is "ba", which is an anagram of "ab".
        // The substring with start index = 2 is "ab", which is an anagram of "ab".
        String s2 = "abab";
        String p2 = "ab";
        List<Integer> anagrams2 = findAllAnagramsInAString.findAnagrams(s2, p2);
        System.out.println(anagrams2);  // [0, 1, 2]

        // Input: s = "ab", p = "abab"
        // Output: []
        String s3 = "ab";
        String p3 = "abab";
        List<Integer> anagrams3 = findAllAnagramsInAString.findAnagrams(s3, p3);
        System.out.println(anagrams3);  // []
    }

    /**
     * Given two strings s and p, return an array of all the start indices of p's anagrams
     * in s. You may return the answer in any order.
     *
     * An anagram is a word or phrase formed by rearranging the letters of a different word
     * or phrase, typically using all the original letters exactly once.
     *
     * Preconditions:
     * - 1 <= s.length, p.length <= 3 * 104
     * - s and p consist of lowercase English letters.
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> allAnagrams = new ArrayList<>();
        if (p.length() > s.length()) {
            return allAnagrams;
        }

        int startIndex = 0;
        int endIndex = startIndex + p.length();
        Map<Character, Integer> pCharacterCounts = getCharacterCounts(p);
        String substring = s.substring(startIndex, endIndex);
        Map<Character, Integer> substringCharacterCounts = getCharacterCounts(substring);

        while (endIndex <= s.length()) {
            if (isAnagram(pCharacterCounts, substringCharacterCounts)) {
                allAnagrams.add(startIndex);
            }

            // move sliding window over by 1
            if (endIndex < s.length()) {
                char startChar = s.charAt(startIndex);
                char endChar = s.charAt(endIndex);
                substringCharacterCounts.put(startChar, substringCharacterCounts.getOrDefault(startChar, 0) - 1);
                substringCharacterCounts.put(endChar, substringCharacterCounts.getOrDefault(endChar, 0) + 1);
                startIndex++;
                endIndex++;
            } else {
                break;
            }
        }
        return allAnagrams;
    }

    private boolean isAnagram(Map<Character, Integer> pCharacterCounts, Map<Character, Integer> substringCharacterCounts) {
        for (Map.Entry<Character, Integer> entry : pCharacterCounts.entrySet()) {
            char character = entry.getKey();
            int count = entry.getValue();
            if (substringCharacterCounts.getOrDefault(character, 0) != count) {
                return false;
            }
        }
        return true;
    }

    private Map<Character, Integer> getCharacterCounts(String s) {
        Map<Character, Integer> characterCounts = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (characterCounts.containsKey(c)) {
                characterCounts.put(c, characterCounts.get(c) + 1);
            } else {
                characterCounts.put(c, 1);
            }
        }
        return characterCounts;
    }
}
