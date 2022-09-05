package InterviewProblems.MediumQuestions.SlidingWindow.PermutationInString.OptimizedSolution;

public class PermutationInString {

    // Let m = the length of s1 and n = the length of s2, where m <= n.
    //
    // Time: O(n)
    // --> We must traverse the entire string s2 once to get the character counts of s2.
    // --> The sliding window ensures that we only visit each character in s2 exactly once.
    // Space: O(1)
    // --> O(1) for creating a new substring of s1 from s2 each time you want to compare
    // anagrams, using at most 26 lowercase English letters.
    // --> O(1) for the two arrays of size 26, representing s1 and s2 character counts.
    // --> O(1) for all other variables we're storing, including the matches variable.

    public static void main(String[] args) {
        PermutationInString permutationInString = new PermutationInString();

        // Inputs: s1 = "ab", s2 = "eidabooo"
        // Output: true
        boolean permutationInString1 = permutationInString.checkInclusion("ab", "eidabooo");
        System.out.println(permutationInString1);  // true

        // Inputs: s1 = "ab", s2 = "eidbaooo"
        // Output: true
        boolean permutationInString2 = permutationInString.checkInclusion("ab", "eidbaooo");
        System.out.println(permutationInString2);  // true

        // Inputs: s1 = "ab", s2 = "eidboaoo"
        // Output: false
        boolean permutationInString3 = permutationInString.checkInclusion("ab", "eidboaoo");
        System.out.println(permutationInString3);  // false

        // Inputs: s1 = "adc", s2 = "dcda"
        // Output: true
        boolean permutationInString4 = permutationInString.checkInclusion("adc", "dcda");
        System.out.println(permutationInString4);  // true

        // Inputs: s1 = "ab", s2 = "a"
        // Output: false
        boolean permutationInString5 = permutationInString.checkInclusion("ab", "a");
        System.out.println(permutationInString5);  // false
    }

    /**
     * Given two strings s1 and s2, return true iff s2 contains a permutation of s1.<br>
     * In other words, return true if one of s1's permutations is the substring of s2.<br><br>
     *
     * Preconditions:<br>
     * - 1 <= s1.length, s2.length <= 10 ^ 4.<br>
     * - s1 and s2 consist of lowercase English letters.
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] characterCountsOfS1 = getCharacterCounts(s1);
        int[] characterCountsOfS2 = getCharacterCounts(s2.substring(0, s1.length()));

        int numMatches = 0;
        for (int i = 0; i < 26; i++) {
            if (characterCountsOfS1[i] == characterCountsOfS2[i]) {
                numMatches++;
            }
        }

        int leftIndex = 0;
        for (int rightIndex = s1.length(); rightIndex < s2.length(); rightIndex++) {
            if (numMatches == 26) {
                return true;
            }

            // update numMatches after visiting character at index rightIndex.
            int index = s2.charAt(rightIndex) - 'a';
            characterCountsOfS2[index]++;
            if (characterCountsOfS1[index] == characterCountsOfS2[index]) {
                // introduced matching character counts
                numMatches++;
            } else if (characterCountsOfS1[index] + 1 == characterCountsOfS2[index]) {
                // introduced mismatching character counts
                numMatches--;
            }

            index = s2.charAt(leftIndex) - 'a';
            characterCountsOfS2[index]--;
            if (characterCountsOfS1[index] == characterCountsOfS2[index]) {
                // introduced matching character counts
                numMatches++;
            } else if (characterCountsOfS1[index] - 1 == characterCountsOfS2[index]) {
                // introduced mismatching character counts
                numMatches--;
            }

            leftIndex++;
        }
        return numMatches == 26;
    }

    private int[] getCharacterCounts(String s) {
        int[] characterCounts = new int[26];
        for (char letter : s.toCharArray()) {
            characterCounts[letter - 'a']++;
        }
        return characterCounts;
    }
}
