/**
 * 616. Add Bold Tag in String
 * <p>
 * You are given a string s and an array of strings words. You should add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in words. If two such substrings overlap, you should wrap them together with only one pair of closed bold-tag. If two substrings wrapped by bold tags are consecutive, you should combine them.
 * <p>
 * Return s after adding the bold tags.
 * <p>
 * solution
 * 1. similar to "merge interval", create intervals
 * 2. similar to "range addition", range caching
 * 3. Trie, create TrieNode
 */

public class AddBoldTaginString {
    public String addBoldTag1(String s, String[] words) {
        if (s == null || words == null || words.length < 1) {
            return s;
        }
        int n = s.length();
        boolean[] bold = new boolean[n];

        for (String w : words) {
            // for(int i = s.indexOf(w); i != -1; i = s.indexOf(w, i+1)){
            //     Arrays.fill(bold, i, i+w.length(), true);
            // }
            int index = s.indexOf(w);

            while (index != -1) {
                int end = index + w.length();
                for (int i = index; i < end; i++) {
                    bold[i] = true;
                }
                index = s.indexOf(w, index + 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        // String start = bold[0] ? "<b>" : "";
        // sb.append(start);
        // for(int i = 0; i< s.length(); i++){
        //     sb.append(s.charAt(i));
        //     sb.append(bold[i] && !bold[i+1] ? "</b>" : "");
        //     sb.append(!bold[i] && bold[i+1] ? "<b>" : "");
        // }

        for (int i = 0; i < n; i++) {
            if (bold[i] && (i == 0 || !bold[i - 1])) {
                // 1. i=0 && bold[i]  2. i!=0 && bold[i] && !bold[i-1]
                sb.append("<b>");
            }
            sb.append(s.charAt(i));
            if (bold[i] && (i == n - 1 || !bold[i + 1])) {
                // 1. i=n-1 && bold[i]  2. i!=n-1 && bold[i] && !bold[i+1]
                sb.append("</b>");
            }
        }
        return sb.toString();
    }
    // similar to merge interval

    // boolean array + stringbuilder
    // time: N is the length of s, M is the length of dict, L is the average length of each word in dict. The time complexity is O(NML)
    // space: O(N)

    public String addBoldTag(String s, String[] dict) {
        int n = s.length();
        int[] mark = new int[n + 1];
        for (String d : dict) {
            int i = -1;
            while ((i = s.indexOf(d, i + 1)) >= 0) {
                mark[i]++;
                mark[i + d.length()]--;
            }
        }
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            int cur = sum + mark[i];
            if (cur > 0 && sum == 0) sb.append("<b>");
            if (cur == 0 && sum > 0) sb.append("</b>");
            if (i == n) break;
            sb.append(s.charAt(i));
            sum = cur;
        }
        return sb.toString();
    }
    // similar to Range Addition  (Range Caching)
    // time O(M*N)  N is the length of s, M is the length of dict
    // space O(N)
}
