package InterviewProblems.MediumQuestions.DynamicProgramming.CountSortedVowelStrings.DPSolution.oneD;

public class CountSortedVowelStrings {

    // source: https://leetcode.com/problems/count-sorted-vowel-strings/discuss/918498/JavaC%2B%2BPython-DP-O(1)-Time-and-Space

    // Let n = the value of n.
    //
    // Time: O(n)
    // --> We iterate through vowel string lengths 1 through n, and compute the values for each of the 5 vowels.
    // Space: O(1)
    // --> We only store an array of 6 integers.

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
        int[] numVowelStringsDP = {0, 1, 1, 1, 1, 1};
        for (int stringLength = 1; stringLength <= n; stringLength++) {
            for (int numVowelsLeft = 1; numVowelsLeft <= 5; numVowelsLeft++) {
                numVowelStringsDP[numVowelsLeft] += numVowelStringsDP[numVowelsLeft - 1];
            }
        }
        return numVowelStringsDP[5];  // the number of vowel strings (5 vowels to choose from) of length n
    }
}
