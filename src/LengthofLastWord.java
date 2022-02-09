public class LengthofLastWord {
    public static int lengthOfLastWord(String s) {
        String[] strings = s.split(" ");  // " "比"\\s+" 快
        return strings.length == 0 ? 0 : strings[strings.length - 1].length();

    }

    public static void main(String[] args) {
        String str1 = "Hello World";

        System.out.println(lengthOfLastWord(str1));

    }
}
