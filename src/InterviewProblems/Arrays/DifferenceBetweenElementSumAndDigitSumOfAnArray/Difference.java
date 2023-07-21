package InterviewProblems.Arrays.DifferenceBetweenElementSumAndDigitSumOfAnArray;

public class Difference {

    // 1 min of brainstorming, solved in 5 minutes = 6 minutes total

    // more optimal solution? find elementSum and digitSum in a single pass through the array

    // Let n = the length of the nums array
    // Let k = the max number of digits in an element in the nums array
    //
    // Time: O(n * k)
    // --> O(n) to find the sum of all elements in the nums array
    // --> O(n * k) to find the sum of all digits in the nums array
    // --> O(1) to return the absolute difference between the element sum and digit sum
    // Space: O(1)
    // --> We only store the element sum and digit sum

    public static void main(String[] args) {
        Difference difference = new Difference();

        // Example 1:
        // Input: nums = [1, 15, 6, 3]
        // Output: 9
        // Explanation:
        // The element sum of nums is 1 + 15 + 6 + 3 = 25.
        // The digit sum of nums is 1 + 1 + 5 + 6 + 3 = 16.
        // The absolute difference between the element sum and digit sum is |25 - 16| = 9.
        int[] nums1 = {1, 15, 6, 3};
        int difference1 = difference.differenceOfSum(nums1);
        System.out.println(difference1);  // 9

        // Example 2:
        // Input: nums = [1, 2, 3, 4]
        // Output: 0
        // Explanation:
        // The element sum of nums is 1 + 2 + 3 + 4 = 10.
        // The digit sum of nums is 1 + 2 + 3 + 4 = 10.
        // The absolute difference between the element sum and digit sum is |10 - 10| = 0.
        int[] nums2 = {1, 2, 3, 4};
        int difference2 = difference.differenceOfSum(nums2);
        System.out.println(difference2);  // 0
    }

    /**
     * You are given a positive integer array nums.
     *
     * The element sum is the sum of all the elements in nums.
     * The digit sum is the sum of all the digits (not necessarily distinct) that appear in nums.
     * Return the absolute difference between the element sum and digit sum of nums.
     *
     * Note that the absolute difference between two integers x and y is defined as |x - y|.
     *
     * Preconditions:
     * - 1 <= nums.length <= 2000.
     * - 1 <= nums[i] <= 2000.
     */
    public int differenceOfSum(int[] nums) {
        // get element sum of all elements in nums array
        int elementSum = getElementSum(nums);

        // get digit sum of all elements in nums array
        int getDigitSum = getDigitSum(nums);

        // return absolute difference of element sum and digit sum
        return Math.abs(elementSum - getDigitSum);
    }

    private int getElementSum(int[] nums) {
        int elementSum = 0;
        for (int num : nums) {
            elementSum += num;
        }
        return elementSum;
    }

    private int getDigitSum(int[] nums) {
        int digitSum = 0;
        for (int num : nums) {
            int sumOfDigits = 0;
            while (num > 0) {
                sumOfDigits += num % 10;
                num /= 10;
            }
            digitSum += sumOfDigits;
        }
        return digitSum;
    }
}
