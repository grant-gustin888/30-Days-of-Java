package InterviewProblems.ACurrentlyDoingTheseQuestions.RandomMediumQuestions.PartitioningIntoMinNumberOfDecibinaryNumbers.AlternativeSolution;

public class DecibinaryNumbers {

    // Let n = the number of digits in the input string.
    //
    // Time: O(n)
    // --> We must iterate through each digit in the input string to find
    //     the largest digit.
    // Space: O(1)
    // --> We only need to store the largest digit seen so far.

    public static void main(String[] args) {
        DecibinaryNumbers decibinaryNumbers = new DecibinaryNumbers();

        // Input: n = "2"
        // Output: 1
        String n1 = "2";
        int numDecibinaryNumbers1 = decibinaryNumbers.minPartitions(n1);
        System.out.println(numDecibinaryNumbers1);  // 1

        // Input: n = "32"
        // Output: 3
        // Explanation: 10 + 11 + 11 = 32
        String n2 = "32";
        int numDecibinaryNumbers2 = decibinaryNumbers.minPartitions(n2);
        System.out.println(numDecibinaryNumbers2);  // 3

        // Input: n = "82734"
        // Output: 8
        String n3 = "82734";
        int numDecibinaryNumbers3 = decibinaryNumbers.minPartitions(n3);
        System.out.println(numDecibinaryNumbers3);  // 8

        // Input: n = "27346209830709182346"
        // Output: 9
        String n4 = "27346209830709182346";
        int numDecibinaryNumbers4 = decibinaryNumbers.minPartitions(n4);
        System.out.println(numDecibinaryNumbers4);  // 9
    }

    /**
     * A decimal number is called deci-binary if each of its digits is either 0 or 1
     * without any leading zeros. For example, 101 and 1100 are deci-binary, while
     * 112 and 3001 are not.
     *
     * Given a string n that represents a positive decimal integer, return the
     * minimum number of positive deci-binary numbers needed so that they sum up to n.
     *
     * Preconditions:
     * - 1 <= n.length <= 10 ^ 5.
     * - n consists of only digits.
     * - n does not contain any leading zeros and represents a positive integer.
     */
    public int minPartitions(String n) {
        char minNumDecibinaryNumbers = '0';
        for (char digit : n.toCharArray()) {
            if (minNumDecibinaryNumbers < digit) {
                minNumDecibinaryNumbers = digit;
            }
        }
        return minNumDecibinaryNumbers - '0';
    }
}
