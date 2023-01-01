package InterviewProblems.MediumQuestions.Backtracking.CombinationSumII.BuggySolution2;

import java.util.*;

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
        combinationSumII.currentCombination = new HashMap<>();

        // Input: candidates = [2, 5, 2, 1, 2], target = 5
        // Output: [[1, 2, 2], [5]]
        int[] candidates2 = {2, 5, 2, 1, 2};
        List<List<Integer>> combinationSum2 = combinationSumII.combinationSum2(candidates2, 5);
        System.out.println(combinationSum2);  // [[1, 2, 2], [5]]
        combinationSumII.allCombinations = new HashSet<>();
        combinationSumII.currentCombination = new HashMap<>();
    }

    Set<Map<Integer, Integer>> allCombinations = new HashSet<>();
    Map<Integer, Integer> currentCombination = new HashMap<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        getCombinations(candidates, target, 0);
        return convertSetToList(allCombinations);
    }

    private void getCombinations(int[] candidates, int target, int index) {
        if (index >= candidates.length) {
            return;  // out of bounds
        } else if (target < 0) {
            return;  // negative target sum
        } else if (target == 0) {
            Map<Integer, Integer> copyOfCurrentCombination = new HashMap<>(currentCombination);
            allCombinations.add(copyOfCurrentCombination);
            return;
        } else {
            int currentElement = candidates[index];

            // include currentElement
            currentCombination.put(currentElement, currentCombination.getOrDefault(currentElement, 0) + 1);
            getCombinations(candidates, target - currentElement, index + 1);

            // exclude currentElement
            currentCombination.put(currentElement, currentCombination.get(currentElement) - 1);
            if (currentCombination.get(currentElement) == 0) {
                currentCombination.remove(currentElement);
            }
            getCombinations(candidates, target, index + 1);
        }
    }

    private List<List<Integer>> convertSetToList(Set<Map<Integer, Integer>> allCombinations) {
        List<List<Integer>> result = new ArrayList<>();
        for (Map<Integer, Integer> combination : allCombinations) {
            List<Integer> combinationList = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : combination.entrySet()) {
                int element = entry.getKey();
                int frequency = entry.getValue();
                for (int i = 0; i < frequency; i++) {
                    combinationList.add(element);
                }
            }
            result.add(combinationList);
        }
        return result;
    }
}
