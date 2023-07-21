package InterviewProblems.DynamicProgramming.HouseRobber.RecursiveSolution;

public class HouseRobber {

    // Let n = the length of the nums array.
    //
    // Time: O(2 ^ n)
    // --> recurrence relation for the runtime is
    // T(n)
    // = 1 if n = 0
    // = 1 if n = 1
    // = 3 if n = 2
    // = 2T(n - 2) + 3 + 1 = 2T(n - 2) + 4
    // Space: O(height of call stack) = O(n)
    // --> At most O(n) stack frames exist in the call stack at once.

    public final int MIN_DISTANCE_BETWEEN_HOUSES = 2;

    public static void main(String[] args) {
        HouseRobber houseRobber = new HouseRobber();

        // Example 1:
        // Input: nums = [2]
        // Output: 2
        // Explanation: Robbing the only house yields 2.
        int[] nums1 = {2};
        int maxMoney1 = houseRobber.rob(nums1);
        System.out.println(maxMoney1);  // 2

        // Example 2:
        // Input: nums = [1, 2]
        // Output: 2
        // Explanation: Robbing the first house only yields 1 (money = 1),
        // but robbing the second house yields 2 (money = 2).
        // Max money is 2 (rob the second house only).
        int[] nums2 = {1, 2};
        int maxMoney2 = houseRobber.rob(nums2);
        System.out.println(maxMoney2);  // 2

        // Example 3:
        // Input: nums = [2, 1]
        // Output: 2
        // Explanation: Robbing the first house yields 2 (money = 2),
        // but robbing the second house only yields 1 (money = 1).
        // Max money is 2 (rob the first house only).
        int[] nums3 = {2, 1};
        int maxMoney3 = houseRobber.rob(nums3);
        System.out.println(maxMoney3);  // 2

        // Example 4:
        // Input: nums = [1, 2, 3, 1]
        // Output: 4
        // Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
        // Total amount you can rob = 1 + 3 = 4.
        int[] nums4 = {1, 2, 3, 1};
        int maxMoney4 = houseRobber.rob(nums4);
        System.out.println(maxMoney4);  // 4

        // Example 5:
        // Input: nums = [2, 7, 9, 3, 1]
        // Output: 12
        // Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
        // Total amount you can rob = 2 + 9 + 1 = 12.
        int[] nums5 = {2, 7, 9, 3, 1};
        int maxMoney5 = houseRobber.rob(nums5);
        System.out.println(maxMoney5);  // 12

        int[] nums6 = {1, 3, 1};
        int maxMoney6 = houseRobber.rob(nums6);
        System.out.println(maxMoney6);  // 3

        int[] nums7 = {2, 1, 1, 2};
        int maxMoney7 = houseRobber.rob(nums7);
        System.out.println(maxMoney7);  // 4
    }

    /**
     * You are a professional robber planning to rob houses along a street.
     * Each house has a certain amount of money stashed, the only constraint
     * stopping you from robbing each of them is that adjacent houses have
     * security systems connected and it will automatically contact the
     * police if two adjacent houses were broken into on the same night.
     *
     * Given an integer array nums representing the amount of money of
     * each house, return the maximum amount of money you can rob tonight
     * without alerting the police.
     *
     * Preconditions:
     * - 1 <= nums.length <= 100.
     * - 0 <= nums[i] <= 400.
     */
    public int rob(int[] nums) {
        return robHelper(nums, 0, nums.length - 1);
    }

    private int robHelper(int[] nums, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return 0;
        } else if (startIndex == endIndex) {
            return nums[startIndex];  // or return nums[endIndex];
        } else if (startIndex + 1 == endIndex) {
            return Math.max(nums[startIndex], nums[endIndex]);
        } else {  // startIndex + 2 >= endIndex
            // either include current house, or skip to next house if it's worth more
            int robCurrentHouse = nums[startIndex] + robHelper(nums, startIndex + MIN_DISTANCE_BETWEEN_HOUSES, endIndex);
            int dontRobCurrentHouse = robHelper(nums, startIndex + 1, endIndex);
            return Math.max(robCurrentHouse, dontRobCurrentHouse);
        }
    }
}
