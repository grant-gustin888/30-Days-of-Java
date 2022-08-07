package InterviewProblems.MediumQuestions.DynamicProgramming.CountSortedVowelStrings.MemoizedSolution;

public class CountSortedVowelStrings {

    // source: https://leetcode.com/problems/count-sorted-vowel-strings/discuss/918507/Java-DP-O(n)-time-Easy-to-understand

    // Let n = the value of n.
    //
    // Time: O(n)
    // --> We traverse through all the i-length strings incrementally from 1 <= i <= n at most once.
    // Space: O(n)
    // --> We create an (n + 1) by 6 memo table.

    public static void main(String[] args) {
        CountSortedVowelStrings countSortedVowelStrings = new CountSortedVowelStrings();

        // Input: n = 1
        // Output: 5
        // Explanation: The 5 sorted strings that consist of vowels only are ["a", "e", "i", "o", "u"].
        int numVowelStrings1 = countSortedVowelStrings.numVowelStrings(1);
        System.out.println(numVowelStrings1);  // 5

        // Input: n = 2
        // Output: 15
        // Explanation: The 15 sorted strings that consist of vowels only are
        // ["aa", "ae", "ai", "ao", "au", "ee", "ei", "eo", "eu", "ii", "io", "iu",
        // "oo", "ou", "uu"].
        // Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
        int numVowelStrings2 = countSortedVowelStrings.numVowelStrings(2);
        System.out.println(numVowelStrings2);  // 15

        // Input: n = 33
        // Output: 66045
        // Explanation: The 66045 sorted strings that consist of vowels only are
        int numVowelStrings3 = countSortedVowelStrings.numVowelStrings(33);
        System.out.println(numVowelStrings3);  // 66045
    }

    /**
     * Given an integer n, return the number of strings of length n that consist only of
     * vowels (a, e, i, o, u) and are lexicographically sorted.<br><br>
     *
     * A string s is lexicographically sorted if for all valid i, s[i] is the same as or
     * comes before s[i + 1] in the alphabet.<br><br>
     *
     * Preconditions:<br>
     * - 1 <= n <= 50.
     */
    public int numVowelStrings(int n) {
        int[][] memo = new int[n + 1][6];
        return numVowelStrings(n, 5, memo);
    }

    private int numVowelStrings(int stringLength, int numVowelsLeft, int[][] memo) {
        if (numVowelsLeft == 0) {
            memo[stringLength][numVowelsLeft] = 0;  // can't make vowel strings without vowels
            return memo[stringLength][numVowelsLeft];
        } else if (stringLength == 0) {
            memo[stringLength][numVowelsLeft] = 1;  // empty string uses 0 vowels
            return memo[stringLength][numVowelsLeft];
        } else {
            if (memo[stringLength][numVowelsLeft] != 0) {
                return memo[stringLength][numVowelsLeft];
            }
            // stringLength - 1 --> reduce string size
            // numVowelsLeft - 1 --> consume a vowel
            memo[stringLength][numVowelsLeft] =
                numVowelStrings(stringLength, numVowelsLeft - 1, memo) +
                numVowelStrings(stringLength - 1, numVowelsLeft, memo);
            return memo[stringLength][numVowelsLeft];
        }
    }
}
