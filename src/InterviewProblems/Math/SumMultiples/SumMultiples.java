package InterviewProblems.Math.SumMultiples;

public class SumMultiples {

    // Let n = the value of the input integer.
    //
    // Time: O(n)
    // --> We iterate through all divisors of n from 1 to n.
    // Space: O(1)
    // --> We only need to store the divisor sum.

    public static void main(String[] args) {
        SumMultiples sumMultiples = new SumMultiples();

        // Example 1:
        // Input: n = 7
        // Output: 21
        // Explanation: Numbers in the range [1, 7] that are divisible by
        // 3, 5, or 7 are 3, 5, 6, 7. The sum of these numbers is 21.
        int divisorSum1 = sumMultiples.sumOfMultiples(7);
        System.out.println(divisorSum1);  // 21

        // Example 2:
        // Input: n = 10
        // Output: 40
        // Explanation: Numbers in the range [1, 10] that are divisible by
        // 3, 5, or 7 are 3, 5, 6, 7, 9, 10. The sum of these numbers is 40.
        int divisorSum2 = sumMultiples.sumOfMultiples(10);
        System.out.println(divisorSum2);  // 40

        // Example 3:
        // Input: n = 9
        // Output: 30
        // Explanation: Numbers in the range [1, 9] that are divisible by
        // 3, 5, or 7 are 3, 5, 6, 7, 9. The sum of these numbers is 30.
        int divisorSum3 = sumMultiples.sumOfMultiples(9);
        System.out.println(divisorSum3);  // 30
    }

    /**
     * Given a positive integer n, find the sum of all integers in the range
     * [1, n] inclusive that are divisible by 3, 5, or 7.
     *
     * Return an integer denoting the sum of all numbers in the given range
     * satisfying the constraint.
     *
     * Preconditions:
     * - 1 <= n <= 10 ^ 3.
     */
    public int sumOfMultiples(int n) {
        // iterate through all divisors of n
        int divisorSum = 0;
        for (int divisor = 1; divisor <= n; divisor++) {
            if (divisor % 3 == 0 || divisor % 5 == 0 || divisor % 7 == 0) {
                divisorSum += divisor;
            }
        }
        return divisorSum;
    }
}
