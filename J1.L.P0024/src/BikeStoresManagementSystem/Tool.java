package BikeStoresManagementSystem;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Tool {

    public static final Scanner sc = new Scanner(System.in);

    public static int int_menu(Object... opts) {

        int N = opts.length;

        for (int i = 0; i < N; i++) {
            System.out.println((i + 1) + ". " + opts[i].toString());
        }

        return readInt("Please choose an option: 1..." + N + ": ");
    }

    public static int int_menu(ArrayList<String> opts) {

        int N = opts.size();

        for (int i = 0; i < N; i++) {
            System.out.println((i + 1) + ". " + opts.get(i));
        }

        return readInt("Please choose an option: 1..." + N + ": ");
    }

    public static String readStr(String prompt) {
        System.out.print(prompt + ": ");
        return sc.nextLine().trim();
    }

    public static String readStr() {
        return sc.nextLine().trim();
    }

    public static int readInt(String prompt, String type) {
        Pattern inputPattern = Pattern.compile("\\d+");
        String input;
        do {
            input = readStr(prompt);
            if (!inputPattern.matcher(input).find()) {
                System.err.println("Please enter a valid " + type);
            }
        } while (!inputPattern.matcher(input).find());

        input = input.replaceAll("\\D+", "");

        return (int) Math.floor(Double.parseDouble(input));
    }

    public static int readInt(String prompt) {
        Pattern inputPattern = Pattern.compile("\\d+");
        String input;
        do {
            input = readStr(prompt);
            if (!inputPattern.matcher(input).find()) {
                System.err.println("Please enter a valid number");
            }
        } while (!inputPattern.matcher(input).find());

        input = input.replaceAll("\\D+", "");

        return (int) Math.floor(Double.parseDouble(input));
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

    public static String generateCodeFromStr() {
        int curNum = readInt("Enter ID number", "ID number");
        return generateCode("P", 3, curNum);
    }

    public static boolean exitChoice(Object... opts) {
        System.out.println("Do you want to: ");
        int choice = int_menu(opts);
        return choice == 1;
    }
}
