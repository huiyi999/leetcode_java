import java.util.PriorityQueue;

/**
 * 1167. Minimum Cost to Connect Sticks
 * <p>
 * You have some number of sticks with positive integer lengths. These lengths are given as an array sticks, where sticks[i] is the length of the ith stick.
 * <p>
 * You can connect any two sticks of lengths x and y into one stick by paying a cost of x + y. You must connect all the sticks until there is only one stick remaining.
 * <p>
 * Return the minimum cost of connecting all the given sticks into one stick in this way.
 */
public class MinimumCosttoConnectSticks {

    public int connectSticks(int[] sticks) {
        if (sticks.length == 1) return 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : sticks) {
            pq.add(s);  // same as pq.offer(s);
        }
        // pq.addAll(Arrays.asList(sticks));
        // error: incompatible types: inference variable T has incompatible bounds
        // int[] is considered as a single Object instance since a primitive array extends from Object.
        // This would work if you have Integer[] instead of int[] since now you're sending an array of Object.

        int ans = 0;

        while (pq.size() > 1) {
            //int x = pq.poll();
            //int y = pq.poll();
            int cost = pq.poll() + pq.poll();
            ans += cost;
            pq.add(cost);
        }

        return ans;

        // greedy alg + priorityQueue
        // by adding the two shortest sticks we generate a local minima, and if we keep doing this, we will eventually generate the global minima.


    }
}
