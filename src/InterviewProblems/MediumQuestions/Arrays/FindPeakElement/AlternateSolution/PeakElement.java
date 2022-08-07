package InterviewProblems.MediumQuestions.Arrays.FindPeakElement.AlternateSolution;

public class PeakElement {

    // source: https://leetcode.com/problems/find-peak-element/discuss/50232/Find-the-maximum-by-binary-search-(recursion-and-iteration)

    // Let n = the number of elements in the nums array.
    //
    // Time: O(log n)
    // --> We use binary search to find the peak element.
    // Space: O(1)
    // --> We only store three variables: startIndex, endIndex, and middleIndex.

    public static void main(String[] args) {
        PeakElement peakElement = new PeakElement();

        // Input: nums = [1]
        // Output: 0
        int[] nums1 = {1};
        int peakElement1 = peakElement.findPeakElement(nums1);
        System.out.println(peakElement1);  // 0

        // Input: nums = [1, 2]
        // Output: 1
        int[] nums2 = {1, 2};
        int peakElement2 = peakElement.findPeakElement(nums2);
        System.out.println(peakElement2);  // 1

        // Input: nums = [2, 1]
        // Output: 0
        int[] nums3 = {2, 1};
        int peakElement3 = peakElement.findPeakElement(nums3);
        System.out.println(peakElement3);  // 0

        // Input: nums = [1, 2, 1]
        // Output: 1
        int[] nums4 = {1, 2, 1};
        int peakElement4 = peakElement.findPeakElement(nums4);
        System.out.println(peakElement4);  // 1

        // Input: nums = [1, 2, 3, 1]
        // Output: 2
        // Explanation: 3 is a peak element and your function should return
        // the index number 2.
        int[] nums5 = {1, 2, 3, 1};
        int peakElement5 = peakElement.findPeakElement(nums5);
        System.out.println(peakElement5);  // 2

        // Input: nums = [1, 2, 1, 3, 5, 6, 4]
        // Output: 5
        // Explanation: Your function can return either index number 1 where
        // the peak element is 2, or index number 5 where the peak element is 6.
        int[] nums6 = {1, 2, 1, 3, 5, 6, 4};
        int peakElement6 = peakElement.findPeakElement(nums6);
        System.out.println(peakElement6);  // 5

        // Input: nums = [4, 5, 1, 2, 3, 4, 5, 6]
        // Output: 7
        int[] nums7 = {4, 5, 1, 2, 3, 4, 5, 6};
        int peakElement7 = peakElement.findPeakElement(nums7);
        System.out.println(peakElement7);  // 7
    }

    /**
     * A peak element is an element that is strictly greater than its neighbors.<br><br>
     *
     * Given a 0-indexed integer array nums, find a peak element, and return its
     * index. If the array contains multiple peaks, return the index to any of the peaks.<br><br>
     *
     * You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is
     * always considered to be strictly greater than a neighbor that is outside the array.<br><br>
     *
     * You must write an algorithm that runs in O(log n) time.<br><br>
     *
     * Preconditions:<br>
     * - 1 <= nums.length <= 1000.<br>
     * - -2 ^ 31 <= nums[i] <= 2 ^ 31 - 1.<br>
     * - nums[i] != nums[i + 1] for all valid i.
     */
    public int findPeakElement(int[] nums) {
        int startIndex = 0;
        int endIndex = nums.length - 1;
        while (startIndex + 2 <= endIndex) {
            int middleIndex = (startIndex + endIndex) / 2;  // or startIndex + (endIndex - startIndex) / 2;
            if (nums[middleIndex] > nums[middleIndex - 1]) {
                startIndex = middleIndex;
            } else {  // nums[middleIndex] <= nums[middleIndex - 1]
                endIndex = middleIndex;
            }
        }

        if (nums[startIndex] >= nums[endIndex]) {
            return startIndex;
        } else {
            return endIndex;
        }
    }
}
