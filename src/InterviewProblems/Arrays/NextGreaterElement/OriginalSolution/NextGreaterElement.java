package InterviewProblems.Arrays.NextGreaterElement.OriginalSolution;

import java.util.Arrays;

public class NextGreaterElement {

    // let m = nums1.length, n = nums2.length

    // Time: O(m * n)
    // --> We compare each element in nums1 to each element in nums2 by performing
    // linear search on nums2 on each element in nums1.
    // Space: O(m)
    // --> We store the next greater element for each element in nums1 in an array.

    public static void main(String[] args) {
        NextGreaterElement nextGreaterElement = new NextGreaterElement();

        // Example 1:
        //
        // Input: nums1 = [4, 1, 2], nums2 = [1, 3, 4, 2]
        // Output: [-1, 3, -1]
        // Explanation: The next greater element for each value of nums1 is as follows:
        // - 4 is underlined in nums2 = [1, 3, 4, 2]. There is no next greater element, so the answer is -1.
        // - 1 is underlined in nums2 = [1, 3, 4, 2]. The next greater element is 3.
        // - 2 is underlined in nums2 = [1, 3, 4, 2]. There is no next greater element, so the answer is -1.
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        int[] result = nextGreaterElement.nextGreaterElement(nums1, nums2);
        System.out.println(Arrays.toString(result));  // [-1, 3, -1]

        // Example 2:
        //
        // Input: nums1 = [2, 4], nums2 = [1, 2, 3, 4]
        // Output: [3, -1]
        // Explanation: The next greater element for each value of nums1 is as follows:
        // - 2 is underlined in nums2 = [1, 2, 3, 4]. The next greater element is 3.
        // - 4 is underlined in nums2 = [1, 2, 3, 4]. There is no next greater element, so the answer is -1.
        int[] nums3 = {2, 4};
        int[] nums4 = {1, 2, 3, 4};
        int[] result2 = nextGreaterElement.nextGreaterElement(nums3, nums4);
        System.out.println(Arrays.toString(result2));  // [3, -1]
    }

    /**
     * The next greater element of some element x in an array is the first greater element
     * that is to the right of x in the same array.
     *
     * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1
     * is a subset of nums2.
     *
     * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j]
     * and determine the next greater element of nums2[j] in nums2.
     * If there is no next greater element, then the answer for this query is -1.
     *
     * Return an array ans of length nums1.length such that ans[i] is the next greater
     * element as described above.
     *
     * Preconditions:
     * - 1 <= nums1.length <= nums2.length <= 1000
     * - 0 <= nums1[i], nums2[i] <= 104
     * - All integers in nums1 and nums2 are unique.
     * - All the integers of nums1 also appear in nums2.
     */

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] firstIndexGreaterThan = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {  // O(m)
            int currentNumIndex = linearSearch(nums2, nums1[i]);  // O(n)

            int number = nums2[currentNumIndex];
            int j;
            for (j = currentNumIndex; j < nums2.length; j++) {  // O(n)
                if (nums2[j] > number) {
                    firstIndexGreaterThan[i] = nums2[j];
                    break;
                }
            }

            if (j == nums2.length) {
                firstIndexGreaterThan[i] = -1;
            }
        }
        return firstIndexGreaterThan;
    }

    private int linearSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
