/**
 * @author : chy
 * @date: 2022-04-29 5:04 p.m.
 */

public class Flatten2DVector {

    /**
     * Design an iterator to flatten a 2D vector. It should support the next and hasNext operations.
     * <p>
     * Implement the Vector2D class:
     * <p>
     * Vector2D(int[][] vec) initializes the object with the 2D vector vec.
     * next() returns the next element from the 2D vector and moves the pointer one step forward. You may assume that all the calls to next are valid.
     * hasNext() returns true if there are still some elements in the vector, and false otherwise.
     * <p>
     * Follow up: As an added challenge, try to code it using only iterators in C++ or iterators in Java.
     */

    class Vector2D {

        private int[][] v;
        private int rowIndex, colIndex;

        public Vector2D(int[][] vec) {
            this.v = vec;
            rowIndex = 0;
            colIndex = 0;
        }

        public int next() {
            hasNext();
            return v[rowIndex][colIndex++];
        }

        public boolean hasNext() {
            while (rowIndex < v.length && colIndex == v[rowIndex].length) {  // Move to next available vector, avoid empty rows
                rowIndex++;
                colIndex = 0;
            }
            return rowIndex < v.length;
        }

        // Follow up: As an added challenge, try to code it using only iterators in C++ or iterators in Java.
        // O(1)
    }

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D obj = new Vector2D(vec);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
}
