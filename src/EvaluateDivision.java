
import java.util.*;


/**
 * You are given equations in the format A / B = k, where A and B are variables represented as strings,
 * and k is a real number (floating-point number). Given some queries, return the answers.
 * If the answer does not exist, return -1.0.
 * <p>
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero
 * and there is no contradiction.
 */

/**
 * Constraints:
 * <p>
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= equations[i][0], equations[i][1] <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= queries[i][0], queries[i][1] <= 5
 * equations[i][0], equations[i][1], queries[i][0], queries[i][1] consist of lower case English letters and digits.
 */

class Edge {

    String v;
    double value;

    public Edge (String v, double value) {

        this.v = v;
        this.value = value;
    }
}
public class EvaluateDivision {
    private static void addEdge(Map<String, List<Edge>> map, String u, String v, double value) {

        if (!map.containsKey (u)) {
            map.put (u, new ArrayList<> ());
        }

        map.get (u).add (new Edge (v, value));
    }

    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] answer = new double[queries.size()];
        Map<String, List<Edge>> map = new HashMap<>();

        for (int i = 0; i < values.length; i++) {
            List<String> equation = equations.get(i);
            addEdge(map, equation.get(0), equation.get(1), values[i]);
            addEdge(map, equation.get(1), equation.get(0), 1 / values[i]);
        }

        for (int i = 0; i < answer.length; i++) {
            List<String> query = queries.get(i);
            answer[i] = dfs(map, new HashSet<>(), query.get(0), query.get(1));
        }

        return answer;
    }


    private static double dfs(Map<String, List<Edge>> map, Set<String> set, String u, String v) {

        if (!map.containsKey (u) || !map.containsKey (v)) {
            return -1;
        }
        else if (u.equals (v)) {
            return 1;
        }

        for (Edge edge : map.get (u)) {
            if (set.contains (edge.v)) {
                continue;
            }
            else if (edge.v.equals (v)) {
                return edge.value;
            }

            set.add (u);
            double val = dfs (map, set, edge.v, v);
            if (val != -1) {
                return val * edge.value;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        List<List<String>> equations1 = new ArrayList<>();

        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        equations1.add(list1);

        List<String> list2 = new ArrayList<>();
        list2.add("b");
        list2.add("c");
        equations1.add(list2);

        double[] values1 = {2.0, 3.0};

        List<List<String>> queries1 = new ArrayList<>();

        List<String> list3 = new ArrayList<>();
        list3.add("a");
        list3.add("c");
        queries1.add(list3);

        List<String> list4 = new ArrayList<>();
        list4.add("b");
        list4.add("a");
        queries1.add(list4);

        List<String> list5 = new ArrayList<>();
        list5.add("a");
        list5.add("e");
        queries1.add(list5);

        List<String> list6 = new ArrayList<>();
        list6.add("a");
        list6.add("a");
        queries1.add(list6);

        List<String> list7 = new ArrayList<>();
        list7.add("x");
        list7.add("x");
        queries1.add(list7);

        double[] res1 = (double[]) calcEquation(equations1, values1, queries1);  // [6.00000,0.50000,-1.00000,1.00000,-1.00000]
        for (double d : res1) {
            System.out.println(d);
        }


        List<List<String>> equations2 = new ArrayList<>();
        List<String> list8 = new ArrayList<>();
        list8.add("bc");
        list8.add("cd");
        equations2.add(list1);
        equations2.add(list2);
        equations2.add(list8);

        double[] values2 = {1.5, 2.5, 5.0};


        List<List<String>> queries2 = new ArrayList<>();
        List<String> list9 = new ArrayList<>();
        list9.add("c");
        list9.add("b");
        List<String> list10 = new ArrayList<>();
        list10.add("bc");
        list10.add("cd");
        List<String> list11 = new ArrayList<>();
        list11.add("cd");
        list11.add("bc");
        queries2.add(list3);
        queries2.add(list9);
        queries2.add(list10);
        queries2.add(list11);

        double[] res2 = calcEquation(equations2, values2, queries2);  // [3.75000,0.40000,5.00000,0.20000]
        for (double d : res2) {
            System.out.println(d);
        }

        List<List<String>> equations3 = new ArrayList<>();
        equations3.add(list1);

        double[] values3 = {0.5};

        List<List<String>> queries3 = new ArrayList<>();
        List<String> list12 = new ArrayList<>();
        list12.add("a");
        list12.add("b");
        queries3.add(list12);
        queries3.add(list4);
        queries3.add(list3);
        List<String> list13 = new ArrayList<>();
        list13.add("x");
        list13.add("y");
        queries3.add(list13);

        double[] res3 = calcEquation(equations3, values3, queries3);  // Output: [0.50000,2.00000,-1.00000,-1.00000]
        for (double d : res3) {
            System.out.println(d);
        }

        List<List<String>> equations4 = new ArrayList<>();

        List<String> l1 = new ArrayList<>();
        l1.add("x1");
        l1.add("x2");
        List<String> l2 = new ArrayList<>();
        l2.add("x2");
        l2.add("x3");
        List<String> l3 = new ArrayList<>();
        l3.add("x3");
        l3.add("x4");
        List<String> l4 = new ArrayList<>();
        l4.add("x4");
        l4.add("x5");
        equations4.add(l1);
        equations4.add(l2);
        equations4.add(l3);
        equations4.add(l4);

        double[] values4 = {3.0,4.0,5.0,6.0};

        List<List<String>> queries4 = new ArrayList<>();
        List<String> l5 = new ArrayList<>();
        l5.add("x1");
        l5.add("x5");
        List<String> l6 = new ArrayList<>();
        l6.add("x5");
        l6.add("x2");
        List<String> l7 = new ArrayList<>();
        l7.add("x2");
        l7.add("x4");
        List<String> l8 = new ArrayList<>();
        l8.add("x2");
        l8.add("x2");
        List<String> l9 = new ArrayList<>();
        l9.add("x2");
        l9.add("x9");
        List<String> l10 = new ArrayList<>();
        l10.add("x9");
        l10.add("x9");
        queries4.add(l5);
        queries4.add(l6);
        queries4.add(l7);
        queries4.add(l8);
        queries4.add(l9);
        queries4.add(l10);

        double[] res4 = calcEquation(equations4, values4, queries4);  // Output: [0.50000,2.00000,-1.00000,-1.00000]
        for (double d : res4) {
            System.out.println(d);
        }


//[["x1","x2"],["x2","x3"],["x3","x4"],["x4","x5"]]

//[["x1","x5"],["x5","x2"],["x2","x4"],["x2","x2"],["x2","x9"],["x9","x9"]]
// output: [360.00000,0.00833,20.00000,1.00000,-1.00000,-1.00000]


    }
}
