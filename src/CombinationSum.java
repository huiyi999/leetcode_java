import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen numbers sum to target.
 * You may return the combinations in any order.
 * <p>
 * The same number may be chosen from candidates an unlimited number of times.
 * Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 */
public class CombinationSum {

    /**
     * approach: 221ms
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        if ((Arrays.stream(candidates).min().getAsInt()) > target)
            return result;

        subset_combination(result, target, list, candidates);
        return result;
    }

    public static void subset_combination(List<List<Integer>> combination, int target, List<Integer> list, int[] candidates) {
        int sum = 0;
        for (int x : list) sum += x;

        if (sum == target) {
            Collections.sort(list);
            if (combination.contains(list))
                return;
            else combination.add(list);
        }

        if (sum > target)
            return;
        for (int i = 0; i < candidates.length; i++) {
            List<Integer> list_rec = new ArrayList<>(list);
            list_rec.add(candidates[i]);
            subset_combination(combination, target, list_rec, candidates);

        }
    }


    /**
     * approach 2: dfs
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, res, new ArrayList<>(), 0, target);
        return res;

    }

    private static void dfs(int[] candidates, List<List<Integer>> res, List<Integer> list, int start, int target) {
        if (target < 0)
            return;
        if (target == 0)
            res.add(new ArrayList(list));

        for (int i = start; i < candidates.length; i++) {
            list.add(candidates[i]);
            dfs(candidates, res, list, i, target - candidates[i]);
            list.remove(list.size() - 1);
        }

    }

    public static void main(String[] args) {
        int[] candidates1 = {2, 3, 6, 7};
        int target1 = 7;
        List<List<Integer>> result1 = combinationSum(candidates1, target1); //[[2,2,3],[7]]
        for (List<Integer> list : result1) {
            System.out.println("list:");
            for (int num : list)
                System.out.println(num);
        }

        System.out.println("\n");
        int[] candidates2 = {2, 3, 5};
        int target2 = 8;
        List<List<Integer>> result2 = combinationSum(candidates2, target2); //[[2,2,2,2],[2,3,3],[3,5]]
        for (List<Integer> list : result2) {
            System.out.println("list:");
            for (int num : list)
                System.out.println(num);
        }

        System.out.println("\n");
        int[] candidates3 = {2};
        int target3 = 1;
        List<List<Integer>> result3 = combinationSum(candidates3, target3); //[]
        for (List<Integer> list : result3) {
            System.out.println("list:");
            for (int num : list)
                System.out.println(num);
        }

        System.out.println("\n");
        int[] candidates4 = {1};
        int target4 = 1;
        List<List<Integer>> result4 = combinationSum(candidates4, target4); // [[1]]
        for (List<Integer> list : result4) {
            System.out.println("list:");
            for (int num : list)
                System.out.println(num);
        }

        System.out.println("\n");
        int[] candidates5 = {1};
        int target5 = 2;
        List<List<Integer>> result5 = combinationSum(candidates5, target5); // [[1,1]]
        for (List<Integer> list : result5) {
            System.out.println("list:");
            for (int num : list)
                System.out.println(num);
        }
    }
}
