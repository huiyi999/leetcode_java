import java.util.HashSet;

/**
 * 246. Strobogrammatic Number
 * <p>
 * Given a string num which represents an integer, return true if num is a strobogrammatic number.
 * <p>
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 */

public class StrobogrammaticNumber {
    public boolean isStrobogrammatic(String num) {

        int i = 0, j = num.length() - 1;
        HashSet<String> set = new HashSet<>();
        set.add("00");
        set.add("11");
        set.add("69");
        set.add("88");
        set.add("96");

        while (i <= j) {
            if (!set.contains(num.charAt(i++) + "" + num.charAt(j--)))
                return false;
        }
        return true;
    }
    // Time complexity : O(N).
    // Space complexity : O(1).  constant extra space

}
