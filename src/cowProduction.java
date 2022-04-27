import static java.lang.System.*;

public class cowProduction {

    /**
     * 母牛生产问题：
     *  假设农场中成熟的母牛每年都会生 1 头小母牛，并且永远不会死。第一年有 1 只小母牛，从第二年开始，母牛开始生小母牛。每只小母牛 3 年之后成熟又可以生小母牛。给定整数 N，求 N 年后牛的数量。
     * <p>
     * 解题思路：从第四年 开始，每年增加的奶牛数是三年前的奶牛数目，因此每年的数目是：
     * 前一年的数目+三年前的数目：
     * 因此状态转移方程是：dp[n]=dp[n-1]+dp[n-3];
     */

    public static int cowNum(int n) {
        if (n < 4) return n;

        int pre1 = 1, pre2 = 2, pre3 = 3;

        for (int i = 4; i <= n; i++) {
            int cur = pre3 + pre1;
            pre1 = pre2;
            pre2 = pre3;
            pre3 = cur;

        }
        return pre3;
    }


    public static void main(String[] args) {
        System.out.println(cowNum(6));


    }
}

