package InterviewProblems.Math.SmallestEvenMultiple;

public class SmallestEvenMultiple {

    // Time: O(1)
    // --> We only need to check if n is even or odd, and we only perform 2 - 3
    // calculations.
    // Space: O(1)
    // --> We don't need any additional data structures.

    public static void main(String[] args) {
        SmallestEvenMultiple smallestEvenMultiple = new SmallestEvenMultiple();

        // Input: n = 5
        // Output: 10
        // Explanation: The smallest multiple of both 5 and 2 is 10.
        int smallestEvenMultiple1 = smallestEvenMultiple.smallestEvenMultiple(5);
        System.out.println(smallestEvenMultiple1);  // 10

        // Input: n = 6
        // Output: 6
        // Explanation: The smallest multiple of both 6 and 2 is 6.
        // Note that a number is a multiple of itself.
        int smallestEvenMultiple2 = smallestEvenMultiple.smallestEvenMultiple(6);
        System.out.println(smallestEvenMultiple2);  // 6

        // Input: n = 149
        // Output: 298
        int smallestEvenMultiple3 = smallestEvenMultiple.smallestEvenMultiple(149);
        System.out.println(smallestEvenMultiple3);  // 298

        // Input: n = 150
        // Output: 150
        // Note that a number is a multiple of itself.
        int smallestEvenMultiple4 = smallestEvenMultiple.smallestEvenMultiple(150);
        System.out.println(smallestEvenMultiple4);  // 150
    }

    /**
     * Given a positive integer n, return the smallest positive integer that is
     * a multiple of both 2 and n.
     *
     * Preconditions:
     * - 1 <= n <= 150.
     */
    public int smallestEvenMultiple(int n) {
        if (n % 2 != 0) {
            return n * 2;
        } else {
            return n;
        }
    }
}
