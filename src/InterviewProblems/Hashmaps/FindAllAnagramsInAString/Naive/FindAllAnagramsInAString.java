package InterviewProblems.ACurrentlyDoingTheseQuestions.FindAllAnagramsInAString.Naive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllAnagramsInAString {

    // Let n = s.length(), m = p.length(), where m <= n.
    //
    // Time: O((n - m + 1) * (2m + n)) = O(n ^ 2)
    // --> There are n - m + 1 windows.
    // --> it takes O(m + n) per window to build the hashmap of the window's characters.
    // --> it takes O(m) per window to build the hashmap of the anagram's characters.
    // --> so it takes O(2m + n) per window to check all m characters to see if the window
    // is an anagram of p.
    // Space: O(m + n)
    // --> We must store up to m unique characters in p,
    // and up to n unique characters in s.

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
        int startIndex = 0;
        int endIndex = startIndex + p.length();
        List<Integer> allAnagrams = new ArrayList<>();
        while (endIndex <= s.length()) {
            String substring = s.substring(startIndex, endIndex);
            if (isWordAnAnagram(substring, p)) {
                allAnagrams.add(startIndex);
            }

            // slide window...
            startIndex++;
            endIndex++;
        }
        return allAnagrams;
    }

    private boolean isWordAnAnagram(String substring, String targetSubstring) {
        Map<Character, Integer> substringCharCounts = getCharacterCounts(substring);
        Map<Character, Integer> targetSubstringCharCounts = getCharacterCounts(targetSubstring);
        for (Map.Entry<Character, Integer> entry : substringCharCounts.entrySet()) {
            Character character = entry.getKey();
            Integer count = entry.getValue();
            if (!targetSubstringCharCounts.containsKey(character) || targetSubstringCharCounts.get(character) != count) {
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
