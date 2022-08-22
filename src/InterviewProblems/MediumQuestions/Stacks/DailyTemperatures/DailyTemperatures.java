package InterviewProblems.MediumQuestions.Stacks.DailyTemperatures;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperatures {

    // Let n = the length of the nums array.
    //
    // Time: O(n)
    // --> We must traverse the array once.
    // Space: O(n)
    // --> We may have to store all n temperatures of the days in the stack if the
    // nums array is listed in descending order.

    public static void main(String[] args) {
        DailyTemperatures dailyTemperatures = new DailyTemperatures();

        // Input: temperatures = [30, 60, 90]
        // Output: [1, 1, 0]
        int[] temperatures1 = {30, 60, 90};
        int[] dailyTemperatures1 = dailyTemperatures.dailyTemperatures(temperatures1);
        System.out.println(Arrays.toString(dailyTemperatures1));  // [1, 1, 0]

        // Input: temperatures = [30, 40, 50, 60]
        // Output: [1, 1, 1, 0]
        int[] temperatures2 = {30, 40, 50, 60};
        int[] dailyTemperatures2 = dailyTemperatures.dailyTemperatures(temperatures2);
        System.out.println(Arrays.toString(dailyTemperatures2));  // [1, 1, 1, 0]

        // Input: temperatures = [4, 3, 2, 1]
        // Output: [0, 0, 0, 0]
        int[] temperatures3 = {4, 3, 2, 1};
        int[] dailyTemperatures3 = dailyTemperatures.dailyTemperatures(temperatures3);
        System.out.println(Arrays.toString(dailyTemperatures3));  // [0, 0, 0, 0]

        // Input: temperatures = [73, 74, 75, 71, 69, 72, 76, 73]
        // Output: [1, 1, 4, 2, 1, 1, 0, 0]
        int[] temperatures4 = {73, 74, 75, 71, 69, 72, 76, 73};
        int[] dailyTemperatures4 = dailyTemperatures.dailyTemperatures(temperatures4);
        System.out.println(Arrays.toString(dailyTemperatures4));  // [1, 1, 4, 2, 1, 1, 0, 0]
    }

    /**
     * Given an array of integers temperatures represents the daily temperatures, return an
     * array answer such that answer[i] is the number of days you have to wait after the ith
     * day to get a warmer temperature.<br><br>
     *
     * If there is no future day for which this is possible, keep answer[i] == 0 instead.<br><br>
     *
     * Preconditions:<br>
     * - 1 <= temperatures.length <= 10 ^ 5.<br>
     * - 30 <= temperatures[i] <= 100.
     */
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stackOfIndices = new Stack<>();
        int[] dailyTemperatures = new int[temperatures.length];
        for (int currentDay = 0; currentDay < temperatures.length; currentDay++) {
            while (!stackOfIndices.isEmpty() &&
                    temperatures[stackOfIndices.peek()] < temperatures[currentDay]) {
                int previousDay = stackOfIndices.pop();
                dailyTemperatures[previousDay] = currentDay - previousDay;
            }
            stackOfIndices.push(currentDay);
        }
        return dailyTemperatures;
    }
}
