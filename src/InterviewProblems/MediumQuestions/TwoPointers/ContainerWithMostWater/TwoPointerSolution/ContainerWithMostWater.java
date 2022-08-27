package InterviewProblems.MediumQuestions.TwoPointers.ContainerWithMostWater.TwoPointerSolution;

public class ContainerWithMostWater {

    // Time: O(n)
    // --> We must traverse the array once to determine the max area.
    // Space: O(1)
    // --> We must store two variables (the start and end pointers) plus the max area.

    public static void main(String[] args) {
        ContainerWithMostWater containerWithMostWater = new ContainerWithMostWater();

        // 2 containers
        // Input: height = [1, 1]
        // Output: 1
        int[] height1 = {1, 1};
        int maxArea1 = containerWithMostWater.maxArea(height1);
        System.out.println(maxArea1);  // 1

        // 3 containers, left and right container are the same height
        // Input: height = [1, 8, 1]
        // Output: 1
        int[] height2 = {1, 8, 1};
        int maxArea2 = containerWithMostWater.maxArea(height2);
        System.out.println(maxArea2);  // 1

        // 3 containers, right container is taller than the left container
        // Input: height = [1, 8, 2]
        // Output: 2
        int[] height3 = {1, 8, 2};
        int maxArea3 = containerWithMostWater.maxArea(height3);
        System.out.println(maxArea3);  // 2

        // 3 containers, left container is taller than the right container
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
        // The widest container is a good candidate for the largest area
        int startIndex = 0;
        int endIndex = height.length - 1;
        int maxArea = Integer.MIN_VALUE;

        while (startIndex < endIndex) {
            int currentHeight = Math.min(height[startIndex], height[endIndex]);
            int currentWidth = endIndex - startIndex;
            maxArea = Math.max(maxArea, currentHeight * currentWidth);

            // remove the smaller container as a candidate container to increase max area
            if (height[startIndex] < height[endIndex]) {
                startIndex++;
            } else {  // height[startIndex] >= height[endIndex]
                endIndex--;
            }
        }
        return maxArea;
    }
}
