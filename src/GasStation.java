/**
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * <p>
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1).
 * You begin the journey with an empty tank at one of the gas stations.
 * <p>
 * Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.
 * <p>
 * Note:
 * <p>
 * If there exists a solution, it is guaranteed to be unique.
 * Both input arrays are non-empty and have the same length.
 * Each element in the input arrays is a non-negative integer.
 */

/**
 * Analysis
 *
 * To solve this problem, we need to understand and use the following 2 facts:
 * 1) if the sum of gas >= the sum of cost, then the circle can be completed.
 * 2) if A can not reach C in a the sequence of A-->B-->C, then B can not make it either.
 *
 *
 */
public class GasStation {
    /**
     * approach 1 ; 31ms
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0;

        for (int i = 0; i < gas.length; i++) {
            int tank = 0;
            start = i;
//            System.out.println("start station: "+start);
            tank += gas[i];
            int next = start;
            int j = 0;
            do {
                if (next == gas.length - 1)
                    next = 0;
                else
                    next++;
//                System.out.println("next station: "+next);
                if (next == 0)
                    tank = tank - cost[cost.length - 1];
                else tank = tank - cost[next - 1];

                if (tank < 0)
                    break;

                tank += gas[next];
//                System.out.println("total tank: "+tank);
                j++;
            } while (j < gas.length - 1);

            if (tank > 0) {
                if (next == gas.length - 1)
                    next = 0;
                else
                    next++;
//                 System.out.println("final station: "+next);
                if (next == 0)
                    tank = tank - cost[cost.length - 1];
                else tank = tank - cost[next - 1];
//                 System.out.println("final tank: "+tank);
                if (tank >= 0)
                    return start;
                else start = -1;
            } else start = -1;

        }
        return start;
    }

    /**
     * approach 2 ; 0ms
     * In the following solution, sumRemaining tracks the sum of remaining to the current index.
     * If sumRemaining < 0, then every index between old start and current index is bad,
     * and we need to update start to be the current index.
     */
    public static int canCompleteCircuit2(int[] gas, int[] cost) {
        int sumRemaining = 0;
        int start = 0;
        int total = 0;
        for (int i = 0; i < gas.length; i++) {

            int remaining = gas[i] - cost[i];

            //if sum remaining of (i-1) >= 0, continue
            if (sumRemaining >= 0) {
                sumRemaining += remaining;
            //otherwise, reset start index to be current
            } else {
                sumRemaining = remaining;
                start = i;
            }

            total += remaining;

        }

        if (total >= 0) return start;
        else return -1;
    }

    /**
     *First we take 0th station as our starting station
     * then, we will fill gas and subtract the cost to reach next station.
     * if filled < 0, this means we are lacking of gas to reach the station.
     * So, we will start freshly from next station
     * and we will also store the gas required to reach 0th station to ith station
     * because we need to circularly reach the starting station.
     * Instead of traversing again from 0th station
     * if we store gas required to reach 0th station to ith station,
     * if we have enough gas, then we will return starting station,
     * Otherwise return -1.
     */
    public int canCompleteCircuit3(int[] gas, int[] cost) {
        int ans = 0, filled = 0, required = 0;
        for(int i = 0; i < gas.length; i++){
            filled += gas[i] - cost[i];
            if(filled < 0){
                required += filled;
                ans = i + 1;
                filled = 0;
            }
        }
        return filled >= Math.abs(required) ? ans : -1;
    }

    public static void main(String[] args) {
        int[] gas1 = {1, 2, 3, 4, 5};
        int[] cost1 = {3, 4, 5, 1, 2};
        int[] gas2 = {2, 3, 4};
        int[] cost2 = {3, 4, 3};


        System.out.println(canCompleteCircuit(gas1, cost1));   //Output: 3
        System.out.println(canCompleteCircuit(gas2, cost2));   //Output: -1

    }
}
