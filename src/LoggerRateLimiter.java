import java.util.HashMap;

/**
 * @author : chy
 * @date: 2022-04-28 3:54 p.m.
 */

/**
 * Design a logger system that receives a stream of messages along with their timestamps. Each unique message should only be printed at most every 10 seconds (i.e. a message printed at timestamp t will prevent other identical messages from being printed until timestamp t + 10).
 * <p>
 * All messages will come in chronological order. Several messages may arrive at the same timestamp.
 * <p>
 * Implement the Logger class:
 * <p>
 * Logger() Initializes the logger object.
 * bool shouldPrintMessage(int timestamp, string message) Returns true if the message should be printed in the given timestamp, otherwise returns false.
 */
public class LoggerRateLimiter {

    private HashMap<String, Integer> msgMap;

    public LoggerRateLimiter() {
        msgMap = new HashMap<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!msgMap.containsKey(message) || msgMap.get(message) <= timestamp - 10) {
            msgMap.put(message, timestamp);
            return true;
        }
        return false;
    }
    // time O(1)  space O(m) m: the size of all incoming msg
}
