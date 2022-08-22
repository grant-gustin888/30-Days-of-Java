package InterviewProblems.MediumQuestions.BruteForce.DailyTemperatures;

import java.util.Arrays;

public class DailyTemperatures {

    // Let n = the length of the nums array.
    //
    // Time: O(n ^ 2)
    // --> We may have to compare all pairs of temperatures to determine how many days it takes
    // for the temperature to get warmer.
    // Space: O(n)
    // --> We have to create a new array to return.

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
        int[] dailyTemperatures = new int[temperatures.length];
        for (int currentDay = 0; currentDay < temperatures.length; currentDay++) {
            for (int nextDay = currentDay + 1; nextDay < temperatures.length; nextDay++) {
                if (temperatures[nextDay] > temperatures[currentDay]) {
                    dailyTemperatures[currentDay] = nextDay - currentDay;
                    break;
                }
            }
        }
        return dailyTemperatures;
    }
}
