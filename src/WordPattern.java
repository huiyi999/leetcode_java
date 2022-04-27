import java.util.HashMap;
import java.util.Map;

public class WordPattern {
    public static boolean wordPattern(String pattern, String str) {
        String[] arr = str.split("\\s+");
        char[] pat = pattern.toCharArray();

        Map<Character, String> map = new HashMap<>();

        return isMatch(arr, 0, pattern, map);
    }

    static boolean isMatch(String[] arr, int i, String pattern, Map<Character, String> map) {
        if (arr.length != pattern.length()) return false;

        char pat = pattern.charAt(i);

        // if the pattern character exists
        if (map.containsKey(pat)) {
            String s = map.get(pat);

            if (!arr[i].equals(s))
                return false;
            else {
                if (i == arr.length - 1)
                    return true;
                else
                    return isMatch(arr, ++i, pattern, map);
            }

        } else {

            for (int k = 0; k < pattern.length(); k++) {
                char c = pattern.charAt(k);
                if (map.containsKey(c)) {
                    String tmp = map.get(c);
                    if (tmp.equals(arr[i]))
                        return false;
                }
            }

            map.put(pattern.charAt(i), arr[i]);
            if (i == arr.length - 1)
                return true;
            else
                return isMatch(arr, ++i, pattern, map);

        }

    }

    public static void main(String[] args) {
        String pattern1 = "abba";
        String pattern2 = "aaaa";
        String pattern3 = "aba";
        String str1 = "dog cat cat dog";
        String str2 = "dog cat cat fish";
        String str3 = "dog cat cat dog";
        String str4 = "dog dog dog dog";
        String str5 = "dog cat cat";

        System.out.println(wordPattern(pattern1, str1));   //true
        System.out.println(wordPattern(pattern1, str2));   //false
        System.out.println(wordPattern(pattern2, str3));   //false
        System.out.println(wordPattern(pattern1, str4));   //false
        System.out.println(wordPattern(pattern3, str5));   //false
    }
}
