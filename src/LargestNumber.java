import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Given a list of non negative integers, arrange them such that they form the largest number.
 */

public class LargestNumber {
    static class sComparator implements Comparator<String>{
        public int compare(String s1, String s2){
            String add1 = s1+s2;
            String add2 = s2+s1;
            return add2.compareTo(add1);
        }
    }
    public static String largestNumber(int[] nums) {
        String[] str_num =new String[nums.length];
        for (int i=0;i<nums.length;i++){
            str_num[i]=String.valueOf(nums[i]);
        }

        Comparator<String> comparator = new sComparator();
        Arrays.sort(str_num,comparator);

//        Arrays.sort(str_num, new Comparator<String>() {
//            public int compare(String str1, String str2) {
//                String obj1=str1+str2;
//                String obj2=str2+str1;
//                return obj1.compareTo(obj2);
//            }
//        });
        String res="";
        for (int i=0;i<=nums.length-1;i++){
            res+=str_num[i];
        }
        if (res.charAt(0)=='0')
            res=res.substring(0,1);
//        return (res.charAt(0)=='0')?"0":res.toString();
        return res;

    }

    public static void main(String[] args) {

        int[] nums1 = {10, 2};
        int[] nums3 = {0, 0};
        int[] nums4 = {0};
        int[] nums2 = {3, 30, 34, 5, 9};


        System.out.println(largestNumber(nums1));   //Output: "210"
        System.out.println(largestNumber(nums2));   //Output: "9534330"
        System.out.println(largestNumber(nums3));   //Output: "0"
        System.out.println(largestNumber(nums4));   //Output: "0"

        System.out.println("===============");

        String demos[] = {"hello", "软件小宇", "test", "中国"};
        Collections.sort(Arrays.asList(demos));
        for (int i = 0; i < demos.length; i++) {
            System.out.println(demos[i]);
        }
        System.out.println("===============");
        String nums[] = {"3", "30", "34", "5","9"};
        Collections.sort(Arrays.asList(nums));
        for (int i = nums.length-1; i >=0; i--) {
            System.out.println(nums[i]);
        }
        System.out.println("===============");
        String num2[] = {"9534330", "9534303"};
        Collections.sort(Arrays.asList(num2));
        for (int i = num2.length-1; i >=0; i--) {
            System.out.println(num2[i]);
        }




    }
}
