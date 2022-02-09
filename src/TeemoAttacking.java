/**
 * In LOL world, there is a hero called Teemo and his attacking can make his enemy Ashe be in poisoned condition.
 * Now, given the Teemo's attacking ascending time series towards Ashe and the poisoning time duration per Teemo's attacking,
 * you need to output the total time that Ashe is in poisoned condition.
 * <p>
 * You may assume that Teemo attacks at the very beginning of a specific time point,
 * and makes Ashe be in poisoned condition immediately.
 */
public class TeemoAttacking {
    public static int findPoisonedDuration(int[] timeSeries, int duration) {

        /**
         * approach1: 思路就是先算出每次中毒不冲突的总时间，再把之间冲突的时间给减去。
         */
        int result1 = timeSeries.length * duration;
        for(int i=1; i<timeSeries.length; i++) {
            if(timeSeries[i] - timeSeries[i-1] < duration) {
                result1 -= duration - (timeSeries[i] - timeSeries[i-1]);
            }
        }

        /**
         * approach2: 1ms
         */
        int stop = -1, poisoned = 0;

        for(int time : timeSeries)
        {
            if(time > stop) {
                stop = duration+time-1;
                poisoned += duration;
            }
            else {
                int new_stop = duration+time-1;
                poisoned += new_stop-stop;
                stop = new_stop;
            }
        }


        /**
         * approach3: 3ms
         */
        int result = 0;
        for (int i = 0; i < timeSeries.length - 1; i++) {
            System.out.println("timeSeries[i]: "+timeSeries[i]);
            System.out.println("timeSeries[i + 1]: "+timeSeries[i + 1]);
            System.out.println("timeSeries[i] + duration - 1: "+(timeSeries[i] + duration - 1));
            if (timeSeries[i + 1] <= timeSeries[i] + duration - 1) {

                result += timeSeries[i + 1] - timeSeries[i];
                System.out.println("result: "+result);
            } else {
                result += duration;
                System.out.println("result: "+result);
            }
        }
        if(timeSeries.length>=1)
            result+=duration;
        return result;
    }

    public static void main(String[] args) {
        int[] timeSeries1 = {1, 4};
        int duration1 = 2;

        int[] timeSeries2 = {1, 2};
        int duration2 = 2;


        int[] timeSeries3 ={};
        int duration3 =100000;
        System.out.println(findPoisonedDuration(timeSeries1, duration1));  //Output:4
//      Explanation: At time point 1, Teemo starts attacking Ashe and makes Ashe be poisoned immediately.
//                   This poisoned status will last 2 seconds until the end of time point 2.
//                   And at time point 4, Teemo attacks Ashe again, and causes Ashe to be in poisoned status for another 2 seconds.
//                   So you finally need to output 4.
        System.out.println(findPoisonedDuration(timeSeries2, duration2));  //Output:3
//        Explanation:
//        At time point 1, Teemo starts attacking Ashe and makes Ashe be poisoned.
//        This poisoned status will last 2 seconds until the end of time point 2.
//        However, at the beginning of time point 2, Teemo attacks Ashe again who is already in poisoned status.
//        Since the poisoned status won't add up together, though the second poisoning attack will still work at time point 2,
//        it will stop at the end of time point 3. So you finally need to output 3.

        System.out.println(findPoisonedDuration(timeSeries3, duration3));  //Output:100000
    }
}
