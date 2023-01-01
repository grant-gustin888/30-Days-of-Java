package InterviewProblems.MediumQuestions.Backtracking.CombinationSumII.BuggySolution1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSumII {

    // TODO: Insert time and space complexity analysis here.

    public static void main(String[] args) {
        CombinationSumII combinationSumII = new CombinationSumII();

        // Input: candidates = [10, 1, 2, 7, 6, 1, 5], target = 8
        // Output: [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]
        int[] candidates1 = {10, 1, 2, 7, 6, 1, 5};
        List<List<Integer>> combinationSum1 = combinationSumII.combinationSum2(candidates1, 8);
        System.out.println(combinationSum1);  // [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]
        combinationSumII.allCombinations = new HashSet<>();
        combinationSumII.currentCombination = new ArrayList<>();

        // Input: candidates = [2, 5, 2, 1, 2], target = 5
        // Output: [[1, 2, 2], [5]]
        int[] candidates2 = {2, 5, 2, 1, 2};
        List<List<Integer>> combinationSum2 = combinationSumII.combinationSum2(candidates2, 5);
        System.out.println(combinationSum2);  // [[1, 2, 2], [5]]
        combinationSumII.allCombinations = new HashSet<>();
        combinationSumII.currentCombination = new ArrayList<>();
    }

    Set<List<Integer>> allCombinations = new HashSet<>();  // only record unique combinations
    List<Integer> currentCombination = new ArrayList<>();
    // record indices to numbers, to account for duplicate numbers in different indices,
    // so you can store combinations like [1, 1, 6] in the first LeetCode example.

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        getCombinations(candidates, target, 0);
        return convertSetToList(allCombinations);  // process all elements in allCombinations at the end
    }

    private void getCombinations(int[] candidates, int target, int index) {
        if (index >= candidates.length) {
            return;  // cannot create any combinations with out of bounds array elements
        } else if (target < 0) {
            return;  // cannot create any combinations with negative target sum
        } else if (target == 0) {
            List<Integer> copyOfCurrentCombination = new ArrayList<>(currentCombination);
            allCombinations.add(copyOfCurrentCombination);
            return;
        } else {  // target > 0
            int currentElement = candidates[index];

            // include currentElement
            currentCombination.add(currentElement);
            getCombinations(candidates, target - currentElement, index + 1);

            // exclude currentElement
            currentCombination.remove(currentCombination.size() - 1);
            getCombinations(candidates, target, index + 1);
        }
    }

    private List<List<Integer>> convertSetToList(Set<List<Integer>> allCombinations) {
        List<List<Integer>> combinations = new ArrayList<>();
        for (List<Integer> combination : allCombinations) {
            combinations.add(combination);
        }
        return combinations;
//        for (List<Integer> combination : allCombinations) {
//            List<Integer> newCombination = new ArrayList<>();
//            for (Map.Entry<Integer, Integer> entry : combination.entrySet()) {
//                // int indexOfElement = entry.getKey();  // ignore index, it's to ensure we only use an element at most once.
//                int valueOfElement = entry.getValue();
//                newCombination.add(valueOfElement);
//            }
//            combinations.add(newCombination);
//        }
//        return combinations;
    }
}
