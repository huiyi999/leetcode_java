/**
 *
 * 158. Read N Characters Given read4 II - Call Multiple Times
 *
 * Given a file and assume that you can only read the file using a given method read4,
 * implement a method read to read n characters. Your method read may be called multiple times.
 *
 * Input: file = "abc", queries = [1,2,1]
 * Output: [1,2,0]
 */
// public class ReadNCharactersGivenReadII extends Reader4 {
//
//
//     /**
//      * @param buf Destination buffer
//      * @param n   Number of characters to read
//      * @return The number of actual characters read
//      */
//     char[] prevBuf = new char[4];   // Keep a buffer of size 4 as a class variable
//     int prevSize = 0;               // tracks the actual size of prevBuf
//     int prevIndex = 0;              // tracks the index of next character to read from prevBuf
//
//     public int read(char[] buf, int n) {
//         int counter = 0;  // index point to the current call's char[] buf,
//
//         while (counter < n) {
//             if (prevIndex < prevSize) {
//
//                 buf[counter++] = prevBuf[prevIndex++];
//
//             } else {
//                 // preIndex >= preSize  have read all characters from prevBuf or first read
//                 prevSize = read4(prevBuf);
//                 prevIndex = 0;
//                 if (prevSize == 0)
//                     break;
//             }
//
//
//         }
//         return counter;
//     }
// }
