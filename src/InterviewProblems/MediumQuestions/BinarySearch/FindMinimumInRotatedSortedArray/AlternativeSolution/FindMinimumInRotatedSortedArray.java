package InterviewProblems.MediumQuestions.BinarySearch.FindMinimumInRotatedSortedArray.AlternativeSolution;

public class FindMinimumInRotatedSortedArray {

    // source: https://leetcode.com/submissions/detail/844089904/
    // "find minimum in rotated sorted array" > solutions

    // Let n = the length of the nums array.
    //
    // Time: O(log n)
    // --> We use modified binary search to find the minimum item in the array.
    // Space: O(1)
    // --> We only use 3 variables: leftIndex, rightIndex, and middleIndex.

    public static void main(String[] args) {
        FindMinimumInRotatedSortedArray findMinimumInRotatedSortedArray = new FindMinimumInRotatedSortedArray();

        // Input: nums = [3, 4, 5, 1, 2]
        // Output: 1
        // Explanation: The original array was [1, 2, 3, 4, 5] rotated 3 times.
        int[] nums1 = {3, 4, 5, 1, 2};
        int minimumItem1 = findMinimumInRotatedSortedArray.findMin(nums1);
        System.out.println(minimumItem1);  // 1

        // Input: nums = [4, 5, 6, 7, 0, 1, 2]
        // Output: 0
        // Explanation: The original array was [0, 1, 2, 4, 5, 6, 7] and it was rotated 4 times.
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int minimumItem2 = findMinimumInRotatedSortedArray.findMin(nums2);
        System.out.println(minimumItem2);  // 0

        // Input: nums = [11, 13, 15, 17]
        // Output: 11
        // Explanation: The original array was [11, 13, 15, 17] and it was rotated 4 times.
        int[] nums3 = {11, 13, 15, 17};
        int minimumItem3 = findMinimumInRotatedSortedArray.findMin(nums3);
        System.out.println(minimumItem3);  // 11

        // Input: [2, 3, 4, 5, 1]
        // Output: 1
        int[] nums4 = {2, 3, 4, 5, 1};
        int minimumItem4 = findMinimumInRotatedSortedArray.findMin(nums4);
        System.out.println(minimumItem4);  // 1

        // Input: [3, 1, 2]
        // Output: 1
        int[] nums5 = {3, 1, 2};
        int minimumItem5 = findMinimumInRotatedSortedArray.findMin(nums5);
        System.out.println(minimumItem5);  // 1
    }

    /**
     * Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
     * For example, the array nums = [0,1,2,4,5,6,7] might become:
     * - [4, 5, 6, 7, 0, 1, 2] if it was rotated 4 times.
     * - [0, 1, 2, 4, 5, 6, 7] if it was rotated 7 times.
     *
     * Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the
     * array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].
     *
     * Given the sorted rotated array nums of unique elements, return the minimum element of
     * this array.
     *
     * You must write an algorithm that runs in O(log n) time.
     *
     * Preconditions:
     * - n == nums.length.
     * - 1 <= n <= 5000.
     * - -5000 <= nums[i] <= 5000.
     * - All the integers of nums are unique.
     * - nums is sorted and rotated between 1 and n times.
     */
    public int findMin(int[] nums) {
         int leftIndex = 0;
         int rightIndex = nums.length - 1;
         while (leftIndex < rightIndex - 1) {
             int middleIndex = (leftIndex + rightIndex) / 2;
             if (nums[middleIndex] < nums[rightIndex]) {
                 // go left
                 rightIndex = middleIndex;
             } else if (nums[middleIndex] > nums[rightIndex]) {
                 // go right
                 leftIndex = middleIndex;
             }
         }
         return Math.min(nums[leftIndex], nums[rightIndex]);
    }
}
