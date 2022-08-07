package InterviewProblems.MediumQuestions.DynamicProgramming.CountSortedVowelStrings.RecursiveSolution;

public class CountSortedVowelStrings {

    // source: https://leetcode.com/problems/count-sorted-vowel-strings/discuss/1146804/Recursion-and-memoization-in-python

    // Let n = the value of n.
    //
    // Time: O(5 ^ n)
    // --> For each n, we need to compute the number of strings of length n for each of the 5 vowels.
    // Space: O(n)
    // --> We only need to store up to n strings in the call stack at a time.

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
        return numVowelStrings(n, 5);
    }

    private int numVowelStrings(int stringLength, int numVowelsLeft) {
        if (numVowelsLeft == 0) {
            return 0;  // can't make vowel strings without vowels
        } else if (stringLength == 0) {
            return 1;  // empty string uses 0 vowels
        } else {
            // stringLength - 1 --> reduce string size
            // numVowelsLeft - 1 --> consume a vowel
            return  numVowelStrings(stringLength, numVowelsLeft - 1) +
                    numVowelStrings(stringLength - 1, numVowelsLeft);
        }
    }
}
