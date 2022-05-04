/**
 * @author : chy
 * @date: 2022-04-28 1:36 p.m.
 */
public class PrefixPalindrome {

    /**
     * Constraint: 0<=len(s)<=1000
     * Given a string:
     * 1.) Take all prefixes of the string and choose the longest palindrome between them.
     * 2.) If chosen prefix has atleast two characters, cut this from s and go back to step 1 with the updated string. Otherwise, end the algo with the current string s.
     */
    public String nonPalindromicSubString(String s) {
        if (s.length() == 0) return "";

        String pre = getPrefixPalindrome(s);
        if (pre.length() > 1) {
            return nonPalindromicSubString(s.substring(pre.length()));
        } else {
            return s;
        }
    }

    private String getPrefixPalindrome(String str) {
        String temp = str + '?';
        str = reverse(str);
        temp += str;
        int n = temp.length();

        int[] lps = new int[n];
        for (int i = 1; i < n; i++) {
            int len = lps[i - 1];
            while (len > 0 && temp.charAt(len) != temp.charAt(i)) {
                len = lps[len - 1];
            }
            if (temp.charAt(i) == temp.charAt(len)) len++;
            lps[i] = len;
        }
        return temp.substring(0, lps[n - 1]);
    }

    private String reverse(String str) {
        char[] a = str.toCharArray();
        int l, r = a.length - 1;

        for (l = 0; l < r; l++, r--) {
            char temp = a[l];
            a[l] = a[r];
            a[r] = temp;
        }
        return String.valueOf(a);
    }

    public static String solvePrefixPalindrome(String s) {

        int max = 0;

        for (int i = 2; i <= s.length(); i++) {

            String s1 = s.substring(0, i);
            StringBuilder go = new StringBuilder(s1);
            String s2 = String.valueOf(go.reverse());

            if (s2.equals(s1)) {
                if (s2.length() > max)
                    max = s2.length();
            }
        }

        if (max > 1)
            return solvePrefixPalindrome(s.substring(max));
        else
            return s.substring(max);
    }
}
