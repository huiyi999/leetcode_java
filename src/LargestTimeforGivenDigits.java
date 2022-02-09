import java.util.ArrayList;
import java.util.List;
// https://leetcode.com/problems/largest-time-for-given-digits/

//Largest Time for Given Digits


public class LargestTimeforGivenDigits {
    /**
     * Approach 1: Using For Loop to Get Permutations
     * 不适用 Backtracking 的方法，而是采用 for 循环遍历的方法。
     * 寻找所有 i, j, k, l 的组合，分别对应时间的：
     * 小时第一位，小时第二位，分钟第一位，分钟第二位。
     * 从而来获取所有的时间。
     * <p>
     * 时间复杂度：O(4*4*4) = O(1)
     * 空间复杂度：O(1)
     */
    public static String largestTimeFromDigits(int[] arr) {
        String str = "";

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    // the last indices l = 6 - i - j - k; 0+1+2+3=6
                    // avoid duplicate among i, j, k.
                    if (i == j || i == k || j == k) {
                        continue;
                    }
                    String hour = "" + arr[i] + arr[j];
                    String minute = "" + arr[k] + arr[6 - i - j - k];
                    String time = hour + ":" + minute;
                    // hour < 24; minute < 60; update result.
                    if (hour.compareTo("24") < 0 && minute.compareTo("60") < 0 && str.compareTo(time) < 0) {
                        str = time;
                    }


                }
            }
        }
        return str;
    }

    /**
     * Approach 2: Using DFS(Backtracking) to Get Permutations
     * 因此本题并没有考察什么难的算法，我们只需要使用 Backtracking 的方法
     * 列举出所有的 Permutations,并判断各个 组合 是否合法（时间）。
     * 然后取最大值即可。
     * <p>
     * 时间复杂度：O(4!) = O(1)
     * 空间复杂度：O(1)
     */

    public static String largestTimeFromDigits2(int[] A) {
        List<List<Integer>> permutations = new ArrayList<>();
        boolean[] visited = new boolean[A.length];
        dfs(A, permutations, visited, new ArrayList<>());
        int hourRst = -1, minuteRst = -1;
        for (List<Integer> time : permutations) {
            int hour = getHour(time);
            int minute = getMinute(time);
            // 判断该排列所得的时间（小时和分钟）合法，并且大于当前的最大时间，则更新最大值
            if ((hour > hourRst && minute != -1) || (hour == hourRst && minute > minuteRst)) {
                hourRst = hour;
                minuteRst = minute;
            }
        }
        // 这里虽然利用 String.format() 会使得代码更加简洁优美
        // 但是更加高效的做法是使用 StringBuilder 将最大值的permutation结果拼接起来（可以快上一倍的时间）
        // sb.append(time.get(0)).append(time.get(1)).append(":").append(time.get(2)).append(time.get(3));
        return (hourRst != -1 && minuteRst != -1) ? String.format("%02d:%02d", hourRst, minuteRst) : "";
    }

    //4*3*2*1=24,24种排列组合
    static void dfs(int[] A, List<List<Integer>> permutations, boolean[] visited, List<Integer> list) {
        if (list.size() == A.length) {
            permutations.add(new ArrayList<>(list));
//            System.out.println("return ");
            return;
        }

        // 递归dfs, 遇到return，一层一层回，直到for loop（i < A.length）
        for (int i = 0; i < A.length; i++) {
//            System.out.println("i: " + i);
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            list.add(A[i]);
//            System.out.println("list: " + list);
            dfs(A, permutations, visited, list);           //每次调用dfs i从0开始
//            System.out.println("dfs i: " + i);
            list.remove(list.size() - 1);
//            System.out.println("list remove: " + list);
            visited[i] = false;                            //return 到这里, 下一步执行i++，直到i < A.length
        }
    }

    // 获取当前排列时间的 小时，如果非法则返回 -1
    static int getHour(List<Integer> time) {
        int hour = time.get(0) * 10 + time.get(1);
        if (hour >= 0 && hour <= 23) {
            return hour;
        } else {
            return -1;
        }
    }

    // 获取当前排列时间的 分钟，如果非法则返回 -1
    static int getMinute(List<Integer> time) {
        int minutes = time.get(2) * 10 + time.get(3);
        if (minutes >= 0 && minutes <= 59) {
            return minutes;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};    //"23:41"
        int[] arr1 = {5, 5, 5, 5};   //""
        int[] arr2 = {0, 0, 0, 0};   //"00:00"
        int[] arr3 = {0, 0, 1, 0};   //"10:00"

//        System.out.println(largestTimeFromDigits(arr));
//        System.out.println(largestTimeFromDigits(arr1));
//        System.out.println(largestTimeFromDigits(arr2));
//        System.out.println(largestTimeFromDigits(arr3));

        System.out.println(largestTimeFromDigits2(arr));
        System.out.println(largestTimeFromDigits2(arr1));
        System.out.println(largestTimeFromDigits2(arr2));
        System.out.println(largestTimeFromDigits2(arr3));

    }
}
