package InterviewProblems.TwoPointers.IntersectionOfTwoArrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayIntersection {

    // Let m = the length of nums1, n = the length of nums2.
    //
    // Time: O(m log m + n log n)
    // --> O(m log m) for sorting nums1
    // --> O(n log n) for sorting nums2
    // --> O(m + n) In the worst case, we only move one pointer, one index at a time.
    // so that we traverse until the end of both the nums1 and nums2 arrays.
    //
    // Space: O(min(m, n))
    // --> The intersection can contain any subset of elements in nums1 and nums2.

    public static void main(String[] args) {
        ArrayIntersection intersection = new ArrayIntersection();

        // Input: nums1 = [1, 2, 2, 1], nums2 = [2, 2]
        // Output: [2, 2]
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        int[] result = intersection.intersect(nums1, nums2);
        System.out.println(Arrays.toString(result));  // [2, 2]

        // Input: nums1 = [4, 9, 5], nums2 = [9, 4, 9, 8, 4]
        // Output: [4, 9]
        // Explanation: [9, 4] is also accepted.
        int[] nums3 = {4, 9, 5};
        int[] nums4 = {9, 4, 9, 8, 4};
        int[] result2 = intersection.intersect(nums3, nums4);
        System.out.println(Arrays.toString(result2));  // [4, 9]

        // Input: nums1 = [1, 1, 1], nums2 = [1, 1, 1]
        // Output: [1, 1, 1]
        int[] nums5 = {1, 1, 1};
        int[] nums6 = {1, 1, 1};
        int[] result3 = intersection.intersect(nums5, nums6);
        System.out.println(Arrays.toString(result3));  // [1, 1, 1]

        // Input: nums1 = [1, 1, 1], nums2 = [2, 2, 2]
        // Output: []
        int[] nums7 = {1, 1, 1};
        int[] nums8 = {2, 2, 2};
        int[] result4 = intersection.intersect(nums7, nums8);
        System.out.println(Arrays.toString(result4));  // []

        // Follow-up questions:
        // 1. What if the given array is already sorted? How would you optimize your algorithm?
        // 2. What if nums1's size is small compared to nums2's size? Which algorithm is better?
        // 3. What if elements of nums2 are stored on disk, and the memory is limited such that
        // you cannot load all elements into the memory at once?
    }

    /**
     * Given two integer arrays nums1 and nums2, return an array of their
     * intersection. Each element in the result must appear as many times
     * as it shows in both arrays and you may return the result in any order.
     *
     * Preconditions:
     * - 1 <= nums1.length, nums2.length <= 1000
     * = 0 <= nums1[i], nums2[i] <= 1000
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int nums1Index = 0;
        int nums2Index = 0;
        List<Integer> intersection = new ArrayList<>();
        while (nums1Index < nums1.length && nums2Index < nums2.length) {
            if (nums1[nums1Index] == nums2[nums2Index]) {
                intersection.add(nums1[nums1Index]);
                nums1Index++;
                nums2Index++;
            } else if (nums1[nums1Index] > nums2[nums2Index]) {
                nums2Index++;
            } else {  // nums1[nums1Index] < nums2[nums2Index]
                nums1Index++;
            }
        }

        int[] intersectionArray = new int[intersection.size()];
        for (int i = 0; i < intersection.size(); i++) {
            intersectionArray[i] = intersection.get(i);
        }
        return intersectionArray;
    }
}
