import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
 * <p>
 * Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.
 * <p>
 * Constraints: 10 <= low <= high <= 10^9
 * <p>
 * Hide Hint #1: Generate all numbers with sequential digits and check if they are in the given range.
 * Hide Hint #2: Fix the starting digit then do a recursion that tries to append all valid digits.
 */
public class SequentialDigits {
    public static List<Integer> sequentialDigits(int low, int high) {
        /**
         * solution1
         */
        List<Integer> result = new ArrayList();
        String s = "123456789";
        for (int l = 2; l <= s.length(); l++) {
            for (int j = 0; j <= s.length() - l; j++) {
                int num = Integer.parseInt(s.substring(j, j + l));
                if (num > high) return result;
                if (num >= low) result.add(num);
            }
        }

        /**
         * solution2
         */
        List<Integer> output = new ArrayList<>();
        for(int i=1;i<10;i++){
            int num=0, j =i;
            while(num<=high && j<10){
                num = num*10+(j);
                if(num>=low && num<=high){
                    output.add(num);
                }
                j++;
            }
        }
        Collections.sort(output);

        return result;

    }
//    public static List<Integer> sequentialDigits(int low, int high) {
//
//        List<Integer> list = new ArrayList<>();
//        if (low < 10 || low > high || high > (int) Math.pow(10, 9))
//            return list;
//
//        sequential(low, high, list);
//        return list;
//    }
//
//
//    public static void sequential(int low, int high, List<Integer> list) {
//        int length = (int) Math.log10(low) + 1;
//        int tmp = (low / (int) Math.pow(10, length - 1) % 10) * ((int) Math.pow(10, length - 1));
//        for (int i = length - 1; i >= 1; i--) {
//            int num = (tmp / (int) Math.pow(10, i) % 10 + 1);
//            tmp += num * ((int) Math.pow(10, i - 1));
//        }
//
//        length = (int) Math.log10(tmp) + 1;
//        for (int i = length; i > 1; i--) {
//            if ((tmp / (int) Math.pow(10, i - 1) % 10) > (tmp / (int) Math.pow(10, i - 2) % 10)){
//                tmp += ((int) Math.pow(10, length - 1));
//                sequential(tmp, high, list);
//                return;
//            }
//
//        }
//        if (tmp > high)
//            return;
//        else list.add(tmp);
//        tmp += ((int) Math.pow(10, length - 1));
//        sequential(tmp, high, list);
//
//    }

    public static void main(String[] args) {

        int low1 = 100;
        int high1 = 300;

        int low2 = 1000;
        int high2 = 13000;


        List<Integer> result1 = sequentialDigits(low1, high1);   //  Output: [123,234]
        for (int i : result1)
            System.out.println(i);

        List<Integer> result2 = sequentialDigits(low2, high2);   //  Output: [1234,2345,3456,4567,5678,6789,12345]
        for (int i : result2)
            System.out.println(i);


    }
}
