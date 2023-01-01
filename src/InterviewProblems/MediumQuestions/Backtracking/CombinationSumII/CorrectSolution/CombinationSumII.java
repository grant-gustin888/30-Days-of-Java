package InterviewProblems.MediumQuestions.Backtracking.CombinationSumII.CorrectSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {

    // inspiration from neetcode (sorting input array first and skipping duplicate elements): https://neetcode.io/practice
    // and kevin naughton jr: https://www.youtube.com/watch?v=IER1ducXujU

    // TODO: Insert time and space complexity analysis here.

    public static void main(String[] args) {
        CombinationSumII combinationSumII = new CombinationSumII();

        // Input: candidates = [10, 1, 2, 7, 6, 1, 5], target = 8
        // Output: [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]
        int[] candidates1 = {10, 1, 2, 7, 6, 1, 5};
        List<List<Integer>> combinationSum1 = combinationSumII.combinationSum2(candidates1, 8);
        System.out.println(combinationSum1);  // [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]

        // Input: candidates = [2, 5, 2, 1, 2], target = 5
        // Output: [[1, 2, 2], [5]]
        int[] candidates2 = {2, 5, 2, 1, 2};
        List<List<Integer>> combinationSum2 = combinationSumII.combinationSum2(candidates2, 5);
        System.out.println(combinationSum2);  // [[1, 2, 2], [5]]
    }

    // for some reason, this solution produces duplicate combinations, even though the code looks
    // almost identical to the sample NeetCode solution.
//    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//        Arrays.sort(candidates);
//        List<List<Integer>> allCombinations = new ArrayList<>();
//        List<Integer> currentCombination = new ArrayList<>();
//        getCombinations(candidates, target, allCombinations, currentCombination, 0);
//        return allCombinations;
//    }
//
//    public void getCombinations(int[] candidates, int target, List<List<Integer>> allCombinations, List<Integer> currentCombination, int index) {
//        if (target == 0) {
//            allCombinations.add(new ArrayList<>(currentCombination));
//        } else if (target < 0) {
//            return;
//        } else {  // target > 0
//            for (int i = index; i < candidates.length; i++) {
//                if (i > index && candidates[i] == candidates[i - 1]) {
//                    continue;
//                }
//
//                // include currentElement
//                currentCombination.add(candidates[i]);
//                getCombinations(candidates, target - candidates[i], allCombinations, currentCombination, index + 1);
//
//                // exclude currentElement
//                currentCombination.remove(currentCombination.size() - 1);
//            }
//        }
//    }

    /**
     * Given a collection of candidate numbers (candidates) and a target number (target),
     * find all unique combinations in candidates where the candidate numbers sum to target.
     *
     * Each number in candidates may only be used once in the combination.
     *
     * Note: The solution set must not contain duplicate combinations.
     *
     * Preconditions:
     * - 1 <= candidates.length <= 100
     * - 1 <= candidates[i] <= 50
     * - 1 <= target <= 30
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> allCombinations = new ArrayList<>();
        List<Integer> currentCombination = new ArrayList<>();
        getCombinations(candidates, target, allCombinations, currentCombination, 0);
        return allCombinations;
    }

    public void getCombinations(int[] candidates, int target, List<List<Integer>> allCombinations,
                                List<Integer> currentCombination, int index) {
        if (target == 0) {
            allCombinations.add(new ArrayList<>(currentCombination));
        } else if (target < 0) {
            return;
        } else {
            for (int i = index; i < candidates.length; i++) {
                if (i > index && candidates[i] == candidates[i - 1]) {
                    continue;
                }

                currentCombination.add(candidates[i]);
                getCombinations(candidates, target - candidates[i], allCombinations, currentCombination, i + 1);
                currentCombination.remove(currentCombination.size() - 1);
            }
        }
    }
}
