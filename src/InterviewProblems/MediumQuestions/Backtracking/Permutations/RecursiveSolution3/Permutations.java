package InterviewProblems.MediumQuestions.Backtracking.Permutations.RecursiveSolution3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Permutations {

    public static void main(String[] args) {
        Permutations permutations = new Permutations();

        // Input: nums = [1]
        // Output: [[1]]
        int[] nums1 = {1};
        List<List<Integer>> permutations1 = permutations.permute(nums1);
        System.out.println(permutations1);  // [[1]]
        permutations.allPermutations = new ArrayList<>();
        permutations.currentPermutation = new ArrayList<>();

        // Input: nums = [0, 1]
        // Output: [[0, 1], [1, 0]]
        int[] nums2 = {0, 1};
        List<List<Integer>> permutations2 = permutations.permute(nums2);
        System.out.println(permutations2);  // [[0, 1], [1, 0]]
        permutations.allPermutations = new ArrayList<>();
        permutations.currentPermutation = new ArrayList<>();

        // Input: nums = [1, 2, 3]
        // Output: [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
        int[] nums3 = {1, 2, 3};
        List<List<Integer>> permutations3 = permutations.permute(nums3);
        System.out.println(permutations3);  // [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
        permutations.allPermutations = new ArrayList<>();
        permutations.currentPermutation = new ArrayList<>();
    }

    List<List<Integer>> allPermutations = new ArrayList<>();
    List<Integer> currentPermutation = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        for (int n : nums) {
            currentPermutation.add(n);
        }
        generatePermutations(currentPermutation, 0, nums.length - 1);
        return allPermutations;
    }

    private void generatePermutations(List<Integer> currentPermutation, int startIndex, int endIndex) {
        if (startIndex == endIndex) {
            List<Integer> copyOfCurrentPermutation = new ArrayList<>(currentPermutation);
            allPermutations.add(copyOfCurrentPermutation);
            return;
        }

        for (int i = startIndex; i <= endIndex; i++) {
            Collections.swap(currentPermutation, startIndex, i);
            generatePermutations(currentPermutation, startIndex + 1, endIndex);
            Collections.swap(currentPermutation, startIndex, i);  // backtrack
        }
    }
}
