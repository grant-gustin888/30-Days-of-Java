package InterviewProblems.MediumQuestions.Strings.RobotBoundedInCircle;

public class RobotBoundedInCircle {

    // Let n = the length of the instructions string.
    //
    // Time: O(n)
    // --> We must traverse through the instructions string once to determine whether the robot
    // has landed on (0, 0) or is not facing north after one cycle.
    // Space: O(1)
    // --> We store a hashmap of size 4 for all 4 possible direction vectors,
    // a directionIndex variable, and an array of size 2 of the robot's x- and y-coordinates.

    public static void main(String[] args) {
        RobotBoundedInCircle robotBoundedInCircle = new RobotBoundedInCircle();

        // Input: instructions = "GGLLGG"
        // Output: true
        // Explanation: The robot is initially at (0, 0) facing the north direction.
        // "G": move one step. Position: (0, 1). Direction: North.
        // "G": move one step. Position: (0, 2). Direction: North.
        // "L": turn 90 degrees anti-clockwise. Position: (0, 2). Direction: West.
        // "L": turn 90 degrees anti-clockwise. Position: (0, 2). Direction: South.
        // "G": move one step. Position: (0, 1). Direction: South.
        // "G": move one step. Position: (0, 0). Direction: South.
        // Repeating the instructions, the robot goes into the cycle: (0, 0) --> (0, 1) --> (0, 2) --> (0, 1) --> (0, 0).
        // Based on that, we return true.
        boolean isRobotBounded1 = robotBoundedInCircle.isRobotBounded("GGLLGG");
        System.out.println(isRobotBounded1);  // true

        // Input: instructions = "GG"
        // Output: false
        // Explanation: The robot is initially at (0, 0) facing the north direction.
        // "G": move one step. Position: (0, 1). Direction: North.
        // "G": move one step. Position: (0, 2). Direction: North.
        // Repeating the instructions, keeps advancing in the north direction and does not go into cycles.
        // Based on that, we return false.
        boolean isRobotBounded2 = robotBoundedInCircle.isRobotBounded("GG");
        System.out.println(isRobotBounded2);  // false

        // Input: instructions = "GL"
        // Output: true
        // Explanation: The robot is initially at (0, 0) facing the north direction.
        // "G": move one step. Position: (0, 1). Direction: North.
        // "L": turn 90 degrees anti-clockwise. Position: (0, 1). Direction: West.
        // "G": move one step. Position: (-1, 1). Direction: West.
        // "L": turn 90 degrees anti-clockwise. Position: (-1, 1). Direction: South.
        // "G": move one step. Position: (-1, 0). Direction: South.
        // "L": turn 90 degrees anti-clockwise. Position: (-1, 0). Direction: East.
        // "G": move one step. Position: (0, 0). Direction: East.
        // "L": turn 90 degrees anti-clockwise. Position: (0, 0). Direction: North.
        // Repeating the instructions, the robot goes into the cycle: (0, 0) --> (0, 1) --> (-1, 1) --> (-1, 0) --> (0, 0).
        // Based on that, we return true.
        boolean isRobotBounded3 = robotBoundedInCircle.isRobotBounded("GL");
        System.out.println(isRobotBounded3);  // true

        // Input: instructions = "GR"
        // Output: true
        boolean isRobotBounded4 = robotBoundedInCircle.isRobotBounded("GR");
        System.out.println(isRobotBounded4);  // true

        // Input: instructions = "GLL"
        // Output: true
        boolean isRobotBounded5 = robotBoundedInCircle.isRobotBounded("GLL");
        System.out.println(isRobotBounded5);  // true
    }

    /**
     * On an infinite plane, a robot initially stands at (0, 0) and faces north.
     * Note that:
     * - The north direction is the positive direction of the y-axis.
     * - The south direction is the negative direction of the y-axis.
     * - The east direction is the positive direction of the x-axis.
     * - The west direction is the negative direction of the x-axis.
     *
     * The robot can receive one of three instructions:
     * - "G": go straight 1 unit.
     * - "L": turn 90 degrees to the left (i.e., anti-clockwise direction).
     * - "R": turn 90 degrees to the right (i.e., clockwise direction).
     * - The robot performs the instructions given in order, and repeats them forever.
     *
     * Return true if and only if there exists a circle in the plane such that the
     * robot never leaves the circle.
     *
     * Preconditions:
     * - 1 <= instructions.length <= 100.
     * - instructions[i] is 'G', 'L' or, 'R'.
     */
    public boolean isRobotBounded(String instructions) {
        int[] coordinates = {0, 0};  // 1st index = x-coordinate, 2nd index = y-coordinate.
        int directionIndex = 0;  // 0 = "North", 1 = "East", 2 = "South", 3 = "West".
        for (char instruction : instructions.toCharArray()) {
            if (instruction == 'G') {  // go straight
                int[] directionVector = getDirectionVector(directionIndex);
                coordinates[0] += directionVector[0];
                coordinates[1] += directionVector[1];
            } else if (instruction == 'L') {  // go left
                directionIndex = rotate(directionIndex, 'L');
            } else {  // go right
                directionIndex = rotate(directionIndex, 'R');
            }
        }
        // directionIndex != 0 ==> not facing north.
        return isOrigin(coordinates) || directionIndex != 0;
    }

    private int[] getDirectionVector(int directionIndex) {
        switch (directionIndex) {
            case 0:  // "North"
                return new int[] {0, 1};
            case 1:  // "East"
                return new int[] {1, 0};
            case 2:  // "South"
                return new int[] {0, -1};
            case 3:  // "West"
                return new int[] {-1, 0};
            default:
                return new int[] {0, 0};
        }
    }

    private int rotate(int currentDirectionIndex, char rotationDirection) {
        if (rotationDirection == 'L') {
            return (currentDirectionIndex + 1) % 4;
        } else {  // rotationDirection == "right"
            // return (currentDirectionIndex + 4 - 1) % 4;
            return (currentDirectionIndex + 3) % 4;
        }
    }

    private boolean isOrigin(int[] coordinates) {
        return coordinates[0] == 0 && coordinates[1] == 0;
    }
}
