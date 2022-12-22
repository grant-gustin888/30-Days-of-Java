package InterviewProblems.MediumQuestions.Backtracking.Permutations.RecursiveSolution2;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

    // source, heaps algorithm: https://www.baeldung.com/cs/array-generate-all-permutations

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

        generatePermutations(currentPermutation, nums.length);
        return allPermutations;
    }

    private void generatePermutations(List<Integer> currentPermutation, int sizeOfArray) {
        if (sizeOfArray <= 1) {
            List<Integer> copyOfCurrentPermutation = new ArrayList<>(currentPermutation);
            allPermutations.add(copyOfCurrentPermutation);
            return;
        }

        // generate all permutations using the remaining elements
        for (int i = 0; i < sizeOfArray; i++) {
            // get all permutations of size n - 1
            generatePermutations(currentPermutation, sizeOfArray - 1);

            // if size is odd, swap 0th and (size - 1)th element
            // else if size is even, swap ith and (size - 1)th element
            if (sizeOfArray % 2 == 1) {
                swap(currentPermutation, 0, sizeOfArray - 1);
            } else {
                swap(currentPermutation, i, sizeOfArray - 1);
            }
        }
    }

    private void swap(List<Integer> currentPermutation, int index1, int index2) {
        int temp = currentPermutation.get(index1);
        currentPermutation.set(index1, currentPermutation.get(index2));
        currentPermutation.set(index2, temp);
    }
}
