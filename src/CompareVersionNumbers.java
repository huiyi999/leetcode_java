
//https://leetcode.com/explore/challenge/card/september-leetcoding-challenge/555/week-2-september-8th-september-14th/3454/

import java.util.stream.Stream;

/**
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.
 * <p>
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 * <p>
 * The . character does not represent a decimal point and is used to separate number sequences.
 * <p>
 * For instance, 2.5 is not "two and a half" or "half way to version three",
 * it is the fifth second-level revision of the second first-level revision.
 * <p>
 * You may assume the default revision number for each level of a version number to be 0.
 * For example, version number 3.4 has a revision number of 3 and 4 for its first and second level revision number.
 * Its third and fourth level revision number are both 0.
 */

public class CompareVersionNumbers {
    public static int compareVersion(String version1, String version2) {

        String[] tmp1 = version1.split("\\.");
        int[] ver1 = Stream.of(tmp1).mapToInt(Integer::parseInt).toArray();

        String[] tmp2 = version2.split("\\.");
        int[] ver2 = Stream.of(tmp2).mapToInt(Integer::parseInt).toArray();

        int len = 0;
        if (ver1.length > ver2.length) {
            len = ver1.length;
        } else {
            len = ver2.length;
        }


        for (int i = 0; i < len; i++) {
            try {
                int com = 0;
                if (ver1[i] > ver2[i])
                    com = 1;
                else if (ver1[i] < ver2[i])
                    com = -1;

                if (com != 0) {
                    return com;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                if (ver1.length > ver2.length) {
                    for (int j = ver2.length; j < ver1.length; j++) {
                        if (ver1[j] > 0)
                            return 1;
                    }
                } else {
                    for (int j = ver1.length; j < ver2.length; j++) {
                        if (ver2[j] > 0)
                            return -1;
                    }
                }
            }
        }
        return 0;
    }

    public int compareVersion2(String a, String b) {
        for (int i = 0, j = 0; i < a.length() || j < b.length(); i++, j++) {
            int m = 0;
            int n = 0;

            //字符0-9的ASCII码：48-56；
            while (i < a.length() && a.charAt(i) != '.') m = m * 10 + a.charAt(i++) - '0';
            while (j < b.length() && b.charAt(j) != '.') n = n * 10 + b.charAt(j++) - '0';

            if (m > n) return 1;
            if (m < n) return -1;
        }
        return 0;
    }

    public static void main(String[] args) {

        String version11 = "0.1";
        String version21 = "1.1";
        //Output: -1


        String version12 = "1.0.1";
        String version22 = "1";
        //Output: 1

        String version13 = "7.5.2.4";
        String version23 = "7.5.3";
        //Output: -1

        String version14 = "1.01";
        String version24 = "1.001";
        //Output: 0

        String version15 = "1.0";
        String version25 = "1.0.0";
        //Output: 0

        System.out.println(compareVersion(version11, version21));
        System.out.println(compareVersion(version12, version22));
        System.out.println(compareVersion(version13, version23));
        System.out.println(compareVersion(version14, version24));
        System.out.println(compareVersion(version15, version25));

    }
}
