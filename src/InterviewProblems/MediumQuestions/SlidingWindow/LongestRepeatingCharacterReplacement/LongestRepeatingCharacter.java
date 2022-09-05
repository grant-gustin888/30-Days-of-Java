package InterviewProblems.MediumQuestions.SlidingWindow.LongestRepeatingCharacterReplacement;

public class LongestRepeatingCharacter {

    // Let n = the length of the string.
    //
    // Time: O(n)
    // --> We must traverse the string once, and processing each character in s takes O(1) time.
    // Space: O(1)
    // --> We store an array containing at most 26 elements for the 26 uppercase English letters.

    public static void main(String[] args) {
        LongestRepeatingCharacter longestRepeatingCharacter = new LongestRepeatingCharacter();

        // case 1. Always r - l + 1 - maxFrequency < k.
        // Input: s = "AAAB", k = 2
        // Output: 4
        // Explanation: Replace the last 'B' with an 'A'.
        int longestRepeatingCharacter1 = longestRepeatingCharacter.characterReplacement("AAAB", 2);
        System.out.println(longestRepeatingCharacter1);  // 4

        // case 1. Sometimes r - l + 1 - maxFrequency == k.
        // Input: s = "ABAB", k = 2
        // Output: 4
        // Explanation: Replace the two 'A's with two 'B's or vice versa.
        int longestRepeatingCharacter2 = longestRepeatingCharacter.characterReplacement("ABAB", 2);
        System.out.println(longestRepeatingCharacter2);  // 4

        // case 1. Sometimes r - l + 1 - maxFrequency > k.
        // Input: s = "AABABBA", k = 1
        // Output: 4
        // Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
        // The substring "BBBB" has the longest repeating letters, which is 4.
        int longestRepeatingCharacter3 = longestRepeatingCharacter.characterReplacement("AABABBA", 1);
        System.out.println(longestRepeatingCharacter3);  // 4
    }

    /**
     * You are given a string s and an integer k. You can choose any character of the string and
     * change it to any other uppercase English character. You can perform this operation at most
     * k times.<br><br>
     *
     * Return the length of the longest substring containing the same letter you can get after
     * performing the above operations.<br><br>
     *
     * Preconditions:<br>
     * - 1 <= s.length <= 10 ^ 5.<br>
     * - s consists of only uppercase English letters.<br>
     * - 0 <= k <= s.length.
     */
    public int characterReplacement(String s, int k) {
        int[] lettersToFrequencies = new int[26];
        int longestSubstringLength = 0;
        int maxLetterFrequency = 0;
        int leftIndex = 0;
        for (int rightIndex = 0; rightIndex < s.length(); rightIndex++) {
            lettersToFrequencies[s.charAt(rightIndex) - 'A']++;
            maxLetterFrequency = Math.max(maxLetterFrequency, lettersToFrequencies[s.charAt(rightIndex) - 'A']);
            while ((rightIndex - leftIndex + 1) - maxLetterFrequency > k) {
                lettersToFrequencies[s.charAt(leftIndex) - 'A']--;
                leftIndex++;
            }
            longestSubstringLength = Math.max(longestSubstringLength, rightIndex - leftIndex + 1);
        }
        return longestSubstringLength;
    }
}
