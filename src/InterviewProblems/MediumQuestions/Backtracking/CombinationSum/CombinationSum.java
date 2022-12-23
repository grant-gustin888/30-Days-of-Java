package InterviewProblems.MediumQuestions.Backtracking.CombinationSum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CombinationSum {

    // todo: insert time and space complexity analysis here

    public static void main(String[] args) {
        CombinationSum combinationSum = new CombinationSum();

        // Input: candidates = [2], target = 1
        // Output: []
        int[] candidates1 = {2};
        List<List<Integer>> combinations1 = combinationSum.combinationSum(candidates1, 1);
        System.out.println(combinations1);  // []
        combinationSum.allCombinations = new HashSet<>();
        combinationSum.currentCombination = new HashMap<>();

        // Input: candidates = [2, 3, 5], target = 8
        // Output: [[2, 2, 2, 2], [2, 3, 3], [3, 5]]
        int[] candidates2 = {2, 3, 5};
        List<List<Integer>> combinations2 = combinationSum.combinationSum(candidates2, 8);
        System.out.println(combinations2);  // [[2, 2, 2, 2], [2, 3, 3], [3, 5]]
        combinationSum.allCombinations = new HashSet<>();
        combinationSum.currentCombination = new HashMap<>();

        // Input: candidates = [2, 3, 6, 7], target = 7
        // Output: [[2, 2, 3], [7]]
        // Explanation:
        // 2 and 3 are candidates, and 2 + 2 + 3 = 7.
        // Note that 2 can be used multiple times.
        // 7 is a candidate, and 7 = 7.
        // These are the only two combinations.
        int[] candidates3 = {2, 3, 6, 7};
        List<List<Integer>> combinations3 = combinationSum.combinationSum(candidates3, 7);
        System.out.println(combinations3);  // [[2, 2, 3], [7]]
        combinationSum.allCombinations = new HashSet<>();
        combinationSum.currentCombination = new HashMap<>();
    }

    // List<List<Integer>> allCombinations = new ArrayList<>();
    Set<Map<Integer, Integer>> allCombinations = new HashSet<>();
    // List<Integer> currentCombination = new ArrayList<>();
    Map<Integer, Integer> currentCombination = new HashMap<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        generateCombinations(candidates, target);
        return convertMapToList(allCombinations);
    }

    private void generateCombinations(int[] candidates, int remaining) {
        if (remaining == 0) {
            // create a copy of the currentCombination array
            // List<Integer> copyOfCurrentCombination = new ArrayList<>(currentCombination);
            Map<Integer, Integer> copyOfCurrentCombination = new HashMap<>(currentCombination);

            // how to detect the same combination? -- using a hashset!
            allCombinations.add(copyOfCurrentCombination);
            return;
        } else if (remaining < 0) {
            return;  // dead end, no combination produces this sum
        } else {  // remaining > 0
            for (int num : candidates) {
                currentCombination.put(num, currentCombination.getOrDefault(num, 0) + 1);
                // currentCombination.add(num);
                generateCombinations(candidates, remaining - num);
                // currentCombination.remove(currentCombination.size() - 1);
                currentCombination.put(num, currentCombination.get(num) - 1);
                if (currentCombination.get(num) <= 0) {
                    currentCombination.remove(num);
                }
            }
        }
    }

    private List<List<Integer>> convertMapToList(Set<Map<Integer, Integer>> allCombinations) {
        List<List<Integer>> combinations = new ArrayList<>();
        for (Map<Integer, Integer> combination : allCombinations) {
            List<Integer> newCombination = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : combination.entrySet()) {
                int key = entry.getKey();
                int frequency = entry.getValue();
                for (int i = 0; i < frequency; i++) {
                    newCombination.add(key);
                }
            }
            combinations.add(newCombination);
        }
        return combinations;
    }
}
