import java.util.*;

/**
 * 1152. Analyze User Website Visit Pattern
 * <p>
 * You are given two string arrays username and website and an integer array timestamp. All the given arrays are of the same length and the tuple [username[i], website[i], timestamp[i]] indicates that the user username[i] visited the website website[i] at time timestamp[i].
 * <p>
 * A pattern is a list of three websites (not necessarily distinct).
 * <p>
 * For example, ["home", "away", "love"], ["leetcode", "love", "leetcode"], and ["luffy", "luffy", "luffy"] are all patterns.
 * The score of a pattern is the number of users that visited all the websites in the pattern in the same order they appeared in the pattern.
 * <p>
 * For example, if the pattern is ["home", "away", "love"], the score is the number of users x such that x visited "home" then visited "away" and visited "love" after that.
 * Similarly, if the pattern is ["leetcode", "love", "leetcode"], the score is the number of users x such that x visited "leetcode" then visited "love" and visited "leetcode" one more time after that.
 * Also, if the pattern is ["luffy", "luffy", "luffy"], the score is the number of users x such that x visited "luffy" three different times at different timestamps.
 * Return the pattern with the largest score. If there is more than one pattern with the same largest score, return the lexicographically smallest such pattern.
 */
public class AnalyzeUserWebsiteVisitPattern {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        int N = username.length;
        Map<String, Map<Integer, String>> tuple = new HashMap<>();

        for (int i = 0; i < N; i++) {
            tuple.putIfAbsent(username[i], new HashMap<>());
            tuple.get(username[i]).put(timestamp[i], website[i]);
        }

        String res = "";
        Map<String, Integer> cnt = new HashMap<>();

        for (Map.Entry<String, Map<Integer, String>> entry : tuple.entrySet()) {
            String user = entry.getKey();
            Map<Integer, String> timeweb = entry.getValue();

            Map.Entry<Integer, String>[] arr = new Map.Entry[timeweb.size()];
            timeweb.entrySet().toArray(arr);
            Arrays.sort(arr, Comparator.comparingInt(Map.Entry::getKey));

            Set<String> set = new HashSet<>();
            for (int i = 0; i < arr.length; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    for (int k = j + 1; k < arr.length; k++) {
                        String tmp = arr[i].getValue() + " " + arr[j].getValue() + " " + arr[k].getValue();
                        if (!set.contains(tmp)) {
                            cnt.put(tmp, cnt.getOrDefault(tmp, 0) + 1);
                            set.add(tmp);
                        }
                        if (res.equals("") || (cnt.get(res) == cnt.get(tmp) && res.compareTo(tmp) > 0) || cnt.get(res) < cnt.get(tmp))
                            res = tmp;


                    }
                }
            }


        }
        return Arrays.asList(res.split(" "));

    }
}
