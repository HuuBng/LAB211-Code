package BikeStoresManagementSystem;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Tool {

    public static final Scanner sc = new Scanner(System.in);


    public static void menu(ArrayList<Objects> opts) {
        for (int i = 0; i < opts.size(); i++) {
            System.out.println((i + 1) + ". " + opts.get(i));
        }

    }

    public static void menu(Objects... opts) {
        for (int i = 0; i < opts.length; i++) {
            System.out.println((i + 1) + ". " + opts[i].toString());
        }
    }

    public static int int_menu(ArrayList<String> opts) {

        int N = opts.size();

        for (int i = 0; i < N; i++) {
            System.out.println((i + 1) + ". " + opts.get(i));
        }

        String inputString;
        Pattern inputPattern = Pattern.compile("\\d+");

        do {
            System.out.print("Please choose an option: 1..." + N + ": ");
            inputString = readStr();
            if (!inputPattern.matcher(inputString).find()) {
                System.err.println("Please enter a digit!!");
            }
        } while (!inputPattern.matcher(inputString).find());

        return Integer.parseInt(inputString);
    }

    public static int int_menu(Objects... opts) {
        int N = opts.length;
        for (int i = 0; i < N; i++) {
            System.out.println((i + 1) + ". " + opts[i].toString());
        }

        String inputString;
        Pattern inputPattern = Pattern.compile("\\d+");

        do {
            System.out.print("Please choose an option: 1..." + N + ": ");
            inputString = readStr();
            if (!inputPattern.matcher(inputString).find()) {
                System.err.println("Please enter a digit!!");
            }
        } while (!inputPattern.matcher(inputString).find());

        return Integer.parseInt(inputString);
    }

    public static String readStr(String prompt) {
        System.out.print(prompt + ": ");
        return sc.nextLine().trim();
    }

    public static String readStr() {
        return sc.nextLine().trim();
    }

    /**
     * Automatically generates an increasing code.
     *
     * @param prefix    Ex: P
     * @param length    Ex: 7
     * @param curNumber Ex: 123
     * @return Ex: P0000123
     */
    public static String generateCode(String prefix, int length, int curNumber) {
        String formatStr = "%0" + length + "d";
        return prefix + String.format(formatStr, curNumber);
    }

    /**
     * Get number in a given code with prefix.
     *
     * @param code   Ex: P0000123
     * @param prefix Ex: P0000
     * @return Ex: 123
     */
    public static int getNumberInCode(String code, String prefix) {
        return Integer.parseInt(code.substring(prefix.length()));
    }

    /**
     * Get number in a given code with prefix length.
     *
     * @param code         Ex: P0000123
     * @param prefixLength Ex: 5
     * @return Ex: 123
     */
    public static int getNumberInCode(String code, int prefixLength) {
        return Integer.parseInt(code.substring(prefixLength));
    }

    public static String generateCodeFromStr() {
        Pattern inputPattern = Pattern.compile("\\d+");
        String input;
        do {
            input = readStr("Enter ID number");
            if (!inputPattern.matcher(input).find()) {
                System.err.println("Please enter a valid ID number");
            }
        } while (!inputPattern.matcher(input).find());

        int curNum = Integer.parseInt(input);

        return generateCode("P", 3, curNum);
    }
}
