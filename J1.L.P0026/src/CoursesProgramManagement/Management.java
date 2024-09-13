package CoursesProgramManagement;

import java.util.ArrayList;

import static CoursesProgramManagement.Tool.*;

public class Management {
    TopicList tList;
    CourseList cList;
    LearnerList lList;

    public Management() {
        tList = new TopicList();
        cList = new CourseList();
        lList = new LearnerList();
    }

    public void manageTopic() {
        ArrayList<String> opts = new ArrayList<>();

        opts.add("Add topic");
        opts.add("Update topic");
        opts.add("Delete topic");
        opts.add("Display all topics");
        opts.add("Exit");

        int choice;
        do {
            choice = int_menu2(opts);
            switch (choice) {
                case 1:
                    do {
                        tList.addTopic();
                    } while (exitChoice("Add another", "Exit"));
                    break;
                case 2:
                    do {
                        tList.updateTopic();
                    } while (exitChoice("Update another", "Exit"));
                    break;
                case 3:
                    do {
                        tList.deleteTopic();
                    } while (exitChoice("Delete another", "Exit"));
                    break;
                case 4:
                    do {
                        tList.displayALL();
                    } while (exitChoice("Display again", "Exit"));
                    break;
                case 5:
                    break;
                default:
                    System.err.println("Please enter a valid number");
            }
        } while (choice != opts.size());
    }

    public void manageCourse() {
        ArrayList<String> opts = new ArrayList<>();

        opts.add("Add course");
        opts.add("Update course");
        opts.add("Delete course");
        opts.add("Display courses information");
        opts.add("Exit");

        int choice;
        do {
            choice = int_menu2(opts);
            switch (choice) {
                case 1:
                    do {
                        cList.addCourse(tList);
                    } while (exitChoice("Add another", "Exit"));
                    break;
                case 2:
                    do {
                        cList.updateCourse(tList);
                    } while (exitChoice("Update another", "Exit"));
                    break;
                case 3:
                    do {
                        cList.deleteCourse();
                    } while (exitChoice("Delete another", "Exit"));
                    break;
                case 4:
                    do {
                        cList.displayCourses(lList);
                    } while (exitChoice("Display again", "Exit"));
                    break;
                case 5:
                    break;
                default:
                    System.err.println("Please enter a valid number");
            }
        } while (choice != opts.size());
    }

    public void manageLearner() {
        ArrayList<String> opts = new ArrayList<>();

        opts.add("Add learner");
        opts.add("Add scores to learner");
        opts.add("Delete learner");
        opts.add("Display learners information");
        opts.add("Exit");

        int choice;
        do {
            choice = int_menu2(opts);
            switch (choice) {
                case 1:
                    do {
                        lList.addLearner(cList);
                    } while (exitChoice("Add another", "Exit"));
                    break;
                case 2:
                    do {
                        lList.updateScore();
                    } while (exitChoice("Add another", "Exit"));
                    break;
                case 3:
                    do {
                        lList.deleteLearner();
                    } while (exitChoice("Delete another", "Exit"));
                    break;
                case 4:
                    do {
                        lList.displayLearners();
                    } while (exitChoice("Display again", "Exit"));
                    break;
                case 5:
                    break;
                default:
                    System.err.println("Please enter a valid number");
            }
        } while (choice != opts.size());
    }

    public void search() {
        ArrayList<String> opts = new ArrayList<>();

        opts.add("Search topic");
        opts.add("Search course");
        opts.add("Exit");

        int choice;
        do {
            choice = int_menu2(opts);
            switch (choice) {
                case 1:
                    do {
                        tList.searchPartialName();
                    } while (exitChoice("Search again", "Exit"));
                    break;
                case 2:
                    int choice2;
                    do {
                        choice2 = int_menu("By topic", "By name");
                        switch (choice2) {
                            case 1:
                                cList.searchByTopic(tList, lList);
                                break;
                            case 2:
                                cList.searchPartialName(tList, lList);
                                break;
                            case 3:
                                break;
                            default:
                                System.err.println("Please enter valid number");
                        }
                    } while (choice2 != 3);
                    break;
                case 3:
                    break;
                default:
                    System.err.println("Please enter a valid number");
            }
        } while (choice != opts.size());
    }

    public void saveToFile() {

    }
}
