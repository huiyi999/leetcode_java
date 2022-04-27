import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupShiftedStrings {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> ret = new ArrayList<>();

        HashMap<String, List<String>> map = new HashMap<>();

        for (String s : strings) {
            int offset = s.charAt(0) - 'a';

            StringBuilder key = new StringBuilder();

            for (int i = 0; i < s.length(); i++) {
                // +26  => prevent: s.charAt(i) - offset < 0
                char ch = (char) ((s.charAt(i) + 26 - offset) % 26);
                key.append(ch);
            }

            map.computeIfAbsent(key.toString(), val -> new ArrayList<>()).add(s);
        }

        for (List<String> val : map.values()) {
            ret.add(val);
        }
        return ret;
    }
    // Space: O(n), n is length of String array
    // Time: O(n*m), n is the same as above, m is the average length of string element in array.
}
