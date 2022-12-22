package InterviewProblems.MediumQuestions.Backtracking.Subsets.AlternativeSolution2ndTry;

import java.util.ArrayList;
import java.util.List;

public class Subsets {

    public static void main(String[] args) {
        Subsets subsets = new Subsets();

        // Input: nums = [0]
        // Output: [[], [0]]
        int[] nums1 = {0};
        List<List<Integer>> subsets1 = subsets.subsets(nums1);
        System.out.println(subsets1);  // [[], [0]]
        subsets.allSubsets = new ArrayList<>();
        subsets.currentSubset = new ArrayList<>();

        // Input: nums = [1, 2, 3]
        // Output: [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
        int[] nums2 = {1, 2, 3};
        List<List<Integer>> subsets2 = subsets.subsets(nums2);
        System.out.println(subsets2);  // [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
        subsets.allSubsets = new ArrayList<>();
        subsets.currentSubset = new ArrayList<>();

        // Input: nums = [1, 1, 1]
        // Output: [[], [1], [1], [1], [1, 1], [1, 1], [1, 1], [1, 1, 1]]
        int[] nums3 = {1, 1, 1};
        List<List<Integer>> subsets3 = subsets.subsets(nums3);
        System.out.println(subsets3);  // [[1, 1, 1], [1, 1], [1, 1], [1], [1, 1], [1], [1], []]
        subsets.allSubsets = new ArrayList<>();
        subsets.currentSubset = new ArrayList<>();
    }

    List<List<Integer>> allSubsets = new ArrayList<>();
    List<Integer> currentSubset = new ArrayList<>();

    /**
     * Given an integer array nums of unique elements, return all possible
     * subsets (the power set).
     *
     * The solution set must not contain duplicate subsets. Return the solution in any order.
     *
     * Preconditions:
     * - 1 <= nums.length <= 10
     * - -10 <= nums[i] <= 10
     * - All the numbers of nums are unique.
     */
    public List<List<Integer>> subsets(int[] nums) {
        // List<Integer> emptySubset = new ArrayList<>();  don't need this part, because dfs already includes empty set...
        // allSubsets.add(emptySubset);
        dfs(nums, 0);
        return allSubsets;
    }

    private void dfs(int[] nums, int index) {
        if (index >= nums.length) {
            List<Integer> copyOfCurrentSubset = new ArrayList<>(currentSubset);  // needed help here
            allSubsets.add(copyOfCurrentSubset);
            return;
        }

        // include nums[index] in the current subset
        currentSubset.add(nums[index]);
        dfs(nums, index + 1);

        // exclude nums[index] in the current subset
        currentSubset.remove(currentSubset.size() - 1);
        dfs(nums, index + 1);
    }
}
