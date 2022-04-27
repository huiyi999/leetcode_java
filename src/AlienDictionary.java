import java.util.*;

/**
 * 269. Alien Dictionary
 * <p>
 * There is a new alien language that uses the English alphabet. However, the order among the letters is unknown to you.
 * <p>
 * You are given a list of strings words from the alien language's dictionary, where the strings in words are sorted lexicographically by the rules of this new language.
 * <p>
 * Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there is no solution, return "". If there are multiple solutions, return any of them.
 * <p>
 * A string s is lexicographically smaller than a string t if at the first letter where they differ, the letter in s comes before the letter in t in the alien language. If the first min(s.length, t.length) letters are the same, then s is smaller if and only if s.length < t.length.
 */
public class AlienDictionary {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> map = new HashMap<>();
        // by comparing the adjacent words and the different character in the same index, key is before value which reflect the lexicographical order.
        Map<Character, Integer> degree = new HashMap<>();
        // int[] indegree = new int[26];
        // "degree": the number means "how many letters come before the key":
        String ans = "";
        for (String s : words) {
            for (char c : s.toCharArray())
                degree.put(c, 0);
        }

        // 1. Convert characters to a graph
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];

            // ["abc","ab"]
            if (w1.length() > w2.length() && w1.startsWith(w2))
                return "";

            int len = Math.min(w1.length(), w2.length());

            for (int j = 0; j < len; j++) {
                char ch1 = w1.charAt(j);
                char ch2 = w2.charAt(j);
                if (ch1 != ch2) {
                    Set<Character> set = new HashSet<>();

                    if (map.containsKey(ch1)) set = map.get(ch1);
                    if (!set.contains(ch2)) {
                        set.add(ch2);
                        map.put(ch1, set);
                        degree.put(ch2, degree.get(ch2) + 1);
                    }
                    break;   //["ac","ab","zc","zb"]   when w1="ab", w2="zc", process ch1('a')!=ch2('z') then break;
                }
            }
        }

        // topological sort
        // find the character with 0 degree
        // 2. Topological sorting: keep adding elements whose in-degree is 0
        Queue<Character> q = new LinkedList<Character>();
        for (char c : degree.keySet()) {
            if (degree.get(c) == 0)
                q.add(c);
        }

        while (!q.isEmpty()) {
            char c = q.poll();
            ans += c;
            if (map.containsKey(c)) {
                for (char tmp : map.get(c)) {
                    degree.put(tmp, degree.get(tmp) - 1);
                    // Update degree: decrease 1 to the successors of the character whose degree = 0
                    if (degree.get(tmp) == 0) q.add(tmp);
                    // If degree = 0, add the character to the queue, and append it to the result string
                }

            }
        }

        return ans.length() != degree.size() ? "" : ans;

    }
//

// O(V+E) in topological sort part. Each node is pushed into queue once, and each edge is checked exactly once.
// complexity be dominated by the first for loop visiting all chars O(all characters (non unique)) words中所有字符,
// or O(V+E) visiting in the queue part
}
