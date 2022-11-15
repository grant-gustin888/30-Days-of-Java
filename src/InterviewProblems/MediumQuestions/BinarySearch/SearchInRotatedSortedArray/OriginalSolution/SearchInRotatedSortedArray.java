package InterviewProblems.MediumQuestions.BinarySearch.SearchInRotatedSortedArray.OriginalSolution;

public class SearchInRotatedSortedArray {

    // inspiration: https://www.youtube.com/embed/U8XENwh8Oy8

    // Let n = the length of the nums array.
    //
    // Time: O(log n)
    // --> We use modified binary search, except when target != nums[middleIndex],
    // we have to compare target against nums[middleIndex], nums[leftIndex], and nums[rightIndex]
    // to determine whether to search the left or right half of the array.
    // Space: O(1)
    // --> We only use 3 variables: leftIndex, rightIndex, and middleIndex.

    public static void main(String[] args) {
        SearchInRotatedSortedArray searchInRotatedSortedArray = new SearchInRotatedSortedArray();

        // Input: nums = [4, 5, 6, 7, 0, 1, 2], target = 0
        // Output: 4
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int index1 = searchInRotatedSortedArray.search(nums1, 0);
        System.out.println(index1);  // 4

        // Input: nums = [4, 5, 6, 7, 0, 1, 2], target = 3
        // Output: -1
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int index2 = searchInRotatedSortedArray.search(nums2, 3);
        System.out.println(index2);  // -1

        // Input: nums = [1], target = 0
        // Output: -1
        int[] nums3 = {1};
        int index3 = searchInRotatedSortedArray.search(nums3, 0);
        System.out.println(index3);  // -1
    }

    /**
     * There is an integer array nums sorted in ascending order (with distinct values).
     *
     * Prior to being passed to your function, nums is possibly rotated at an unknown
     * pivot index k (1 <= k < nums.length) such that the resulting array is
     * [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]
     * (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3
     * and become [4,5,6,7,0,1,2].
     *
     * Given the array nums after the possible rotation and an integer target,
     * return the index of target if it is in nums, or -1 if it is not in nums.
     *
     * You must write an algorithm with O(log n) runtime complexity.
     *
     * Preconditions:
     * - 1 <= nums.length <= 5000
     * - -10 ^ 4 <= nums[i] <= 10 ^ 4
     * - All values of nums are unique.
     * - nums is an ascending array that is possibly rotated.
     * - -10 ^ 4 <= target <= 10 ^ 4
     */
    public int search(int[] nums, int target) {
        int leftIndex = 0;
        int rightIndex = nums.length - 1;

        // we say leftIndex <= rightIndex because we still need to
        // check the array if leftIndex == rightIndex:
        // [0], leftIndex = 0 and rightIndex = 0...
        while (leftIndex <= rightIndex) {
            int middleIndex = (leftIndex + rightIndex) / 2;
            if (target == nums[middleIndex]) {
                return middleIndex;
            } else if (nums[leftIndex] <= nums[middleIndex]) {  // look in the left sorted portion of the array...
                if (target > nums[middleIndex] || target < nums[leftIndex]) {
                    leftIndex = middleIndex + 1;  // go right
                } else {  //
                    rightIndex = middleIndex - 1;  // go left
                }
            } else {  // nums[leftIndex] > nums[middleIndex], look in the right sorted portion of the array...
                if (target < nums[middleIndex] || target > nums[rightIndex]) {
                    rightIndex = middleIndex - 1;  // go left
                } else {
                    leftIndex = middleIndex + 1;  // go right
                }
            }
        }
        return -1;
    }
}
