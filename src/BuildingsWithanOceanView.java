import java.util.ArrayList;

/**
 * 1762. Buildings With an Ocean View
 * <p>
 * There are n buildings in a line. You are given an integer array heights of size n that represents the heights of the buildings in the line.
 * <p>
 * The ocean is to the right of the buildings. A building has an ocean view if the building can see the ocean without obstructions. Formally, a building has an ocean view if all the buildings to its right have a smaller height.
 * <p>
 * Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.
 */

public class BuildingsWithanOceanView {

    public int[] findBuildings(int[] heights) {
        ArrayList<Integer> ans = new ArrayList<>();
        int n = heights.length;
        ans.add(n - 1);
        int h = heights[n - 1];

        for (int i = heights.length - 2; i >= 0; i--) {
            if (heights[i] > h) {
                ans.add(i);
                h = heights[i];
            }
        }
        int[] ret = new int[ans.size()];
        int j = ans.size() - 1;
        for (int i = 0; i < ans.size(); i++) {
            ret[i] = ans.get(j);
            j--;
        }
        return ret;
    }
}
