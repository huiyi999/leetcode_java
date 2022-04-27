/**
 * 408. Valid Word Abbreviation
 * <p>
 * A string can be abbreviated by replacing any number of non-adjacent, non-empty substrings with their lengths. The lengths should not have leading zeros.
 * <p>
 * For example, a string such as "substitution" could be abbreviated as (but not limited to):
 * <p>
 * "s10n" ("s ubstitutio n")
 * "sub4u4" ("sub stit u tion")
 * "12" ("substitution")
 * "su3i1u2on" ("su bst i t u ti on")
 * "substitution" (no substrings replaced)
 * The following are not valid abbreviations:
 * <p>
 * "s55n" ("s ubsti tutio n", the replaced substrings are adjacent)
 * "s010n" (has leading zeros)
 * "s0ubstitution" (replaces an empty substring)
 * Given a string word and an abbreviation abbr, return whether the string matches the given abbreviation.
 * <p>
 * A substring is a contiguous non-empty sequence of characters within a string.
 */

public class ValidWordAbbreviation {
    public boolean validWordAbbreviation(String word, String abbr) {

        int m = 0, n = 0;

        while (m < word.length() && n < abbr.length()) {
            int tmp = 0;

            if (!Character.isDigit(abbr.charAt(n))) {
                if (word.charAt(m) != abbr.charAt(n)) {
                    return false;
                }
                m++;
                n++;

            } else if (Character.isDigit(abbr.charAt(n)) && abbr.charAt(n) != '0') {
                while (n < abbr.length() && Character.isDigit(abbr.charAt(n))) {
                    tmp = tmp * 10 + abbr.charAt(n) - '0';
                    n++;
                }
                m += tmp;


            } else if (abbr.charAt(n) == '0') {
                return false;
            }

        }
        return m == word.length() && n == abbr.length();


    }
}
