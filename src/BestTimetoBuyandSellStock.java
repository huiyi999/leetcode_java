/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * <p>
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 * <p>
 * Note that you cannot sell a stock before you buy one.
 */
public class BestTimetoBuyandSellStock {
    public static int maxProfit(int[] prices) {
        int max=0;

        for (int i = 0; i < prices.length; i++) {
            for (int j = 0; j < i; j++) {
                int tmp = prices[i] - prices[j];
                if (tmp > max)
                    max = tmp;
            }
        }

        /**
         * O(n) Time O(1) Space
         */
        int minSoFar = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            minSoFar = Math.min(minSoFar, price);
            maxProfit = Math.max(maxProfit, price - minSoFar);
        }


        return max;
    }

    public static void main(String[] args) {

        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices1));   //Output: 5
        // Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6 - 1 = 5.
        // Not 7 - 1 = 6, as selling price needs to be larger than buying price.

        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println(maxProfit(prices2));  // Output: 0
        // Explanation: In this case,no transaction is done, i.e.max profit = 0.
    }
}
