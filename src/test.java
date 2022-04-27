import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class test {
    public static void main(String[] args) {
        //Various operations here. Don't try this at home!
        //Calling doSomething method that may throw exception
        System.out.println("hello world");
        int[] scooters1 = {7, 4, 14};

        int[] scooters2 = {15, 7, 3, 10};

        int ans1 = solution(23, scooters1);   // 4
        int ans2 = solution(27, scooters2);   // 7
        System.out.println(ans1);
        System.out.println(ans2);

        test2();
    }

    public static void test2() {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> permutation = new ArrayList<>();

        permutation.add(1);
        permutation.add(4);
        permutation.add(3);

        res.add(permutation);

        System.out.println(res);
        String randomString = usingUUID();
        System.out.println("Random string is: " + randomString);
        System.out.println("Random string of 12 characters is: "
                + randomString.substring(0, 12));
    }


    static String usingUUID() {
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString().replaceAll("-", "");
    }


    public static int solution(int finish, int[] scooters) {

        if (scooters == null || scooters.length == 0)
            return finish;

        Arrays.sort(scooters);
        System.out.println(scooters.length);

        int distance = 1, curPOS = 0;

        for (int scooter : scooters) {
            if (curPOS < scooter && scooter <= finish) {

                curPOS = scooter;
                distance += 1;
                if (curPOS + 10 < scooters[scooters.length - 1]) {
                    curPOS += 10;
                    distance += 1;
                }
                System.out.println("curPos: " + curPOS + " distance: " + distance);

            }


        }
        while (curPOS < finish) {
            if (curPOS + 10 <= finish) {
                curPOS += 10;
                distance += 1;
            } else if (curPOS < finish && curPOS + 10 > finish) {
                distance += 1;
                curPOS += 1;
            }
            System.out.println("curPos: " + curPOS + " distance: " + distance);
        }


        return distance;

    }


}
