package InterviewProblems.Arrays.FindAllAnagramsInAString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllAnagramsInAString {

    // Inspiration: https://leetcode.com/submissions/detail/842869060/

    // Let n = s.length(), m = p.length(), where m <= n.
    //
    // Time: O(n)
    // --> O(m) to populate the pLetterFrequencies array.
    // --> O(n - m + 1) because there are n - m + 1 windows of length m in string s,
    // and checking each window against string s for an anagram takes O(1) time.
    // Space: O(n)
    // --> O(1) to create the sLetterFrequencies of size 26.
    // --> O(1) to create the pLetterFrequencies of size 26.
    // --> O(1) to create variables lenS, lenP, startIndex, and endIndex.
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

        int lenS = s.length();
        int lenP = p.length();
        int[] sLetterFrequencies = new int[26];
        int[] pLetterFrequencies = new int[26];
        for (int i = 0; i < lenP; i++) {
            pLetterFrequencies[p.charAt(i) - 'a']++;
        }

        int startIndex = 0;
        int endIndex = 0;
        while (endIndex < lenS) {
            if (endIndex - startIndex + 1 < lenP) {
                sLetterFrequencies[s.charAt(endIndex) - 'a']++;
                endIndex++;
            } else if (endIndex - startIndex + 1 == lenP) {
                sLetterFrequencies[s.charAt(endIndex) - 'a']++;
                if (isAnagram(sLetterFrequencies, pLetterFrequencies)) {
                    allAnagrams.add(startIndex);
                }
                sLetterFrequencies[s.charAt(startIndex) - 'a']--;
                startIndex++;
                endIndex++;
            }
        }
        return allAnagrams;
    }

    private boolean isAnagram(int[] sFreq, int[] pFreq) {
        return Arrays.equals(sFreq, pFreq);
    }
}
