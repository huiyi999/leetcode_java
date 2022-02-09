/**
 * On an infinite plane, a robot initially stands at (0, 0) and faces north.  The robot can receive one of three instructions:
 * <p>
 * "G": go straight 1 unit;
 * "L": turn 90 degrees to the left;
 * "R": turn 90 degress to the right.
 * The robot performs the instructions given in order, and repeats them forever.
 * <p>
 * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
 */
public class RobotBoundedInCircle {
    public static boolean isRobotBounded(String instructions) {
//        String[] instructionsArr =instructions.split('');
        int[] root = {0, 0};

        String direction = "north";

        for (int i = 0; i < instructions.length(); i++) {
            Character instruction = instructions.charAt(i);
            if (instruction.equals('G')) {
                if (direction == "north")
                    root[1] += 1;
                else if (direction == "south")
                    root[1] -= 1;
                else if (direction == "west")
                    root[0] -= 1;
                else
                    root[0] += 1;

            }
            if (instruction.equals('L')) {
                if (direction == "north")
                    direction = "west";
                else if (direction == "west")
                    direction = "south";
                else if (direction == "south")
                    direction = "east";
                else
                    direction = "north";

            }
            if (instruction.equals('R')) {
                if (direction == "north")
                    direction = "east";
                else if (direction == "east")
                    direction = "south";
                else if (direction == "south")
                    direction = "west";
                else
                    direction = "north";
            }
        }

//        System.out.println("first root: " + root[0] + " " + root[1]);

//        The robot stays in the circle iff (looking at the final vector) it changes direction
//        (ie. doesn't stay pointing north), or it moves 0.
        if (root[0] == 0 && root[1] == 0)
            return true;
        if (direction.equals("north"))
            return false;
        return true;
    }

    public static void main(String[] args) {
        String str1 = "GGLLGG";
        String str2 = "GG";
        String str3 = "GL";

        System.out.println(isRobotBounded(str1));   // Output: true
        // Explanation: The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
        // When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.


        System.out.println(isRobotBounded(str2));  //Output: false
        // Explanation: The robot moves north indefinitely.


        System.out.println(isRobotBounded(str3)); //Output: true
        // Explanation: The robot moves from (0, 0) ->(0, 1) ->(-1, 1) ->(-1, 0) ->(0, 0) -> ...
    }
}
