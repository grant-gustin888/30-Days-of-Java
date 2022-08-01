package InterviewProblems.MediumQuestions.Math.TotalHammingDistance.NaiveSolution;

import java.util.LinkedList;
import java.util.List;

public class TotalHammingDistance {

    // Let n = the number of elements in the nums array.
    //
    // Time: O(n ^ 2)
    // --> O(n ^ 2) to generate all (n choose 2) = n(n - 1) / 2 pairs of numbers in nums.
    // --> O(n ^ 2) to compute the total hamming distances of all n numbers in nums:
    // computing the hamming distance takes O(n) time?
    // Space: O(n ^ 2)
    // --> We must store all (n choose 2) = n(n - 1) / 2 pairs of numbers in nums.

    public static void main(String[] args) {
        TotalHammingDistance totalHammingDistance = new TotalHammingDistance();

        // Input: nums = [1]
        // Output: 0
        int[] nums1 = {1};
        int totalHammingDistance1 = totalHammingDistance.totalHammingDistance(nums1);
        System.out.println(totalHammingDistance1);  // 0

        // Input: nums = [4, 14, 4]
        // Output: 4
        int[] nums2 = {4, 14, 4};
        int totalHammingDistance2 = totalHammingDistance.totalHammingDistance(nums2);
        System.out.println(totalHammingDistance2);  // 4

        // Input: nums = [4, 14, 2]
        // Output: 6
        // Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
        // showing the four bits relevant in this case).
        // The answer will be:
        // HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
        int[] nums3 = {4, 14, 2};
        int totalHammingDistance3 = totalHammingDistance.totalHammingDistance(nums3);
        System.out.println(totalHammingDistance3);  // 6
    }

    /**
     * The Hamming distance between two integers is the number of positions at
     * which the corresponding bits are different.<br><br>
     *
     * Given an integer array nums, return the sum of Hamming distances between
     * all the pairs of the integers in nums.<br><br>
     *
     * Preconditions:<br>
     * - 1 <= nums.length <= 10 ^ 4.<br>
     * - 0 <= nums[i] <= 10 ^ 9.<br>
     * - The answer for the given input will fit in a 32-bit integer.
     */
    public int totalHammingDistance(int[] nums) {
        List<List<Integer>> allPairsOfNumbers = getAllPairs(nums);
        int totalHammingDistance = 0;
        for (List<Integer> pair : allPairsOfNumbers) {
            int num1 = pair.get(0);
            int num2 = pair.get(1);
            totalHammingDistance += hammingDistance(num1, num2);
        }
        return totalHammingDistance;
    }

    private List<List<Integer>> getAllPairs(int[] nums) {
        List<List<Integer>> allPairs = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                List<Integer> pair = new LinkedList<>();
                pair.add(nums[i]);
                pair.add(nums[j]);
                allPairs.add(pair);
            }
        }
        return allPairs;
    }

    private int hammingDistance(int num1, int num2) {
        int hammingDistance = 0;
        int xor = num1 ^ num2;
        while (xor != 0) {
            hammingDistance++;
            xor = xor & (xor - 1);
        }
        return hammingDistance;
    }
}
