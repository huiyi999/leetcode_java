import java.util.ArrayList;
import java.util.List;

public class OA {

    public static void main(String[] args) {
        String output1 = solution1(10, new String[]{"L", "L", "C0", "L", "C3"});
        System.out.println(output1);


        int output2 = solution2(new int[][][]{{{60, 180, 240}}, {{210, 240, 0}, {570, 600, 0}}, {{105, 210, 0}}}, new int[]{450, 500});
    }

    private static String solution1(int n, String[] operations) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++)
            sb.append("0");
        for (String op : operations) {
            if (op.equals("L")) {
                int ind = sb.indexOf("0");
                if (ind != -1)
                    sb.setCharAt(ind, '1');
            } else {
                int ind = Integer.valueOf(op.substring(1));
                if (ind < n) {
                    sb.setCharAt(ind, '0');
                }
            }
        }
        return sb.toString();
    }

    private static int solution2(int[][][] schedules, int[] newMeeting) {
        int output = 0;

        List<List<int[]>> lists = new ArrayList<>();

        for (int i = 0; i < schedules.length; i++) {
            boolean isAvailable = true;
            List<int[]> employee = new ArrayList<>();
            for (int j = 0; j < schedules[i].length; j++) {
                int start = schedules[i][j][0];
                int end = schedules[i][j][1];
                int recurrence = schedules[i][j][2];
                if (recurrence == 0) {
                    employee.add(new int[]{start, end});
                } else {
                    while (start < 24 * 60 && end <= 24 * 60) {
                        employee.add(new int[]{start, end});
                        start += recurrence;
                        end += recurrence;
                        if (end > 24 * 60)
                            end = 24 * 60;
                    }
                }
            }
            lists.add(employee);
        }
        
        for (List<int[]> employee : lists) {
            for (int i = 0; i < employee.size(); i++) {
                if (i == 0) {
                    if (newMeeting[1] < employee.get(0)[0]) {
                        output++;
                        break;
                    }
                }

                int[] pair = employee.get(i);


            }


        }


        return output;
    }

}
