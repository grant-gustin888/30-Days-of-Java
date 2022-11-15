package InterviewProblems.MediumQuestions.BinarySearch.SearchInRotatedSortedArray.AlternativeSolution;

public class SearchInRotatedSortedArray {

    // from NeetCode > Binary Search > Search In Rotated Sorted Array solutions:
    // https://neetcode.io/practice

    // Let n = the length of the nums array.
    //
    // Time: O(log n)
    // --> O(log n) to find the minimum element in the nums array using binary search
    // --> O(log n) for 2 calls to binary search on the left and right halves of the array
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
        int minIndex = minIndex(nums);
        int leftSearch = binarySearch(nums, 0, minIndex - 1, target);
        int rightSearch = binarySearch(nums, minIndex, nums.length - 1, target);
        if (leftSearch != -1) {
            return leftSearch;
        } else if (rightSearch != -1) {
            return rightSearch;
        } else {
            return -1;
        }
    }

    public int minIndex(int[] nums) {
        int leftIndex = 0;
        int rightIndex = nums.length - 1;
        while (leftIndex <= rightIndex) {
            int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
            int previousIndex = (middleIndex - 1 + nums.length) % nums.length;
            int nextIndex = (middleIndex + 1) % nums.length;
            if (nums[middleIndex] <= nums[previousIndex] && nums[middleIndex] <= nums[nextIndex]) {
                return middleIndex;
            } else if (nums[middleIndex] <= nums[rightIndex]) {
                rightIndex = middleIndex - 1;
            } else if (nums[middleIndex] >= nums[leftIndex]) {
                leftIndex = middleIndex + 1;
            }
        }
        return -1;
    }

    public int binarySearch(int[] nums, int leftIndex, int rightIndex, int target) {
        while (leftIndex <= rightIndex) {
            int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
            if (nums[middleIndex] == target) {
                return middleIndex;
            } else if (nums[middleIndex] < target) {
                leftIndex = middleIndex + 1;
            } else {
                rightIndex = middleIndex - 1;
            }
        }
        return -1;
    }
}
