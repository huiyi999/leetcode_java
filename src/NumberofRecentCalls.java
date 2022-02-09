import java.util.LinkedList;
import java.util.Queue;

/**
 * You have a RecentCounter class which counts the number of recent requests within a certain time frame.
 *
 * Implement the RecentCounter class:
 *
 * RecentCounter() Initializes the counter with zero recent requests.
 * int ping(int t) Adds a new request at time t, where t represents some time in milliseconds,
 * and returns the number of requests that has happened in the past 3000 milliseconds (including the new request).
 * Specifically, return the number of requests that have happened in the inclusive range [t - 3000, t].
 * It is guaranteed that every call to ping uses a strictly larger value of t than the previous call.
 */
public class NumberofRecentCalls {

    /**
     * 因为调用是按时间顺序的，所以比较简单，维护一个queue就好， queue的一端是最新加入的，另外一端是时间最老的，
     * 每次加入的时候看看最老的那些是否应该pop，如果已经过期的话pop掉就好
     */
    private Queue<Integer> queue;
    public NumberofRecentCalls() {
        queue = new LinkedList<>();

    }

    public int ping(int t) {
        queue.add(t);
        while(queue.size() > 0) {
            int last = queue.peek();
            if(t-last > 3000) {
                queue.poll();
            }
            else {
                break;
            }
        }
        return queue.size();
    }
    public static void main(String[] args) {
        /**
         * Your RecentCounter object will be instantiated and called as such:
         * RecentCounter obj = new RecentCounter();
         * int param_1 = obj.ping(t);
         */

        NumberofRecentCalls obj =new NumberofRecentCalls();


        int param_1 = obj.ping(1);
        int param_2 = obj.ping(100);
        int param_3 = obj.ping(3001);
        int param_4 = obj.ping(3002);
        System.out.println(param_1);
        System.out.println(param_2);
        System.out.println(param_3);
        System.out.println(param_4);




//        Input
//                ["RecentCounter", "ping", "ping", "ping", "ping"]
//                [[], [1], [100], [3001], [3002]]
//        Output
//                [null, 1, 2, 3, 3]
//
//        Explanation
//        RecentCounter recentCounter = new RecentCounter();
//        recentCounter.ping(1);     // requests = [1], range is [-2999,1], return 1
//        recentCounter.ping(100);   // requests = [1, 100], range is [-2900,100], return 2
//        recentCounter.ping(3001);  // requests = [1, 100, 3001], range is [1,3001], return 3
//        recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002], range is [2,3002], return 3

    }
}
