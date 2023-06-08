package InterviewProblems.ACurrentlyDoingTheseQuestions.RandomMediumQuestions.StrictlyPalindromicNumber.OriginalSolution;

public class StrictlyPalindromicNumber {

    // Let n = the value of the input integer.
    // Let b = the base of the integer, where b >= 2.
    //
    // Time: O(n log_2 n)
    // --> we iterate through the for loop up to O(n) times.
    // When we check whether a number is palindromic, we:
    // - convert the number n into digits in base b, which takes O(log_b n) = O(log_2 n) time.
    // - check if the number n in base b (of length O(log_b n) = O(log_2 n)) in
    // O(log_b n) = O(log_2 n) time.
    //
    // Space: O(log_2 n)
    // --> We need O(log_b n) = O(log_2 n) digits to store the value of the integer n in base b.

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
        for (int base = 2; base <= n - 2; base++) {
            if (!isPalindromic(n, base)) {
                return false;
            }
        }
        return true;
    }

    private String convertToBase(int n, int base) {
        String digits = "";
        while (n > 0) {
            digits = n % base + digits;
            n /= base;
        }
        return digits;
    }

    private boolean isPalindromic(int n, int base) {
        String nInBaseB = convertToBase(n, base);
        int startIndex = 0;
        int endIndex = nInBaseB.length() - 1;
        while (startIndex < endIndex) {
            if (nInBaseB.charAt(startIndex) != nInBaseB.charAt(endIndex)) {
                return false;
            }

            startIndex++;
            endIndex--;
        }
        return true;

        // String nInBaseB = convertToBase(n, base);
        // for (int i = 0; i < nInBaseB.length() / 2; i++) {
        //     if (nInBaseB.charAt(i) != nInBaseB.charAt(nInBaseB.length() - 1 - i)) {
        //         return false;
        //     }
        // }
        // return true;
    }
}
