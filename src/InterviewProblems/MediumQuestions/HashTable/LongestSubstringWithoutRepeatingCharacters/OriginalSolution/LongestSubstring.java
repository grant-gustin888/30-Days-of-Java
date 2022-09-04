package InterviewProblems.MediumQuestions.HashTable.LongestSubstringWithoutRepeatingCharacters.OriginalSolution;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstring {

    // Let n = the length of the string
    //
    // Time: O(n)
    // --> We must traverse every character in the string once to find the longest
    // substring without repeating characters.
    // Space: O(n)
    // --> We store a hashmap of containing most n character-last index pairs.

    public static void main(String[] args) {
        LongestSubstring longestSubstring = new LongestSubstring();

        // Input: s = ""
        // Output: 0
        String s1 = "";
        int longestSubstring1 = longestSubstring.lengthOfLongestSubstring(s1);
        System.out.println(longestSubstring1);  // 0

        // Input: s = "a"
        // Output: 1
        String s2 = "a";
        int longestSubstring2 = longestSubstring.lengthOfLongestSubstring(s2);
        System.out.println(longestSubstring2);  // 1

        // no unique characters
        // Input: s = "bbbbb"
        // Output: 1
        // Explanation: The answer is "b", with the length of 1.
        String s3 = "bbbbb";
        int longestSubstring3 = longestSubstring.lengthOfLongestSubstring(s3);
        System.out.println(longestSubstring3);  // 1

        // Input: s = "abcabcbb"
        // Output: 3
        // Explanation: The answer is "abc", with the length of 3.
        String s4 = "abcabcbb";
        int longestSubstring4 = longestSubstring.lengthOfLongestSubstring(s4);
        System.out.println(longestSubstring4);  // 3

        // Input: s = "pwwkew"
        // Output: 3
        // Explanation: The answer is "wke", with the length of 3.
        // Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
        String s5 = "pwwkew";
        int longestSubstring5 = longestSubstring.lengthOfLongestSubstring(s5);
        System.out.println(longestSubstring5);  // 3

        // Input: s = "dvdf"
        // Output: 3
        String s6 = "dvdf";
        int longestSubstring6 = longestSubstring.lengthOfLongestSubstring(s6);
        System.out.println(longestSubstring6);  // 3

        // Input: s = "abcdefgh"
        // Output: 8
        String s7 = "abcdefgh";
        int longestSubstring7 = longestSubstring.lengthOfLongestSubstring(s7);
        System.out.println(longestSubstring7);  // 8
    }

    /**
     * Given a string s, find the length of the longest substring without repeating characters.<br><br>
     *
     * Preconditions:<br>
     * - 0 <= s.length <= 5 * 10 ^ 4.<br>
     * - s consists of English letters, digits, symbols and spaces.
     */
    public int lengthOfLongestSubstring(String s) {
        // Take 1. Hashset of characters seen.
        // int startIndex = 0;
        // int endIndex = 0;
        // int longestSubstringLength = 0;  // the length of the longest substring with unique characters
        // Set<Character> currentSubstringLettersUsed = new HashSet<>();
        // while (endIndex < s.length()) {
        //     char currentLetter = s.charAt(endIndex);
        //     if (!currentSubstringLettersUsed.contains(currentLetter)) {
        //         currentSubstringLettersUsed.add(currentLetter);
        //
        //         endIndex++;
        //     } else {  // lettersSeen.contains(currentLetter)
        //         longestSubstringLength = Math.max(longestSubstringLength, currentSubstringLettersUsed.size());
        //         currentSubstringLettersUsed = new HashSet<>();
        //         currentSubstringLettersUsed.add(currentLetter);
        //
        //         startIndex = endIndex;
        //         endIndex++;
        //     }
        // }
        //
        // // but what if the longest length happens to be the entire substring,
        // // or anywhere character in the middle of s until the end of the string s?
        // longestSubstringLength = Math.max(longestSubstringLength, currentSubstringLettersUsed.size());
        //
        // return longestSubstringLength;

        // Take 2. Hashmap of characters to their last indices seen in the string.
        Map<Character, Integer> characterToLastIndices = new HashMap<>();
        int longestSubstringLength = 0;
        int startIndex = 0;
        int endIndex = 0;
        while (endIndex < s.length()) {
            char currentLetter = s.charAt(endIndex);
            if (characterToLastIndices.containsKey(currentLetter)) {
                startIndex = Math.max(startIndex, characterToLastIndices.get(currentLetter) + 1);
            }

            characterToLastIndices.put(currentLetter, endIndex);
            longestSubstringLength = Math.max(longestSubstringLength, endIndex - startIndex + 1);
            endIndex++;
        }
        return longestSubstringLength;
    }
}
