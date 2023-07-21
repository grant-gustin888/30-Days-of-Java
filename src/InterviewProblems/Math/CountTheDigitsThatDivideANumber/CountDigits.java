package InterviewProblems.Math.CountTheDigitsThatDivideANumber;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CountDigits {

    // TODO: come up with a simpler solution that doesn't require maps or sets.

    // time taken: 23:49 + 1 minute delay + 5 * 2 = 10 minute penalty for wrong submissions
    // = 34:49

    // Let n = the number of digits in num.
    //
    // Time: O(n)
    // --> O(n) for getDigitFrequencies()
    // --> O(n) for getUniqueDigits()
    // --> O(n) for the for loop in countDigits()
    // Space: O(n)
    // --> O(n) for getDigitFrequencies()
    // --> O(n) for getUniqueDigits()

    public static void main(String[] args) {
        CountDigits countDigits = new CountDigits();

        // Example 1:
        // Input: num = 7
        // Output: 1
        // Explanation: 7 divides itself, hence the answer is 1.
        int numDigits1 = countDigits.countDigits(7);
        System.out.println(numDigits1);  // 1

        // Example 2:
        // Input: num = 121
        // Output: 2
        // Explanation: 121 is divisible by 1, but not 2.
        // Since 1 occurs twice as a digit, we return 2.
        int numDigits2 = countDigits.countDigits(121);
        System.out.println(numDigits2);  // 2

        // Example 3:
        // Input: num = 1248
        // Output: 4
        // Explanation: 1248 is divisible by all of its digits,
        // hence the answer is 4.
        int numDigits3 = countDigits.countDigits(1248);
        System.out.println(numDigits3);  // 4
    }

    /**
     * Given an integer num, return the number of digits in num that divide num.
     *
     * An integer val divides nums if nums % val == 0.
     *
     * Preconditions:
     * - 1 <= num <= 10 ^ 9
     * - num does not contain 0 as one of its digits.
     */
    public int countDigits(int num) {
        // count the number of digits in num.
        Map<Integer, Integer> digitFrequencies = getDigitFrequencies(num);

        // get all unique digits of num
        Set<Integer> uniqueDigits = getUniqueDigits(num);  // excludes the digit 0.

        // for each digit in hashmap, check if num is divisible by each of the digits that appear in num.
        // +frequency for each digit that appears in num.
        int numDigitsDivisible = 0;  // the number of digits divisible by num
        for (int digit : uniqueDigits) {
            if (num % digit == 0) {
                numDigitsDivisible += digitFrequencies.get(digit);
            }
        }
        return numDigitsDivisible;
    }

    private Map<Integer, Integer> getDigitFrequencies(int num) {
        String numberAsString = String.valueOf(num);
        Map<Integer, Integer> digitFrequencies = new HashMap<>();
        for (char digit : numberAsString.toCharArray()) {
            int currentDigit = Character.getNumericValue(digit);
            if (digitFrequencies.containsKey(currentDigit)) {
                digitFrequencies.put(currentDigit, digitFrequencies.get(currentDigit) + 1);
            } else {
                digitFrequencies.put(currentDigit, 1);
            }
        }
        return digitFrequencies;
    }

    private Set<Integer> getUniqueDigits(int num) {
        Set<Integer> uniqueDigits = new HashSet<>();
        String numberAsString = String.valueOf(num);
        for (char digit : numberAsString.toCharArray()) {
            int currentDigit = Character.getNumericValue(digit) % 10;
            if (currentDigit != 0) {
                uniqueDigits.add(currentDigit);
            }
        }
        return uniqueDigits;
    }
}
