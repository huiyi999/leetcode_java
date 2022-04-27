import java.util.ArrayList;
import java.util.List;


/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * <p>
 * Note:
 * <p>
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 */
public class WordBreak {
    public static boolean wordBreak(String s, List<String> wordDict) {

        /**
         * DP
         * 首先我们要存储的历史信息res[i]是表示到字符串s的第i个元素为止能不能用字典中的词来表示，
         * 我们需要一个长度为n的布尔数组来存储信息。然后假设我们现在拥有res[0,...,i-1]的结果，
         * 我们来获得res[i]的表达式。思路是对于每个以i为结尾的子串，看看他是不是在字典里面以及他之前的元素对应的res[j]是不是true，
         * 如果都成立，那么res[i]为true
         */
        if (wordDict.size() == 0 || s.length() == 0)
            return false;

        boolean[] res = new boolean[s.length() + 1];
        res[0] = true;


        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {

                String tmp = s.substring(j, i);
//                System.out.println("tmp: " + tmp);
//                System.out.println("res[j]: " + res[j]);
                if (res[j] && wordDict.contains(tmp)) {
                    res[i] = true;
                    break;
                }
            }
        }

        return res[s.length()];
    }

    public boolean wordBreak2(String s, List<String> wordDict) {
        if (s.length() == 0)
            return true;
        boolean segment[] = new boolean[s.length() + 1];
        segment[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (String word : wordDict) {

                if (word.length() <= i) {
                    if (segment[i - word.length()]) {
                        if (s.substring(i - word.length(), i).equals(word)) {
                            segment[i] = true;
                            break;
                        }
                    }
                }
            }
        }
        return segment[s.length()];
    }

    public static void main(String[] args) {
        String s1 = "leetcode";
        List<String> wordDict1 = new ArrayList<>();
        wordDict1.add("leet");
        wordDict1.add("code");

        String s2 = "applepenapple";
        List<String> wordDict2 = new ArrayList<>();
        wordDict2.add("apple");
        wordDict2.add("pen");

        String s3 = "catsandog";
        List<String> wordDict3 = new ArrayList<>();
        wordDict3.add("cats");
        wordDict3.add("dog");
        wordDict3.add("sand");
        wordDict3.add("and");
        wordDict3.add("cat");

        System.out.println(wordBreak(s1, wordDict1));       //        Output: true
//        Explanation: Return true because "leetcode" can be segmented as "leet code".

        System.out.println(wordBreak(s2, wordDict2));       //        Output: true
//        Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
//        Note that you are allowed to reuse a dictionary word.

        System.out.println(wordBreak(s3, wordDict3));       // Output: false


    }
}
