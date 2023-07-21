package InterviewProblems.Arrays.MaximumSumWithExactlyKElements.OptimalSolution;

public class MaxSumWithExactlyKElements {

    // Let n = the length of the nums array
    // Let k = the number of operations we perform
    //
    // Time: O(n)
    // --> O(n) to find the max value in the nums array
    // --> O(1) to calculate the sum of am arithmetic series
    // Space: O(1)
    // --> We only store the max value of the nums array

    public static void main(String[] args) {
        MaxSumWithExactlyKElements maxSumWithExactlyKElements = new MaxSumWithExactlyKElements();

        // Example 1:
        // Input: nums = [1, 2, 3, 4, 5], k = 3
        // Output: 18
        // Explanation: We need to choose exactly 3 elements from nums to maximize the sum.
        // For the first iteration, we choose 5. Then sum is 5 and nums = [1, 2, 3, 4, 6]
        // For the second iteration, we choose 6. Then sum is 5 + 6 and nums = [1, 2, 3, 4, 7]
        // For the third iteration, we choose 7. Then sum is 5 + 6 + 7 = 18 and nums = [1, 2, 3, 4, 8]
        // So, we will return 18.
        // It can be proven, that 18 is the maximum answer that we can achieve.
        int[] nums1 = {1, 2, 3, 4, 5};
        int maxSum1 = maxSumWithExactlyKElements.maximizeSum(nums1, 3);
        System.out.println(maxSum1);  // 18

        // Example 2:
        // Input: nums = [5, 5, 5], k = 2
        // Output: 11
        // Explanation: We need to choose exactly 2 elements from nums to maximize the sum.
        // For the first iteration, we choose 5. Then sum is 5 and nums = [5, 5, 6]
        // For the second iteration, we choose 6. Then sum is 5 + 6 = 11 and nums = [5, 5, 7]
        // So, we will return 11.
        // It can be proven, that 11 is the maximum answer that we can achieve.
        int[] nums2 = {5, 5, 5};
        int maxSum2 = maxSumWithExactlyKElements.maximizeSum(nums2, 2);
        System.out.println(maxSum2);  // 11
    }

    /**
     * You are given a 0-indexed integer array nums and an integer k.
     * Your task is to perform the following operation exactly k times
     * in order to maximize your score:
     * 1. Select an element m from nums.
     * 2. Remove the selected element m from the array.
     * 3. Add a new element with a value of m + 1 to the array.
     * 4. Increase your score by m.
     *
     * Return the maximum score you can achieve after performing the
     * operation exactly k times.
     *
     * Preconditions:
     * - 1 <= nums.length <= 100
     * - 1 <= nums[i] <= 100
     * - 1 <= k <= 100
     */
    public int maximizeSum(int[] nums, int k) {
        // find the max number in the nums array.
        // because to maximize the score, you always choose the largest digit
        int maxValue = getMaxValue(nums);

        // sequence is max, max + 1, ... , max + (k - 1)
        // The sum of this arithmetic sequence is:
        //
        // sum of i = 1 to k (max + i - 1)
        // = sum of i = 1 to k max + sum of i = 1 to k (i - 1)
        // = sum of i = 1 to k max + sum of i = 1 to k (i) - sum of i = 1 to k (1)
        // = sum of i = 1 to k max + k * (k + 1) / 2 - k
        // = max * k + (k - 1) * k / 2
        return maxValue * k + (k - 1) * (k) / 2;
    }

    private int getMaxValue(int[] nums) {
        int maxValue = -1;  // 1 <= nums[i] <= 100
        for (int num : nums) {
            maxValue = Math.max(maxValue, num);
        }
        return maxValue;
    }
}
