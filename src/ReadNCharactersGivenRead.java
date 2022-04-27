/**
 * 157. Read N Characters Given Read4
 * <p>
 * Given a file and assume that you can only read the file using a given method read4, implement a method to read n characters.
 *
 * Definition of read4:
 *
 *     Parameter:  char[] buf4
 *     Returns:    int
 *
 * buf4[] is a destination, not a source. The results from read4 will be copied to buf4[].
 *
 * Method read:
 *
 * By using the read4 method, implement the method read that reads n characters from file and store it in the buffer array buf. Consider that you cannot manipulate file directly.
 *
 * The return value is the number of actual characters read.
 *
 * Definition of read:
 *
 *     Parameters:	char[] buf, int n
 *     Returns:	int
 *
 * buf[] is a destination, not a source. You will need to write the results to buf[].
 *
 */
//
// /**
//  * The read4 API is defined in the parent class Reader4.
//  *     int read4(char[] buf4);
//  */
//
// public class ReadNCharactersGivenRead extends Reader4 {
//     /**
//      * @param buf Destination buffer
//      * @param n   Number of characters to read
//      * @return    The number of actual characters read
//      */
//     public int read(char[] buf, int n) {    // storing n characters
//         int total = 0;
//         int toWrite = 1;
//         char[] tmp = new char[4];
//
//         while(toWrite!=0 && total < n){
//
//             // buf4[] is a destination, not a source.
//             // read4(): The return value is the number of actual characters read.
//             toWrite = read4(tmp);
//
//             //toWrite = Math.min(toWrite, n-total);
//
//             for(int i = 0; i< toWrite && total<n; i++){
//                 buf[total++] = tmp[i];
//             }
//
//         }
//         return total;
//
//     }
//
//     // "leetcode"  5   total<n
// }