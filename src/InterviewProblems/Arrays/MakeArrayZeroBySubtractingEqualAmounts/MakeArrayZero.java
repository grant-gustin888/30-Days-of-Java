package InterviewProblems.Arrays.MakeArrayZeroBySubtractingEqualAmounts;

import java.util.HashSet;
import java.util.Set;

public class MakeArrayZero {

    // Let n = the length of the nums array.
    //
    // Time: O(n)
    // --> We must traverse the nums array once to determine unique integers.
    // Space: O(n)
    // --> We must store a hashset of unique non-zero integers.

    public static void main(String[] args) {
        MakeArrayZero makeArrayZero = new MakeArrayZero();

        // Input: nums = [0]
        // Output: 0
        // Explanation: Each element in nums is already 0 so no operations are needed.
        int[] nums1 = {0};
        int minOperations1 = makeArrayZero.minimumOperations(nums1);
        System.out.println(minOperations1);  // 0

        // Input: nums = [0, 0, 0, 0, 0]
        // Output: 0
        // Explanation: Each element in nums is already 0 so no operations are needed.
        int[] nums2 = {0, 0, 0, 0, 0};
        int minOperations2 = makeArrayZero.minimumOperations(nums2);
        System.out.println(minOperations2);  // 0

        // Input: nums = [1]
        // Output: 1
        int[] nums3 = {1};
        int minOperations3 = makeArrayZero.minimumOperations(nums3);
        System.out.println(minOperations3);  // 1

        // Input: nums = [1, 1, 1, 1, 1]
        // Output: 1
        int[] nums4 = {1, 1, 1, 1, 1};
        int minOperations4 = makeArrayZero.minimumOperations(nums4);
        System.out.println(minOperations4);  // 1

        // Input: nums = [1, 5, 0, 3, 5]
        // Output: 3
        // Explanation:
        // In the first operation, choose x = 1. Now, nums = [0, 4, 0, 2, 4].
        // In the second operation, choose x = 2. Now, nums = [0, 2, 0, 0, 2].
        // In the third operation, choose x = 2. Now, nums = [0, 0, 0, 0, 0].
        int[] nums5 = {1, 5, 0, 3, 5};
        int minimumOperations5 = makeArrayZero.minimumOperations(nums5);
        System.out.println(minimumOperations5);  // 3

        // Input: nums = [1, 2, 3, 4, 5, 6]
        // Output: 6
        int[] nums6 = {1, 2, 3, 4, 5, 6};
        int minimumOperations6 = makeArrayZero.minimumOperations(nums6);
        System.out.println(minimumOperations6);  // 6
    }

    /**
     * You are given a non-negative integer array nums. In one operation, you must:<br>
     * 1. Choose a positive integer x such that x is less than or equal to the smallest
     * non-zero element in nums.<br>
     * 2. Subtract x from every positive element in nums.<br><br>
     *
     * Return the minimum number of operations to make every element in nums equal to 0.
     */
    public int minimumOperations(int[] nums) {
        // The minimum number of operations to make every element in nums equal to 0 is the
        // same as the number of non-zero unique numbers in the array.
        Set<Integer> uniqueNonZeroIntegers = new HashSet<>();
        for (int number : nums) {
            if (number != 0) {
                uniqueNonZeroIntegers.add(number);
            }
        }
        return uniqueNonZeroIntegers.size();
    }
}
