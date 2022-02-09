import java.util.ArrayList;
import java.util.List;


/**
 * Find all possible combinations of k numbers that add up to a number n,
 * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 * <p>
 * Note:
 * <p>
 * All numbers will be positive integers.
 * The solution set must not contain duplicate combinations.
 */
public class CombinationSumIII {
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> combination = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < 10; i++)
            numbers.add(i);


        subset_combination(combination, k, n, list, numbers);


        return combination;
    }

    public static void subset_combination(List<List<Integer>> combination, int k, int n, List<Integer> list, List<Integer> numbers) {
        int sum = 0;
        for (int x: list) sum+=x;

        if (sum == n && list.size() == k){
            combination.add(list);
        }

        if (sum > n)
            return;
        for (int i = 0; i < numbers.size(); i++) {
            List<Integer> remaining = numbers.subList(i + 1, numbers.size());
            List<Integer> list_rec = new ArrayList<>(list);
            list_rec.add(numbers.get(i));
            subset_combination(combination,k, n, list_rec, remaining);

        }
    }


    public static void main(String[] args) {
        int k1 = 3;
        int n1 = 7;

        int k2 = 3;
        int n2 = 9;
        System.out.println(combinationSum3(k1, n1));  //[[1,2,4]]
        System.out.println(combinationSum3(k2, n2));  //[[1,2,6], [1,3,5], [2,3,4]]
    }
}
