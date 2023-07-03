package InterviewProblems.Arrays.LeftAndRightSumDifferences;

import java.util.Arrays;

public class LeftAndRightSumDifferences {

    // Let n = the length of the array
    //
    // Time: O(n)
    // --> O(n) to get left sum array
    // --> O(n) to get right sum array
    // --> O(n) to get left-right sum difference array
    // Space: O(n)
    // --> O(n) for left sum array
    // --> O(n) for right sum array
    // --> O(n) for left-right sum difference array

    public static void main(String[] args) {
        LeftAndRightSumDifferences leftAndRightSumDifferences = new LeftAndRightSumDifferences();

        // Example 1:
        // Input: nums = [1]
        // Output: [0]
        // Explanation: The array leftSum is [0] and the array rightSum is [0].
        // The array answer is [|0 - 0|] = [0].
        int[] nums1 = {1};
        int[] differenceArray1 = leftAndRightSumDifferences.leftRightDifference(nums1);
        System.out.println(Arrays.toString(differenceArray1));  // [0]

        // Example 2:
        // Input: nums = [10, 4, 8, 3]
        // Output: [15, 1, 11, 22]
        // Explanation: The array leftSum is [0,10,14,22] and the array rightSum is [15, 11, 3, 0].
        // The array answer is [|0 - 15|, |10 - 11|, |14 - 3|, |22 - 0|] = [15, 1, 11, 22].
        int[] nums2 = {10, 4, 8, 3};
        int[] differenceArray2 = leftAndRightSumDifferences.leftRightDifference(nums2);
        System.out.println(Arrays.toString(differenceArray2));  // [15, 1, 11, 22]
    }

    /**
     * Given a 0-indexed integer array nums, find a 0-indexed integer array
     * answer where:
     *
     * 1. answer.length == nums.length.
     * 2. answer[i] = |leftSum[i] - rightSum[i]|.
     * 3. leftSum[i] is the sum of elements to the left of the index i in the
     * array nums. If there is no such element, leftSum[i] = 0.
     * 4. rightSum[i] is the sum of elements to the right of the index i in the
     * array nums. If there is no such element, rightSum[i] = 0.
     *
     * Return the array answer.
     *
     * Preconditions:
     * - 1 <= nums.length <= 1000.
     * - 1 <= nums[i] <= 10 ^ 5.
     */

    public int[] leftRightDifference(int[] nums) {
        // get left sum array
        int[] leftSumArray = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            leftSumArray[i] = leftSumArray[i - 1] + nums[i - 1];
        }

        // get right sum array
        int[] rightSumArray = new int[nums.length];
        for (int j = nums.length - 2; j >= 0; j--) {
            rightSumArray[j] = rightSumArray[j + 1] + nums[j + 1];
        }

        // for each element, take |left sum[i] - right sum[i]|
        int[] leftRightSumDifference = new int[nums.length];
        for (int k = 0; k < nums.length; k++) {
            leftRightSumDifference[k] = Math.abs(leftSumArray[k] - rightSumArray[k]);
        }
        return leftRightSumDifference;
    }
}
