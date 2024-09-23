package CoursesProgramManagement;

import java.util.ArrayList;

import static CoursesProgramManagement.Tool.int_menu;

public class Main {

    public static void main(String[] args) {
        Management catalog = new Management();

        String dataFile = "J1.L.P0026/src/Data.dat";
        catalog.loadFromFile(dataFile);
        ArrayList<String> menu = new ArrayList<>();
        menu.add("Manage Topic");
        menu.add("Manage Course");
        menu.add("Manage Learner");
        menu.add("Search");
        menu.add("Save");
        menu.add("Quit");

        int choice;
        do {

            System.out.println(
                    "\n=======================================================================\n" +
                            "|| ┏┓            ┏┓               ┳┳┓                    ┏┓          ||\n" +
                            "|| ┃ ┏┓┓┏┏┓┏┏┓┏  ┃┃┏┓┏┓┏┓┏┓┏┓┏┳┓  ┃┃┃┏┓┏┓┏┓┏┓┏┓┏┳┓┏┓┏┓╋  ┗┓┓┏┏╋┏┓┏┳┓ ||\n" +
                            "|| ┗┛┗┛┗┻┛ ┛┗ ┛  ┣┛┛ ┗┛┗┫┛ ┗┻┛┗┗  ┛ ┗┗┻┛┗┗┻┗┫┗ ┛┗┗┗ ┛┗┗  ┗┛┗┫┛┗┗ ┛┗┗ ||\n" +
                            "||                      ┛                   ┛               ┛        ||\n" +
                            "=======================================================================\n");

            choice = int_menu(menu);
            System.out.println();
            switch (choice) {
                case 1:
                    catalog.manageTopic();
                    break;
                case 2:
                    catalog.manageCourse();
                    break;
                case 3:
                    catalog.manageLearner();
                    break;
                case 4:
                    catalog.search();
                    break;
                case 5:
                    catalog.saveToFile(dataFile);
                    break;
                default:
                    System.out.println("Exiting!!!");
            }
        } while (choice >= 1 && choice < menu.size());
    }
}
