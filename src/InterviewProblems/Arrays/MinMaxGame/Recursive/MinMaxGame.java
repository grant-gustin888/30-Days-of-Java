package InterviewProblems.Arrays.MinMaxGame.Recursive;

public class MinMaxGame {

    // Let n = length of the nums array
    //
    // Time: O(n)
    // --> Recurrence relation
    // --> T(n) = T(n / 2) + O(n) + O(1) = T(n / 2) + O(n) = O(n)
    // --> T(n / 2) <-- the tail recursive call
    // --> O(1) <-- for base case
    // --> O(n / 2) <-- the for loop runs n / 2 times, body runs in O(1)
    // Space: O(n)
    // --> O(n / 2) + O(n / 4) + ... + O(1) = O(2n) = O(n).
    // --> O(n / 2) for the first newNums array,
    // --> O(n / 4) for the second newNums array, ... , etc.
    // --> until we get to the base case, where we have O(1) space.

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
        if (nums.length == 1) {
            return nums[0];
        }

        int[] newNums = new int[nums.length / 2];
        for (int i = 0; i < newNums.length; i++) {
            if (i % 2 == 0) {
                newNums[i] = Math.min(nums[2 * i], nums[2 * i + 1]);
            } else {  // i % 2 == 1
                newNums[i] = Math.max(nums[2 * i], nums[2 * i + 1]);
            }
        }
        return minMaxGame(newNums);
    }
}
