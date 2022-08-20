package InterviewProblems.Hashmaps.MaximumNumberOfPassInArray;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MaximumPasses {

    // Let n = the length of the nums array.
    //
    // Time: O(n)
    // --> We must traverse the entire array once to determine the maximum number of
    // pairs possible in the nums array.
    // Space: O(n)
    // --> We must store a hashset of unpaired numbers.

    public static void main(String[] args) {
        MaximumPasses maximumPasses = new MaximumPasses();

        // Input: nums = [0]
        // Output: [0, 1]
        // Explanation: No pairs can be formed, and there is 1 number leftover in nums.
        int[] nums1 = {0};
        int[] maximumPasses1 = maximumPasses.numberOfPairs(nums1);
        System.out.println(Arrays.toString(maximumPasses1));  // [0, 1]

        // Input: nums = [1, 1]
        // Output: [1, 0]
        // Explanation: Form a pair with nums[0] and nums[1] and remove them from nums. Now, nums = [].
        // No more pairs can be formed. A total of 1 pair has been formed, and there are 0 numbers leftover in nums.
        int[] nums2 = {1, 1};
        int[] maximumPasses2 = maximumPasses.numberOfPairs(nums2);
        System.out.println(Arrays.toString(maximumPasses2));  // [1, 0]

        // Input: nums = [0, 3]
        // Output: [0, 2]
        int[] nums3 = {0, 3};
        int[] maximumPasses3 = maximumPasses.numberOfPairs(nums3);
        System.out.println(Arrays.toString(maximumPasses3));  // [0, 2]

        // Input: nums = [1, 3, 2, 1, 3, 2, 2]
        // Output: [3, 1]
        // Explanation:
        // Form a pair with nums[0] and nums[3] and remove them from nums. Now, nums = [3, 2, 3, 2, 2].
        // Form a pair with nums[0] and nums[2] and remove them from nums. Now, nums = [2, 2, 2].
        // Form a pair with nums[0] and nums[1] and remove them from nums. Now, nums = [2].
        // No more pairs can be formed. A total of 3 pairs have been formed, and there is 1 number leftover in nums.
        int[] nums4 = {1, 3, 2, 1, 3, 2, 2};
        int[] maximumPasses4 = maximumPasses.numberOfPairs(nums4);
        System.out.println(Arrays.toString(maximumPasses4));  // [3, 1]
    }

    /**
     * You are given a 0-indexed integer array nums. In one operation, you may
     * do the following:<br>
     * 1. Choose two integers in nums that are equal.<br>
     * 2. Remove both integers from nums, forming a pair.<br>
     * 3. The operation is done on nums as many times as possible.<br><br>
     *
     * Return a 0-indexed integer array answer of size 2 where answer[0] is
     * the number of pairs that are formed and answer[1] is the number of
     * leftover integers in nums after doing the operation as many times as
     * possible.<br><br>
     *
     * Preconditions:
     * - 1 <= nums.length <= 100.<br>
     * - 0 <= nums[i] <= 100.
     */
    public int[] numberOfPairs(int[] nums) {
        int numPairs = 0;
        Set<Integer> unpairedNumbers = new HashSet<>();
        for (int number : nums) {
            if (!unpairedNumbers.contains(number)) {
                unpairedNumbers.add(number);
            } else {  // unpairedNumbers.contains(number)
                unpairedNumbers.remove(number);
                numPairs++;
            }
        }
        return new int[] {numPairs, unpairedNumbers.size()};
    }
}
