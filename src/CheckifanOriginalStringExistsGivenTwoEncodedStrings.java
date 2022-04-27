import java.util.*;

/**
 * 2060. Check if an Original String Exists Given Two Encoded Strings
 * <p>
 * An original string, consisting of lowercase English letters, can be encoded by the following steps:
 * <p>
 * Arbitrarily split it into a sequence of some number of non-empty substrings.
 * Arbitrarily choose some elements (possibly none) of the sequence, and replace each with its length (as a numeric string).
 * Concatenate the sequence as the encoded string.
 * For example, one way to encode an original string "abcdefghijklmnop" might be:
 * <p>
 * Split it as a sequence: ["ab", "cdefghijklmn", "o", "p"].
 * Choose the second and third elements to be replaced by their lengths, respectively. The sequence becomes ["ab", "12", "1", "p"].
 * Concatenate the elements of the sequence to get the encoded string: "ab121p".
 * Given two encoded strings s1 and s2, consisting of lowercase English letters and digits 1-9 (inclusive), return true if there exists an original string that could be encoded as both s1 and s2. Otherwise, return false.
 * <p>
 * Note: The test cases are generated such that the number of consecutive digits in s1 and s2 does not exceed 3.
 */

public class CheckifanOriginalStringExistsGivenTwoEncodedStrings {
    Map<String, Boolean> map = new HashMap<>();

    public boolean possiblyEquals1(String s1, String s2) {
        return find(s1, s2, 0, 0, 0);
    }

    // diff is the number of characters that we can use to match
    // if diff < 0 means we have characters in s1 can use to match
    // diff > 0 meaning we need to pick more chars in s1
    // diff < 0 meaning we need to pick more chars in s2
    // if diff > 0 means we have characters in s2 can use to match
    private boolean find(String s1, String s2, int i, int j, int diff) {

        String key = i + "-" + j + "-" + diff;
        boolean result = false;

        if (map.containsKey(key)) {
            return map.get(key);
        }

        // if s1 and s2 are all finished, then check diff value, if it is 0, then that equals.
        if (i == s1.length() && j == s2.length()) {
            result = (diff == 0);
            map.put(key, result);
            return result;
        }

        // if s1 contains digit in index i
        if (i < s1.length() && Character.isDigit(s1.charAt(i))) {
            int end = i + 1;
            while (end < s1.length() && Character.isDigit(s1.charAt(end)))
                end++;

            List<Integer> digits = find(s1.substring(i, end));

            for (int cnt : digits) {
                if (find(s1, s2, end, j, diff - cnt)) {
                    map.put(key, true);
                    return true;
                }
            }
        }

        // if s2 contains digit in index j
        else if (j < s2.length() && Character.isDigit(s2.charAt(j))) {
            int end = j + 1;
            while (end < s2.length() && Character.isDigit(s2.charAt(end)))
                end++;

            List<Integer> digits = find(s2.substring(j, end));

            for (int cnt : digits) {
                if (find(s1, s2, i, end, diff + cnt)) {
                    map.put(key, true);
                    return true;
                }
            }
        }
        // if index i in s1 and index j in s2 are not digit
        // or if i == s1.length() or j == s2.length()
        else if (diff == 0) {

            // if index i in s1 and index j in s2 are not digit, return false if they are not same
            // s1 = "a5b", s2 = "c5b"  i = 0, j = 0

            // if i == s1.length() or j == s2.length(), return false,
            // since diff is 0 and one string is finished, and another string remain

            if (i == s1.length() || j == s2.length() || s1.charAt(i) != s2.charAt(j)) {
                map.put(key, false);
                return false;
            }
            // diff=0
            return find(s1, s2, i + 1, j + 1, diff);
        }

        // if i == s1.length() and diff > 0, then we can not decrease diff, return false;
        else if (diff > 0) {
            if (i >= s1.length()) {  // s1 is finished, but diff still > 0
                map.put(key, false);
                return false;
            }

            result = find(s1, s2, i + 1, j, diff - 1);
            map.put(key, result);
            return result;
        }
        // if j == s2.length() and diff <>> 0, then we can not increase diff, return false;
        else {
            if (j >= s2.length()) {  // s2 is finished, but diff still < 0
                map.put(key, false);
                return false;
            }
            // diff <> 0, means we can use any character to replace the character at index j in s2, just increase j
            result = find(s1, s2, i, j + 1, diff + 1);
            map.put(key, result);
            return result;
        }

        map.put(key, false);
        return false;
    }

    private List<Integer> find(String s) {
        List<Integer> ans = new ArrayList<>();

        if (s.length() == 1) {
            int one = s.charAt(0) - '0';
            ans.add(one);

        } else if (s.length() == 2) {
            int one = s.charAt(0) - '0';
            int two = s.charAt(1) - '0';
            ans.add(one + two);
            ans.add(one * 10 + two);

        } else {
            int one = s.charAt(0) - '0';
            int two = s.charAt(1) - '0';
            int three = s.charAt(2) - '0';
            ans.add(one + two + three);
            ans.add(one * 10 + two + three);
            ans.add(one + two * 10 + three);
            ans.add(one * 100 + two * 10 + three);

        }
        return ans;
    }

    public boolean possiblyEquals2(String s1, String s2) {
        s1 += ':';
        s2 += ':';
        return dfs(s1, s2, 0, 0, 0, 0, new HashSet<>());
    }

