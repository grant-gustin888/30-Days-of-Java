package InterviewProblems.MediumQuestions.SlidingWindow.PermutationInString.OriginalSolution;

public class PermutationInString {

    // Let m = the length of s1 and n = the length of s2, where m <= n.
    //
    // Time: O(m * n)
    // --> We must examine at most (n - m) windows, and each window requires us to make m comparisons.
    // --> So we make (n - m) * m comparisons between characters.
    // Space: O(m)
    // --> O(m) for creating a new substring of s1 from s2 each time you want to compare anagrams.
    // --> O(1) for all other variables we're storing.

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
        int startIndex = 0;
        int endIndex = s1.length();
        while (endIndex <= s2.length()) {
            if (isAnagram(s1, s2.substring(startIndex, endIndex))) {
                return true;
            }
            startIndex++;
            endIndex++;
        }
        return false;
    }

    private boolean isAnagram(String s1, String s2) {
        int[] characterCountsOfS1 = getCharacterCounts(s1);
        int[] characterCountsOfS2 = getCharacterCounts(s2);
        for (int i = 0; i < 26; i++) {
            if (characterCountsOfS1[i] != characterCountsOfS2[i]) {
                return false;
            }
        }
        return true;
    }

    private int[] getCharacterCounts(String s) {
        int[] characterCounts = new int[26];
        for (char letter : s.toCharArray()) {
            characterCounts[letter - 'a']++;
        }
        return characterCounts;
    }
}
