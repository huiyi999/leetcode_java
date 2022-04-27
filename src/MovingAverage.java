import java.util.LinkedList;
import java.util.Queue;

/**
 * 346. Moving Average from Data Stream
 * <p>
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 * <p>
 * Implement the MovingAverage class:
 * <p>
 * MovingAverage(int size) Initializes the object with the size of the window size.
 * double next(int val) Returns the moving average of the last size values of the stream.
 */


public class MovingAverage {
    Queue<Integer> q;
    double sum = 0.0;
    int size;

    public MovingAverage(int size) {
        this.q = new LinkedList();
        this.size = size;

    }

    public double next(int val) {
        if (q.size() == this.size)
            sum = sum - q.poll();

        q.offer(val);
        sum += val;

        return sum / q.size();
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */