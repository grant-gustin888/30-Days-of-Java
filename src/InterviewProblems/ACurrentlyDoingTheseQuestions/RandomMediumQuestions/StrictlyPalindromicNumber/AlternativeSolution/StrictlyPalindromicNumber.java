package InterviewProblems.ACurrentlyDoingTheseQuestions.RandomMediumQuestions.StrictlyPalindromicNumber.AlternativeSolution;

public class StrictlyPalindromicNumber {

    // Let n = the value of the input integer
    //
    // Time: O(1)
    // --> We just return false.
    // Space: O(1)
    // --> We don't store any additional data in this algorithm.

    public static void main(String[] args) {
        StrictlyPalindromicNumber strictlyPalindromicNumber = new StrictlyPalindromicNumber();

        // Input: n = 9
        // Output: false
        // Explanation: In base 2: 9 = 1001 (base 2), which is palindromic.
        // In base 3: 9 = 100 (base 3), which is not palindromic.
        // Therefore, 9 is not strictly palindromic so we return false.
        // Note that in bases 4, 5, 6, and 7, n = 9 is also not palindromic.
        boolean isStrictlyPalindromic1 = strictlyPalindromicNumber.isStrictlyPalindromic(9);
        System.out.println(isStrictlyPalindromic1);  // false

        // Input: n = 4
        // Output: false
        // Explanation: We only consider base 2: 4 = 100 (base 2), which is not palindromic.
        // Therefore, we return false.
        boolean isStrictlyPalindromic2 = strictlyPalindromicNumber.isStrictlyPalindromic(4);
        System.out.println(isStrictlyPalindromic2);  // false
    }

    /**
     * An integer n is strictly palindromic if, for every base b between 2 and
     * n - 2 (inclusive), the string representation of the integer n in base b
     * is palindromic.
     *
     * Given an integer n, return true if n is strictly palindromic and false otherwise.
     *
     * A string is palindromic if it reads the same forward and backward.
     *
     * Preconditions:
     * - 4 <= n <= 10 ^ 5.
     */
    public boolean isStrictlyPalindromic(int n) {
        return false;
    }
}
