package InterviewProblems.MediumQuestions.DynamicProgramming.CountSortedVowelStrings.IterativeSolution.Original;

public class CountSortedVowelStrings {

    // source: https://leetcode.com/problems/count-sorted-vowel-strings/discuss/918507/Java-DP-O(n)-time-Easy-to-understand

    // Let n = the value of n.
    //
    // Time: O(n)
    // --> We iterate through vowel string lengths 1 to n.
    // Space: O(1)
    // --> We store only 5 variables: numAStrings, numEStrings, numIStrings, numOStrings, numUStrings.

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
        int numAStrings = 1;  // strings that end with 'a'
        int numEStrings = 1;  // strings that end with 'e'
        int numIStrings = 1;  // strings that end with 'i'
        int numOStrings = 1;  // strings that end with 'o'
        int numUStrings = 1;  // strings that end with 'u'
        for (int vowelStringLength = 1; vowelStringLength < n; vowelStringLength++) {
            // You can append 'a', 'e', 'i', 'o', 'u' to a vowel string of length n - 1 ending with 'a'.
            numAStrings = numAStrings + numEStrings + numIStrings + numOStrings + numUStrings;

            // You can append 'e', 'i', 'o', 'u' to a vowel string of length n - 1 ending with 'e'.
            numEStrings = numEStrings + numIStrings + numOStrings + numUStrings;

            // You can append 'i', 'o', 'u' to a vowel string of length n - 1 ending with 'i'.
            numIStrings = numIStrings + numOStrings + numUStrings;

            // You can append 'o', 'u' to a vowel string of length n - 1 ending with 'o'.
            numOStrings = numOStrings + numUStrings;

            // You can only append 'u' to a vowel string of length n - 1 ending with 'u'.
            // numUStrings = numUStrings;
        }
        return numAStrings + numEStrings + numIStrings + numOStrings + numUStrings;
    }
}
