package InterviewProblems.MediumQuestions.TwoPointers.ContainerWithMostWater.BruteForceSolution;

public class ContainerWithMostWater {

    // Time: O(n ^ 2)
    // --> We must compare all (n choose 2) = n(n - 1) / 2 pairs of containers.
    // Space: O(1)
    // --> We only store one variable, the max area of two containers.

    public static void main(String[] args) {
        ContainerWithMostWater containerWithMostWater = new ContainerWithMostWater();

        // Input: height = [1, 1]
        // Output: 1
        int[] height1 = {1, 1};
        int maxArea1 = containerWithMostWater.maxArea(height1);
        System.out.println(maxArea1);  // 1

        // Input: height = [1, 8, 1]
        // Output: 1
        int[] height2 = {1, 8, 1};
        int maxArea2 = containerWithMostWater.maxArea(height2);
        System.out.println(maxArea2);  // 2

        // Input: height = [1, 8, 2]
        // Output: 2
        int[] height3 = {1, 8, 2};
        int maxArea3 = containerWithMostWater.maxArea(height3);
        System.out.println(maxArea3);  // 2

        // Input: height = [5, 8, 2]
        // Output: 5
        int[] height4 = {5, 8, 2};
        int maxArea4 = containerWithMostWater.maxArea(height4);
        System.out.println(maxArea4);  // 5

        // Input: height = [1, 8, 6, 2, 5, 4, 8, 3, 7]
        // Output: 49
        // Explanation: The above vertical lines are represented by array
        // [1, 8, 6, 2, 5, 4, 8, 3, 7]. In this case, the max area of water (blue section)
        // the container can contain is 49.
        int[] height5 = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int maxArea5 = containerWithMostWater.maxArea(height5);
        System.out.println(maxArea5);  // 49
    }

    /**
     * You are given an integer array height of length n. There are n vertical lines drawn such that
     * the two endpoints of the ith line are (i, 0) and (i, height[i]).<br><br>
     *
     * Find two lines that together with the x-axis form a container, such that the container
     * contains the most water.<br><br>
     *
     * Return the maximum amount of water a container can store.<br><br>
     *
     * Notice that you may not slant the container.<br><br>
     *
     * Preconditions:
     * - n == height.length.
     * - 2 <= n <= 10 ^ 5.
     * - 0 <= height[i] <= 10 ^ 4.
     */
    public int maxArea(int[] height) {
        int maxArea = Integer.MIN_VALUE;
        for (int startIndex = 0; startIndex < height.length; startIndex++) {
            for (int endIndex = startIndex + 1; endIndex < height.length; endIndex++) {
                int currentHeight = Math.min(height[endIndex], height[startIndex]);
                int currentWidth = endIndex - startIndex;
                int currentArea = currentHeight * currentWidth;
                if (currentArea > maxArea) {
                    maxArea = currentArea;
                }
            }
        }
        return maxArea;
    }
}
