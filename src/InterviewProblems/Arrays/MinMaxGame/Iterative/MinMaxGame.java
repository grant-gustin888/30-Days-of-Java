package InterviewProblems.Arrays.MinMaxGame.Iterative;

public class MinMaxGame {

    // credit to: https://leetcode.com/problems/min-max-game/discuss/2332540/C%2B%2B-sol-100-optimal-7-lines-of-code

    // Let n = length of the nums array
    //
    // Time: O(n)
    // --> The two for loops run a total of O(n) iterations, and the
    // innermost loop body runs in O(1) time.
    // Space: O(1)
    // --> We don't create any new data structures, and we only use 2
    // other variable names i and n.

    public static void main(String[] args) {
        MinMaxGame minMaxGame = new MinMaxGame();

        // Input: [3]
        // Output: 3
        int[] nums2 = {3};
        int min2 = minMaxGame.minMaxGame(nums2);
        System.out.println(min2);  // 3

        // Input: [1, 3, 5, 2, 4, 8, 2, 2]
        // Output: 1
        int[] nums1 = {1, 3, 5, 2, 4, 8, 2, 2};
        int min1 = minMaxGame.minMaxGame(nums1);
        System.out.println(min1);  // 1
    }

    public int minMaxGame(int[] nums) {
        for (int n = nums.length; n > 1; n /= 2) {
            for (int i = 0; i < n / 2; i++) {
                if (i % 2 == 0) {
                    nums[i] = Math.min(nums[2 * i], nums[2 * i + 1]);
                } else {
                    nums[i] = Math.max(nums[2 * i], nums[2 * i + 1]);
                }
            }
        }
        return nums[0];

        // first attempt:
        // int[] newNums = new int[nums.length / 2];
        // while (newNums.length > 1) {
        //     newNums = new int[nums.length / 2];
        //     for (int i = 0; i < newNums.length; i++) {
        //         if (i % 2 == 0) {
        //             newNums[i] = Math.min(nums[2 * i], nums[2 * i + 1]);
        //         } else {  // i % 2 == 1
        //             newNums[i] = Math.max(nums[2 * i], nums[2 * i + 1]);
        //         }
        //     }
        // }
        // return nums[0];
    }
}
