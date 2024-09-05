package BikeStoresManagementSystem;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Menu {

    public static final Scanner sc = new Scanner(System.in);

    public int int_getChoice(ArrayList<String> Opts) {

        int N = Opts.size();

        for (int i = 0; i < N; i++) {
            System.out.println((i + 1) + ". " + Opts.get(i));
        }

        String inputString;
        Pattern inputPattern = Pattern.compile("\\d+");

        do {
            System.out.print("Please choose an option: 1..." + N + ": ");
            inputString = sc.nextLine().trim();
            if (!inputPattern.matcher(inputString).find()) {
                System.err.println("Please enter a digit!!");
            }
        } while (!inputPattern.matcher(inputString).find());

        return Integer.parseInt(inputString);
    }
}
