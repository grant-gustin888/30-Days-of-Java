package InterviewProblems.MediumQuestions.Math.TotalHammingDistance.OptimalSolution;

public class TotalHammingDistance {

    // source: https://leetcode.com/problems/total-hamming-distance/submissions/

    // Let n = the number of elements in the nums array.
    //
    // Time: O(n)
    // --> We iterate through all 32 columns for each bit (an integer has 32 bits).
    // and each pair hamming distance check takes O(n) time.
    // Space: O(1)
    // --> We only use 4 variables: totalHammingDistance, bitIndex, numZeroes,
    // and numOnes.

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
        int totalHammingDistance = 0;
        // bitIndex is the index of the bit we are currently looking at,
        // starting from the right.
        for (int bitIndex = 0; bitIndex < 32; bitIndex++) {
            int numZeroesInLSB = 0;  // LSB = least significant bit.
            int numOnesInLSB = 0;
            for (int n : nums) {
                int isSameBit = (1 << bitIndex) & n;
                if (isSameBit == 0) {
                    numZeroesInLSB++;
                } else {
                    numOnesInLSB++;
                }
            }
            totalHammingDistance += numZeroesInLSB * numOnesInLSB;
        }
        return totalHammingDistance;
    }
}
