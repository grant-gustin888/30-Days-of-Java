package InterviewProblems.MediumQuestions.Backtracking.SubsetII;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SubsetII {

    // Let n = the length of the nums array
    //
    // Time: O(n * 2 ^ n)
    // --> O(n * 2 ^ n) to create a copy of each subset (in O(n) time each) for all
    // subsets (in O(2 ^ n) time)
    // --> O(n) to convert from a subset map to a subset list
    // Space: O(n * 2 ^ n)
    // --> O(n * 2 ^ n) to store all subsets (in O(n) space each) for all subsets (in
    // O(2 ^ n) space)
    // --> O(n) to store the new subset list

    public static void main(String[] args) {
        SubsetII subsetSumII = new SubsetII();

        // Input: nums = [0]
        // Output: [[], [0]]
        int[] nums1 = {0};
        List<List<Integer>> subsets1 = subsetSumII.subsetsWithDup(nums1);
        System.out.println(subsets1);  // [[], [0]]
        subsetSumII.allSubsets = new HashSet<>();
        subsetSumII.currentSubset = new HashMap<>();

        // Input: nums = [1, 2]
        // Output: [[], [1], [1, 2], [2]]
        int[] nums2 = {1, 2};
        List<List<Integer>> subsets2 = subsetSumII.subsetsWithDup(nums2);
        System.out.println(subsets2);  // [[], [1], [1, 2], [2]]
        subsetSumII.allSubsets = new HashSet<>();
        subsetSumII.currentSubset = new HashMap<>();

        // Input: nums = [1,2,2]
        // Output: [[], [1], [1, 2], [1, 2, 2], [2], [2, 2]]
        int[] nums3 = {1, 2, 2};
        List<List<Integer>> subsets3 = subsetSumII.subsetsWithDup(nums3);
        System.out.println(subsets3);  // [[], [1], [1, 2], [1, 2, 2], [2], [2, 2]]
        subsetSumII.allSubsets = new HashSet<>();
        subsetSumII.currentSubset = new HashMap<>();
    }

    Set<Map<Integer, Integer>> allSubsets = new HashSet<>();
    Map<Integer, Integer> currentSubset = new HashMap<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        generateSubsetsWithDuplicates(nums, 0);
        return convertMapToList(allSubsets);
    }

    private void generateSubsetsWithDuplicates(int[] nums, int index) {
        if (index >= nums.length) {
            Map<Integer, Integer> copyOfCurrentSubset = new HashMap<>(currentSubset);
            allSubsets.add(copyOfCurrentSubset);
            return;
        }

        // include nums[index]
        currentSubset.put(nums[index], currentSubset.getOrDefault(nums[index], 0) + 1);
        generateSubsetsWithDuplicates(nums, index + 1);

        // exclude nums[index]
        currentSubset.put(nums[index], currentSubset.get(nums[index]) - 1);
        if (currentSubset.get(nums[index]) <= 0) {
            currentSubset.remove(nums[index]);
        }
        generateSubsetsWithDuplicates(nums, index + 1);
    }

    private List<List<Integer>> convertMapToList(Set<Map<Integer, Integer>> subsets) {
        List<List<Integer>> newSubsetsAsList = new ArrayList<>();
        for (Map<Integer, Integer> subset : subsets) {
            List<Integer> newSubset = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : subset.entrySet()) {
                int key = entry.getKey();
                int frequency = entry.getValue();
                for (int i = 0; i < frequency; i++) {
                    newSubset.add(key);
                }
            }
            newSubsetsAsList.add(newSubset);
        }
        return newSubsetsAsList;
    }
}
