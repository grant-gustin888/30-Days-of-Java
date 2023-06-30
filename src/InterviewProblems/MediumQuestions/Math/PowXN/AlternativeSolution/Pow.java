package InterviewProblems.MediumQuestions.Math.PowXN.AlternativeSolution;

public class Pow {

    // inspired by NeetCode solutions: https://neetcode.io/practice
    // Math and Geometry > Pow(x, n)

    // Let n = the absolute value of the exponent in x ^ n.
    //
    // Time: O(log n)
    // --> Each time (except for the first time if n < 0), we divide n by 2, so we
    // recurse at most log n times.
    // Space: O(log n)
    // --> Each time (except for the first time if n < 0), we divide n by 2, so we
    // must create at most log n stack frames for each recursive call to myPow.

    public static void main(String[] args) {
        Pow pow = new Pow();

        // Input: x = 2.00000, n = 10
        // Output: 1024.00000
        double pow1 = pow.myPow(2.00000, 10);
        System.out.println(pow1);  // 1024.00000

        // Input: x = 2.10000, n = 3
        // Output: 9.26100
        double pow2 = pow.myPow(2.10000, 3);
        System.out.println(pow2);  // 9.26100

        // Input: x = 2.00000, n = -2
        // Output: 0.25000
        // Explanation: 2-2 = 1/22 = 1/4 = 0.25
        double pow3 = pow.myPow(2.00000, -2);
        System.out.println(pow3);  // 0.25000
    }

    /**
     * Implement pow(x, n), which calculates x raised to the power n (i.e., x ^ n).
     *
     * Preconditions:
     * -100.0 < x < 100.0
     * -2 ^ 31 <= n <= 2 ^ 31 - 1
     * n is an integer.
     * -10 ^ 4 <= x ^ n <= 10 ^ 4
     */
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        } else if (n < 0) {
            if (n % 2 == 0) {  // n even
                n = n / 2;
                n = -n;
                x = (1 / x) * (1 / x);
            } else {  // n odd
                // Odds don't need to be divided as their negative is in the positive limit
                n = -n;
                x = 1 / x;
            }
        }

        // assert n > 0;
        if (n % 2 == 0) {  // n even
            return myPow(x * x, n / 2);
        } else {  // n odd
            return x * myPow(x * x, n / 2);
        }
    }
}