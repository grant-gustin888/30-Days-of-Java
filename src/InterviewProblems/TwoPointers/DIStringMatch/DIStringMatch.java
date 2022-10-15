package InterviewProblems.TwoPointers.DIStringMatch;

import java.util.Arrays;

public class DIStringMatch {

    // Let n = the length of the string s.
    //
    // Time: O(n)
    // --> We must iterate through all characters in string s to reconstruct the permutation.
    // Space: O(n)
    // --> We must create an array of integers of size n + 1.

    public static void main(String[] args) {
        DIStringMatch diStringMatch = new DIStringMatch();

        // Input: s = "IDID"
        // Output: [0, 4, 1, 3, 2]
        int[] diStringMatch1 = diStringMatch.diStringMatch("IDID");
        System.out.println(Arrays.toString(diStringMatch1));  // [0, 4, 1, 3, 2]

        // Input: s = "III"
        // Output: [0, 1, 2, 3]
        int[] diStringMatch2 = diStringMatch.diStringMatch("III");
        System.out.println(Arrays.toString(diStringMatch2));  // [0, 1, 2, 3]

        // Input: s = "DDI"
        // Output: [3, 2, 0, 1]
        int[] diStringMatch3 = diStringMatch.diStringMatch("DDI");
        System.out.println(Arrays.toString(diStringMatch3));  // [3, 2, 0, 1]
    }

    /**
     * A permutation perm of n + 1 integers of all the integers in the range [0, n] can
     * be represented as a string s of length n where:
     * --> s[i] == 'I' if perm[i] < perm[i + 1], and
     * --> s[i] == 'D' if perm[i] > perm[i + 1].
     *
     * Given a string s, reconstruct the permutation perm and return it.
     * If there are multiple valid permutations perm, return any of them.
     *
     * Preconditions:
     * - 1 <= s.length <= 10 ^ 5.
     * - s[i] is either 'I' or 'D'.
     */
    public int[] diStringMatch(String s) {
        int[] permutationIndices = new int[s.length() + 1];
        int startIndex = 0;
        int endIndex = s.length() - 1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'I') {
                permutationIndices[i] = startIndex;
                startIndex++;
            } else {  // s.charAt(i) == 'D'
                permutationIndices[i] = endIndex + 1;
                endIndex--;
            }
        }

        permutationIndices[s.length()] = startIndex;  // or Math.max(startIndex, endIndex);
        return permutationIndices;
    }
}
