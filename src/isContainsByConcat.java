public class isContainsByConcat {

    /**
     * 给定两个字符串s1和s2，要求判断s2是否能够被通过s1做循环移位（rotate）得到的字符串包含。
     * 例如，s1=AABCD和s2=CDAA，返回true；给定s1=ABCD和s2=ACBD，返回false。
     */
    public static boolean isContainsByConcat(String a, String b) {

        if (a == null || b == null || a.isEmpty() || b.isEmpty())
            return false;

        String newString = a.concat(b);
        return newString.contains(b);


    }

    /**
     * 将字符串向右循环移动 k 位。
     * <p>
     * 将 abcd123 中的 abcd 和 123 单独翻转，得到 dcba321，然后对整个字符串进行翻转，得到 123abcd。
     */
    public static String StringRightLoopShift(String a, int k) {

        char[] rt = new char[a.length()];
        for (int i = 0; i < a.length(); i++)
            rt[i] = a.charAt(i);

        int shift = k % a.length();
        for (int i = 0; i < a.length(); i++) {

            if (i +shift>= a.length())
                rt[(i+shift)%a.length()] = a.charAt(i);
            else
                rt[i + k] = a.charAt(i);


        }
        return  String.valueOf(rt);

    }

}