    private boolean dfs(String s1, String s2, int i, int j, int diff, int num, Set<Integer> set) {
        int a = s1.charAt(i) - '0';
        int b = s2.charAt(j) - '0';

        int curDiff = diff + num;
        int key = i * 10000 + j * 1000 + curDiff;
        if (num == 0 && !set.add(key)) return false;

        if (a < 10) { // is digit
            // (ex: 44). Either we use "4" and "4" || "44"
            return dfs(s1, s2, i + 1, j, curDiff, a, set) || // either use this current number
                    (num > 0) && dfs(s1, s2, i + 1, j, diff, num * 10 + a, set); // or concatenate number
        }

        if (b < 10) { // is digit
            return dfs(s1, s2, i, j + 1, curDiff, -b, set) ||
                    (num < 0) && dfs(s1, s2, i, j + 1, diff, num * 10 - b, set);
        }

        if (curDiff < 0) { // is letter
            return (a > 10) && dfs(s1, s2, i + 1, j, curDiff + 1, 0, set);
        }
        if (curDiff > 0) {
            return (b > 10) && dfs(s1, s2, i, j + 1, curDiff - 1, 0, set);
        }
        return (a == b) && (s1.charAt(i) == ':' || dfs(s1, s2, i + 1, j + 1, 0, 0, set));
    }


// -1000 < diff < 1000 as there can be at most 3 digits in the string meaning largest digits are 999

    // 1. s1[i] == s2[j] and diff = 0   increment i+1 and j+1
// 2. if s1[i] is not digit and diff > 0  then increment i, diff
// 3. if s2[j] is not digit and diff < 0 then increment j, diff
// 4. if s1[i] is digit then get digit value and decrement diff val as we have covered such chars in the s1 string and increment i i+1, diff-val
// 5. if s2[j] is digit then get digit value and increment diff val as we need to cover such chars in the s2 string and increment j, j+1, diff+val
    public boolean possiblyEquals(String s1, String s2) {
        return helper(s1.toCharArray(), s2.toCharArray(), 0, 0, 0, new Boolean[s1.length() + 1][s2.length() + 1][2000]);
    }

    boolean helper(char[] s1, char[] s2, int i, int j, int diff, Boolean[][][] dp) {
        // terminating
        if (i == s1.length && j == s2.length) {
            return diff == 0;
        }

        if (dp[i][j][diff + 1000] != null)
            return dp[i][j][diff + 1000];

        // 1.if both i and j are at the same location and chars are same then simply increment both pointers
        if (i < s1.length && j < s2.length && diff == 0 && s1[i] == s2[j]) {
            if (helper(s1, s2, i + 1, j + 1, diff, dp))
                return dp[i][j][diff + 1000] = true;
        }

        // 2. if s1[i] is not digit and diff > 0  then increment i, diff
        if (i < s1.length && !Character.isDigit(s1[i]) && diff > 0) {
            if (helper(s1, s2, i + 1, j, diff - 1, dp))
                return dp[i][j][diff + 1000] = true;
        }

        // 3. if s2[j] is not digit and diff < 0 then increment j, diff
        if (j < s2.length && !Character.isDigit(s2[j]) && diff < 0) {
            if (helper(s1, s2, i, j + 1, diff + 1, dp))
                return dp[i][j][diff + 1000] = true;
        }

        // 4. wildcard matching in s1
        // if s1 contains l123   1,2,3 or 12,3 or 123 or 1,23
        if (i < s1.length && Character.isDigit(s1[i])) {
            int p = i + 1;
            while (p < s1.length && Character.isDigit(s1[p]))
                p++;

            List<Integer> digits = findDigit(s1, i, p);

            for (int val : digits) {
                //System.out.println("s1: "+val);
                if (helper(s1, s2, p, j, diff - val, dp)) {
                    return dp[i][j][diff + 1000] = true;
                }
            }
        }
        // if s1 contains l123
        // then need to check with val as 1 then val as 12 and val as 123
        // for(int k = i, val = 0; k < i + 4 && k < s1.length && Character.isDigit(s1[k]); k++) {
        //     val = val * 10 + s1[k] -'0';
        //     if(helper(s1, s2, k+1, j, diff-val, dp)) {
        //         return dp[i][j][diff+1000] = true;
        //     }
        // }


        if (j < s2.length && Character.isDigit(s2[j])) {
            int q = j + 1;
            while (q < s2.length && Character.isDigit(s2[q]))
                q++;

            List<Integer> digits = findDigit(s2, j, q);
            //System.out.println("j: "+j +" q:"+q);
            for (int val : digits) {
                //System.out.println("s2: "+val);
                if (helper(s1, s2, i, q, diff + val, dp)) {
                    return dp[i][j][diff + 1000] = true;
                }
            }
        }

        return dp[i][j][diff + 1000] = false;

    }

    private List<Integer> findDigit(char[] s, int i, int j) {
        List<Integer> ans = new ArrayList<>();
        int l = j - i;
        if (l == 1) {
            int one = s[i] - '0';
            ans.add(one);

        } else if (l == 2) {
            int one = s[i++] - '0';
            int two = s[i] - '0';
            ans.add(one + two);
            ans.add(one * 10 + two);

        } else {
            int one = s[i++] - '0';
            int two = s[i++] - '0';
            int three = s[i] - '0';
            ans.add(one + two + three);
            ans.add(one * 10 + two + three);
            ans.add(one + two * 10 + three);
            ans.add(one * 100 + two * 10 + three);

        }
        //System.out.println(ans.toString());
        return ans;
    }
    // [2000]  [-999,999]
    // 999a999
    // time O(M * N * DIFF) M = s1.length, N = s2.length, DIFF = 2000 can lie between [-999,999] as per constraints.

//In the worst case, we’ll recurse at every possible combination of ‘POS1’, ‘POS2’, and ‘DIFF’.
    // space O(M * N * DIFF) M = s1.length, N = s2.length, DIFF = 2000 can lie between [-999, 999] as per constraints.

}
