package InterviewProblems.PriorityQueues.SortArrayByIncreasingFrequency;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SortArrayByIncreasingFrequency {

    // Let n = the length of the nums array
    //
    // Time: O(n log n)
    // --> O(n log n) to add all numbers from the nums array to the priority queue
    // --> O(n log n) to remove all numbers from the priority queue
    // Space: O(n)
    // --> O(n) for storing a heap

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
        // count up number of each number
        Map<Integer, Integer> frequencyOfEachNumber = getFrequencies(nums);

        // sort the numbers by their frequency
        PriorityQueue<Integer> frequenciesOfNumbers = getNumsByFrequency(frequencyOfEachNumber);
        for (int num : nums) {
            frequenciesOfNumbers.add(num);
        }

        // pop from PQ to get sorted array
        int i = 0;
        while (!frequenciesOfNumbers.isEmpty()) {
            nums[i] = frequenciesOfNumbers.poll();
            i++;
        }
        return nums;
    }

    private Map<Integer, Integer> getFrequencies(int[] nums) {
        Map<Integer, Integer> frequencyOfEachNumber = new HashMap<>();
        for (int num : nums) {
            if (frequencyOfEachNumber.containsKey(num)) {
                frequencyOfEachNumber.put(num, frequencyOfEachNumber.get(num) + 1);
            } else {
                frequencyOfEachNumber.put(num, 1);
            }
        }
        return frequencyOfEachNumber;
    }

    private PriorityQueue<Integer> getNumsByFrequency(Map<Integer, Integer> frequencyOfEachNumber) {
        PriorityQueue<Integer> frequenciesOfNumbers = new PriorityQueue<>(
            (num1, num2) -> {
                int num1Frequency = frequencyOfEachNumber.get(num1);
                int num2Frequency = frequencyOfEachNumber.get(num2);
                if (num1Frequency == num2Frequency) {
                    // sort keys in descending order.
                    return num2 - num1;
                } else {
                    // sort values in ascending order.
                    return num1Frequency - num2Frequency;
                }
            }
        );
        return frequenciesOfNumbers;
    }
}
