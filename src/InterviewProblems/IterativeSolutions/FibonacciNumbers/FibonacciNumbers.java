package InterviewProblems.IterativeSolutions.FibonacciNumbers;

public class FibonacciNumbers {

    // Time: O(n)
    // --> We must compute the first n fibonacci numbers to find the nth fibonacci numbers.
    // Space: O(1)
    // --> We use an array to store the last 3 fibonacci numbers in the fibonacci sequence.

    public static void main(String[] args) {
        FibonacciNumbers fibonacciNumbers = new FibonacciNumbers();

        // Input: n = 2
        // Output: 1
        // Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
        System.out.println(fibonacciNumbers.fib(2));  // 1

        // Input: n = 3
        // Output: 2
        // Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
        System.out.println(fibonacciNumbers.fib(3));  // 2

        // Input: n = 4
        // Output: 3
        // Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
        System.out.println(fibonacciNumbers.fib(4));  // 3
    }

    /**
     * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence,
     * such that each number is the sum of the two preceding ones, starting from 0 and 1.
     *
     * That is,
     *
     * F(0) = 0, F(1) = 1
     * F(n) = F(n - 1) + F(n - 2), for n > 1.
     *
     * Return the value of F(n).
     *
     * Preconditions:
     * - 0 <= n <= 2^31 - 1.
     */
    public int fib(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        int[] fibonacciNumbers = new int[3];
        fibonacciNumbers[0] = 0;
        fibonacciNumbers[1] = 1;
        for (int i = 2; i <= n; i++) {
            int nextFibonacciNumber = fibonacciNumbers[0] + fibonacciNumbers[1];
            fibonacciNumbers[0] = fibonacciNumbers[1];
            fibonacciNumbers[1] = nextFibonacciNumber;
        }
        return fibonacciNumbers[1];
    }
}
