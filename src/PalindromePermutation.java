import java.util.HashSet;
import java.util.Set;

/**
 * 266. Palindrome Permutation
 *
 * Given a string s, return true if a permutation of the string could form a palindrome.
 */
public class PalindromePermutation {
    public boolean canPermutePalindrome(String s) {
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            if (!set.add(s.charAt(i)))
                set.remove(s.charAt(i));
            // if(!set.contains(s.charAt(i)))
            //     set.add(s.charAt(i));
            // else
            //     set.remove(s.charAt(i));
        }

        return set.size() <= 1;
    }
    // Time complexity : O(n).  We traverse over the string ss of length nn once only.
    // Space complexity : O(1)O(1)
}
