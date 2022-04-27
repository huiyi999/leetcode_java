import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 247. Strobogrammatic Number II
 * <p>
 * Given an integer n, return all the strobogrammatic numbers that are of length n. You may return the answer in any order.
 * <p>
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 */

public class StrobogrammaticNumberII {
    // method 1
    // time O(5 ^ (n / 2) * n)  space O(5 ^ (n / 2))
    public List<String> findStrobogrammatic1(int n) {
        return helper(n, n);
    }

    List<String> helper(int cur, int n) {
        if (cur == 0) return Arrays.asList("");
        if (cur == 1) return Arrays.asList("0", "1", "8");

        List<String> list = helper(cur - 2, n);

        List<String> ans = new ArrayList<>();

        for (String s : list) {
            if (cur != n) ans.add("0" + s + "0");

            ans.add("1" + s + "1");
            ans.add("8" + s + "8");
            ans.add("6" + s + "9");
            ans.add("9" + s + "6");
        }
        return ans;
    }

    // method 2
    //So we start from l = 0, r = n - 1,
    // enumerate all possible values that ensure [l,r] to be strobogrammatic if [l + 1, r - 1] are strobogrammatic, and we move forward to the next step, we end when l >= r.
    List<String> ans;

    public List<String> findStrobogrammatic(int n) {
        ans = new ArrayList<>();
        findStrobogrammaticFrom(new char[n], 0, n - 1);
        return ans;
    }

    void findStrobogrammaticFrom(char[] chs, int l, int r) {
        // end for even length
        if (l > r) {
            ans.add(new String(chs));     // O(n) to new String
            return;
        }

        // end for odd length
        if (l == r) {   // the middle char with odd length
            chs[l] = '0';
            ans.add(new String(chs));

            chs[l] = '1';
            ans.add(new String(chs));

            chs[l] = '8';
            ans.add(new String(chs));
            return;

        }

        if (l != 0) {
            chs[l] = '0';
            chs[r] = '0';
            findStrobogrammaticFrom(chs, l + 1, r - 1);
        }

        chs[l] = '1';
        chs[r] = '1';
        findStrobogrammaticFrom(chs, l + 1, r - 1);

        chs[l] = '8';
        chs[r] = '8';
        findStrobogrammaticFrom(chs, l + 1, r - 1);

        chs[l] = '6';
        chs[r] = '9';
        findStrobogrammaticFrom(chs, l + 1, r - 1);

        chs[l] = '9';
        chs[r] = '6';
        findStrobogrammaticFrom(chs, l + 1, r - 1);
    }

//     O(5^(n/2) * n) time because one half of the digits determines the other, so we have n/2 positions and for each position we have 5 choices. In the end we iterate through all n digits to create the string that we add to the result.
// O(n) additional space because of the char[].


}
