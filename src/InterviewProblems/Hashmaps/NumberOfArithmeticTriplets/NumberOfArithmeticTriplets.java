package InterviewProblems.Hashmaps.NumberOfArithmeticTriplets;

import java.util.HashSet;
import java.util.Set;

public class NumberOfArithmeticTriplets {

    // Let n = the length of the nums array.
    //
    // Time: O(n)
    // --> O(n) to populate the hashset of numbers in the nums array.
    // --> O(n) to traverse the nums array to identify arithmetic triplets.
    // Space: O(n)
    // --> We must store all the numbers in the nums array in a hashset.

    public static void main(String[] args) {
        NumberOfArithmeticTriplets numberOfArithmeticTriplets = new NumberOfArithmeticTriplets();

        // Input: nums = [0, 1, 3], diff = 4
        // Output: 0
        int[] nums1 = {0, 1, 3};
        int numberOfArithmeticTriplets1 = numberOfArithmeticTriplets.arithmeticTriplets(nums1, 4);
        System.out.println(numberOfArithmeticTriplets1);  // 0

        // Input: nums = [0, 1, 4, 6, 7, 10], diff = 3
        // Output: 2
        // Explanation:
        // (1, 2, 4) is an arithmetic triplet because both 7 - 4 == 3 and 4 - 1 == 3.
        // (2, 4, 5) is an arithmetic triplet because both 10 - 7 == 3 and 7 - 4 == 3.
        int[] nums2 = {0, 1, 4, 6, 7, 10};
        int numberOfArithmeticTriplets2 = numberOfArithmeticTriplets.arithmeticTriplets(nums2, 3);
        System.out.println(numberOfArithmeticTriplets2);  // 2

        // Input: nums = [4, 5, 6, 7, 8, 9], diff = 2
        // Output: 2
        // Explanation:
        // (0, 2, 4) is an arithmetic triplet because both 8 - 6 == 2 and 6 - 4 == 2.
        // (1, 3, 5) is an arithmetic triplet because both 9 - 7 == 2 and 7 - 5 == 2.
        int[] nums3 = {4, 5, 6, 7, 8, 9};
        int numberOfArithmeticTriplets3 = numberOfArithmeticTriplets.arithmeticTriplets(nums3, 2);
        System.out.println(numberOfArithmeticTriplets3);  // 2
    }

    /**
     * You are given a 0-indexed, strictly increasing integer array nums and a positive
     * integer diff. A triplet (i, j, k) is an arithmetic triplet if the following
     * conditions are met:<br>
     * 1. i < j < k,<br>
     * 2. nums[j] - nums[i] == diff, and<br>
     * 3. nums[k] - nums[j] == diff.<br><br>
     *
     * Return the number of unique arithmetic triplets.<br><br>
     *
     * Preconditions:<br>
     * - 3 <= nums.length <= 200.<br>
     * - 0 <= nums[i] <= 200.<br>
     * - 1 <= diff <= 50.<br>
     * - nums is strictly increasing.
     */
    public int arithmeticTriplets(int[] nums, int diff) {
        Set<Integer> numbersSeen = populateNumbersSeen(nums);
        return getNumArithmeticTriplets(nums, diff, numbersSeen);
    }

    private Set<Integer> populateNumbersSeen(int[] nums) {
        Set<Integer> numbersSeen = new HashSet<>();
        for (int number : nums) {
            numbersSeen.add(number);
        }
        return numbersSeen;
    }

    private int getNumArithmeticTriplets(int[] nums, int diff, Set<Integer> numbersSeen) {
        int numUniqueArithmeticTriplets = 0;
        for (int number : nums) {
            if (isArithmeticTriplet(numbersSeen, number, diff)) {
                numUniqueArithmeticTriplets++;
            }
        }
        return numUniqueArithmeticTriplets;
    }

    private boolean isArithmeticTriplet(Set<Integer> numbersSeen, int number, int diff) {
        return numbersSeen.contains(number - diff) && numbersSeen.contains(number + diff);
    }
}
