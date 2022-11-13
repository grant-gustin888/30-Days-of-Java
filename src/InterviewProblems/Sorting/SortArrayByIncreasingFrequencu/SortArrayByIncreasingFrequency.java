package InterviewProblems.Sorting.SortArrayByIncreasingFrequencu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortArrayByIncreasingFrequency {

    // Let n = the length of the nums array
    //
    // Time: O(n log n)
    // --> O(n log n) to sort all numbers in the nums array according to a comparator function
    // Space: O(n)
    // --> O(n) for storing a hashmap

    // inspiration:
    // - https://leetcode.com/problems/sort-array-by-increasing-frequency/discuss/1092566/Java-Solution-using-custom-Sort
    // - https://leetcode.com/problems/sort-array-by-increasing-frequency/discuss/1380432/Java-oror-Easy-Approach-with-Explanation-oror-HashMap-oror-Heap(-min-%2B-max-)

    public static void main(String[] args) {
        SortArrayByIncreasingFrequency sortArrayByIncreasingFrequency = new SortArrayByIncreasingFrequency();

        // Input: nums = [1, 1, 2, 2, 2, 3]
        // Output: [3, 1, 1, 2, 2, 2]
        // Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.
        int[] nums1 = {1, 1, 2, 2, 2, 3};
        int[] sortedArray1 = sortArrayByIncreasingFrequency.frequencySort(nums1);
        System.out.println(Arrays.toString(sortedArray1));  // [3, 1, 1, 2, 2, 2]

        // Input: nums = [2, 3, 1, 3, 2]
        // Output: [1, 3, 3, 2, 2]
        // Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.
        int[] nums2 = {2, 3, 1, 3, 2};
        int[] sortedArray2 = sortArrayByIncreasingFrequency.frequencySort(nums2);
        System.out.println(Arrays.toString(sortedArray2));  // [1, 3, 3, 2, 2]

        // Input: nums = [-1, 1, -6, 4, 5, -6, 1, 4, 1]
        // Output: [5, -1, 4, 4, -6, -6, 1, 1, 1]
        int[] nums3 = {-1, 1, -6, 4, 5, -6, 1, 4, 1};
        int[] sortedArray3 = sortArrayByIncreasingFrequency.frequencySort(nums3);
        System.out.println(Arrays.toString(sortedArray3));  // [5, -1, 4, 4, -6, -6, 1, 1, 1]
    }

    /**
     * Given an array of integers nums, sort the array in increasing order based on the
     * frequency of the values. If multiple values have the same frequency, sort them in
     * decreasing order.
     *
     * Return the sorted array.
     *
     * Preconditions:
     * - 1 <= nums.length <= 100.
     * - -100 <= nums[i] <= 100.
     */
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> integerFrequencies = getIntegerFrequencies(nums);
        List<Map.Entry<Integer, Integer>> integerList = getSortedArrayByFrequency(integerFrequencies);
        return buildSortedArray(nums, integerList);
    }

    private Map<Integer, Integer> getIntegerFrequencies(int[] nums) {
        Map<Integer, Integer> integerFrequencies = new HashMap<>();
        for (int num : nums) {
            integerFrequencies.put(num, integerFrequencies.getOrDefault(num, 0) + 1);
        }
        return integerFrequencies;
    }

    private List<Map.Entry<Integer, Integer>> getSortedArrayByFrequency(Map<Integer, Integer> integerFrequencies) {
        List<Map.Entry<Integer, Integer>> integerList = new ArrayList<>(integerFrequencies.entrySet());
        integerList.sort((num1, num2) -> {
            if (num1.getValue() == num2.getValue()) {
                // sort keys in descending order.
                return num2.getKey() - num1.getKey();
            } else {
                // sort values in ascending order.
                return num1.getValue() - num2.getValue();
            }
        });
        return integerList;
    }

    private int[] buildSortedArray(int[] nums, List<Map.Entry<Integer, Integer>> integerList) {
        int sortedArrayIndex = 0;
        int[] sortedArray = new int[nums.length];
        for (Map.Entry<Integer, Integer> entry : integerList) {
            int integer = entry.getKey();
            int frequency = entry.getValue();
            for (int i = 0; i < frequency; i++) {
                sortedArray[sortedArrayIndex] = integer;
                sortedArrayIndex++;
            }
        }
        return sortedArray;
    }
}
