package InterviewProblems.MediumQuestions.CurrentlyDoingTheseQuestions.nthFibonacciNumberDP;

public class nthFibonacciNumber {

    public static void main(String[] args) {
        nthFibonacciNumber nthFibonacciNumber = new nthFibonacciNumber();
        for (int i = 0; i <= 45; i++) {
            System.out.println(nthFibonacciNumber.fib(i));
        }
    }

    /**
     * Return the nth Fibonacci number.
     *
     * Preconditions:
     * - n >= 1.
     */
    public int fib(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        if (n >= 1) {
            dp[1] = 1;
        }
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
