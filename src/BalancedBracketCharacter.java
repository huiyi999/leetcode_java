public class BalancedBracketCharacter {

    public static boolean isBalanced(String s){
        int n = -1;
        while (s.length() != n) {
            n = s.length();
            s = s.replace("()", "");
            System.out.println(s);
            s = s.replace("[]", "");
            s = s.replace("{}", "");
        }
        if (s.length() == 0)
            return true;
        else
            return false;
    }
    public static void main(String[] args) {

        String s1="{}()";
        String s2="[{()}]";
        String s3="[](";

        System.out.println(isBalanced(s1));
        System.out.println(isBalanced(s2));
        System.out.println(isBalanced(s3));

    }

}
