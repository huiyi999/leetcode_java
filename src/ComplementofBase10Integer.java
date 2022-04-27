/**
 * Every non-negative integer N has a binary representation.
 * For example, 5 can be represented as "101" in binary, 11 as "1011" in binary, and so on.
 * Note that except for N = 0, there are no leading zeroes in any binary representation.
 * <p>
 * The complement of a binary representation is the number in binary you get when changing every 1 to a 0 and 0 to a 1.
 * For example, the complement of "101" in binary is "010" in binary.
 * <p>
 * For a given number N in base-10, return the complement of it's binary representation as a base-10 integer.
 */

public class ComplementofBase10Integer {
    public static int bitwiseComplement(int N) {
        String number = Integer.toBinaryString(N);

        int len =number.length();

        int[] complement=new int[len];
        for (int i=0;i<len;i++){
            Character ch = number.charAt(i);
            if(ch.equals('0'))
                complement[i]=1;
        }
        int result=0;
        for (int i=0;i<len;i++) {
            result+=(int)Math.pow(2,len-1-i)*complement[i];
//            System.out.println(complement[i]+" result: "+result);
        }


        return result;

    }

    public static int bitwiseComplement2(int N) {
        int bitLen = (int)(Math.log(N)/Math.log(2))+1;
        System.out.println(bitLen);
        int bit = 1 << bitLen;    //
        System.out.println(bit);
        bit -= 1;              //
        System.out.println(bit);  //
        return N ^ bit;
    }


    public static void main(String[] args) {
        int N1 = 5;
        int N2 = 7;
        int N3 = 10;

        System.out.println(bitwiseComplement(N1));  // Output: 2
//      Explanation: 5 is "101" in binary, with complement "010" in binary, which is 2 in base-10.

        System.out.println(bitwiseComplement(N2));  // Output: 0
//        Explanation: 7 is "111" in binary, with complement "000" in binary, which is 0 in base-10.

        System.out.println(bitwiseComplement(N3));  // Output: 5
//        Explanation: 10 is "1010" in binary, with complement "0101" in binary, which is 5 in base-10.

        System.out.println(bitwiseComplement((int)Math.pow(10, 9)));


        System.out.println(bitwiseComplement2(N1));
    }
}
