import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 339. Nested List Weight Sum
 * <p>
 * You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists.
 * <p>
 * The depth of an integer is the number of lists that it is inside of. For example, the nested list [1,[2,2],[[3],2],1] has each integer's value set to its depth.
 * <p>
 * Return the sum of each integer in nestedList multiplied by its depth.
 */

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 */
// public interface NestedInteger {
//     // Constructor initializes an empty nested list.
//     public NestedInteger();
//
//     // Constructor initializes a single integer.
//     public NestedInteger(int value);
//
//     // @return true if this NestedInteger holds a single integer, rather than a nested list.
//     public boolean isInteger();
//
//     // @return the single integer that this NestedInteger holds, if it holds a single integer
//     // Return null if this NestedInteger holds a nested list
//     public Integer getInteger();
//
//     // Set this NestedInteger to hold a single integer.
//     public void setInteger(int value);
//
//     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
//     public void add(NestedInteger ni);
//
//     // @return the nested list that this NestedInteger holds, if it holds a nested list
//     // Return empty list if this NestedInteger holds a single integer
//     public List<NestedInteger> getList();
// }


public class NestedListWeightSum {
    // similiar to tree level order traverse

    // public int depthSum2(List<NestedInteger> nestedList) {
    //     if (nestedList == null) {
    //         return 0;
    //     }
    //
    //     int sum = 0;
    //     int level = 1;
    //
    //     Queue<NestedInteger> queue = new LinkedList<NestedInteger>(nestedList);
    //     while (queue.size() > 0) {
    //         int size = queue.size();
    //
    //         for (int i = 0; i < size; i++) {
    //             NestedInteger ni = queue.poll();
    //
    //             if (ni.isInteger()) {
    //                 sum += ni.getInteger() * level;
    //             } else {
    //                 queue.addAll(ni.getList());
    //             }
    //         }
    //
    //         level++;
    //     }
    //
    //     return sum;
    // }
    //
    //
    // private int ans = 0;
    //
    // public int depthSum(List<NestedInteger> nestedList) {
    //     bfs(nestedList, 1);
    //     return ans;
    // }
    //
    // void bfs(List<NestedInteger> nestedList, int depth) {
    //     for (NestedInteger nested : nestedList) {
    //         if (nested.isInteger())
    //             ans += nested.getInteger() * depth;
    //         else
    //             bfs(nested.getList(), depth + 1);
    //     }
    // }
}