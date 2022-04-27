import java.util.ArrayList;
import java.util.List;

public class BullsandCows {
    public static String getHint(String secret, String guess) {


        int bull = 0;  //right position
        int cow = 0;   //wrong position

        char[] list1 = new char[secret.length()];
        for (int i = 0; i < secret.length(); i++) {
            list1[i] = (secret.charAt(i));
        }

        char[] list2 = new char[guess.length()];
        for (int i = 0; i < guess.length(); i++) {
            list2[i] = (guess.charAt(i));
        }

        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                list1[i] = 'A';
                list2[i] = 'A';
                bull++;
            }

        }
        for (int i = 0; i < secret.length(); i++) {
            for (int j = 0; j < list1.length; j++) {
                if (list1[j] == guess.charAt(i) && list1[j] != 'A' && list2[i] != 'A' && list2[i] != 'B' && list1[j] != 'B') {
                    list1[j] = 'B';
                    list2[i] = 'B';
                    cow++;
                }

            }
        }
        String result = String.valueOf(bull) + 'A' + cow + 'B';

        return result;
    }

    public static void main(String[] args) {
        String secret1 = "1807";
        String guess1 = "7810";


        String secret2 = "1123";
        String guess2 = "0111";

        String secret3 = "011";
        String guess3 = "110";

        String secret4 = "11";
        String guess4 = "10";


        String secret5 = "1122";
        String guess5 = "0001";
        System.out.println(getHint(secret1, guess1));   //"1A3B"
        System.out.println(getHint(secret2, guess2));   //"1A1B"
        System.out.println(getHint(secret3, guess3));   //"1A2B"
        System.out.println(getHint(secret4, guess4));   //"1A0B"
        System.out.println(getHint(secret5, guess5));   //"0A1B"

    }
}
