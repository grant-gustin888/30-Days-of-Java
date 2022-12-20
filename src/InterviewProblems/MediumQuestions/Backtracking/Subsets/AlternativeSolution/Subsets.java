package InterviewProblems.MediumQuestions.Backtracking.Subsets.AlternativeSolution;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    // Inspired by NeetCode's "subset" video:
    // https://www.youtube.com/watch?v=REOH22Xwdkk

    // Let n = the length of the nums array.
    //
    // Time: O(n * 2 ^ n)
    // --> We produce 2 ^ n subsets, and spend O(n) creating a copy of each subset
    // to add to our list of subsets.
    // Space: O(n)
    // --> We store up to n elements in the call stack when performing recursive calls.

    public static void main(String[] args) {
        Subsets subsets = new Subsets();

        // Input: nums = [0]
        // Output: [[], [0]]
        int[] nums2 = {0};
        List<List<Integer>> result2 = subsets.subsets(nums2);
        System.out.println(result2);  // [[], [0]]

        // Input: nums = [1, 2, 3]
        // Output: [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
        int[] nums1 = {1, 2, 3};
        List<List<Integer>> result = subsets.subsets(nums1);
        System.out.println(result);  // [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
    }

    List<List<Integer>> subsets = new ArrayList<>();
    List<Integer> currentSubset = new ArrayList<>();

    /**
     * Return all possible subsets (the power set).
     *
     * The solution set must not contain duplicate subsets.
     * Return the solution in any order.
     *
     * Preconditions:
     * - 1 <= nums.length <= 10.
     * - -10 <= nums[i] <= 10.
     * - All the numbers of nums are unique.
     */
    public List<List<Integer>> subsets(int[] nums) {
        generateSubsets(nums, 0);
        return subsets;
    }

    private void generateSubsets(int[] nums, int index) {
        if (index >= nums.length) {
            // We must create a new copy of the current subset since we will be modifying it
            subsets.add(new ArrayList<>(currentSubset));
            // NOT subsets.add(currentSubset); since this would create a reference to the same list
            return;
        }

        // Include the current element in the current subset.
        currentSubset.add(nums[index]);
        generateSubsets(nums, index + 1);

        // Exclude the current element in the current subset.
        currentSubset.remove(currentSubset.size() - 1);
        generateSubsets(nums, index + 1);
    }
}
