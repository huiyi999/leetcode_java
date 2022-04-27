/**
 * 1216. Valid Palindrome III
 * <p>
 * Given a string s and an integer k, return true if s is a k-palindrome.
 * <p>
 * A string is k-palindrome if it can be transformed into a palindrome by removing at most k characters from it.
 */

public class ValidPalindromeIII {
    // LCS of the given string & its reverse will be the longest palindromic sequence.

    public boolean isValidPalindrome(String s, int k) {
        // return isValid(s, k, 0, s.length() - 1);

        // similar to 516 Longest Palindromic Subsequence
        // 1. calculate LPS
        char[] arr = s.toCharArray();
        int n = s.length();
        int[] dp = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            int pre = 0;
            for (int j = i + 1; j < n; ++j) {
                int tmp = dp[j];
                if (arr[i] == arr[j])
                    dp[j] = 2 + pre;
                else
                    dp[j] = Math.max(dp[j], dp[j - 1]);
                pre = tmp;
            }
        }
        // 2. compare the length of LPS and s
        return n - dp[n - 1] <= k;
    }
// O(n) space O(n^2) time   with one-array
// dp[i][j] only depends on dp[i+1][j-1](down-left), dp[i+1][j](down) and dp[i][j-1](left). So if we reduce dp[n][m] to dp[m], that means, for dp[j], its down is itself, its left is dp[j-1]. Its down-left is a little tricky. As its down-left dp[i+1][j-1] is now dp[j-1], so we need to preserve it before updating to dp[j-1].


}
