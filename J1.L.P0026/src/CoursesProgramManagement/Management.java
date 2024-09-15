package CoursesProgramManagement;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
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

        System.out.println("\t=== Manage Topic ===");
        opts.add("Add topic");
        opts.add("Update topic");
        opts.add("Delete topic");
        opts.add("Display all topics");
        opts.add("Exit");

        int choice;
        do {
            choice = int_menu2(1, opts);
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
                    System.out.println("ERROR: Please enter a valid number");
            }
        } while (choice != opts.size());
    }

    public void manageCourse() {
        ArrayList<String> opts = new ArrayList<>();

        System.out.println("\t=== Manage Course ===");
        opts.add("Add course");
        opts.add("Update course");
        opts.add("Delete course");
        opts.add("Display courses information");
        opts.add("Exit");

        int choice;
        do {
            choice = int_menu2(2, opts);
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
                    System.out.println("ERROR: Please enter a valid number");
            }
        } while (choice != opts.size());
    }

    public void manageLearner() {
        ArrayList<String> opts = new ArrayList<>();

        System.out.println("\t=== Manage Learner ===");
        opts.add("Add learner");
        opts.add("Add scores to learner");
        opts.add("Delete learner");
        opts.add("Display learners information");
        opts.add("Exit");

        int choice;
        do {
            choice = int_menu2(3, opts);
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
                    System.out.println("ERROR: Please enter a valid number");
            }
        } while (choice != opts.size());
    }

    public void search() {
        ArrayList<String> opts = new ArrayList<>();

        System.out.println("\t=== Search ===");
        opts.add("Search topic");
        opts.add("Search course");
        opts.add("Exit");

        int choice;
        do {
            choice = int_menu2(4, opts);
            switch (choice) {
                case 1:
                    do {
                        tList.searchPartialName();
                    } while (exitChoice("Search again", "Exit"));
                    break;
                case 2:
                    int choice2;
                    do {
                        System.out.println("Search:");
                        choice2 = int_menu("By topic", "By name", "Exit");
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
                                System.out.println("ERROR: Please enter valid number");
                        }
                    } while (choice2 != 3);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("ERROR: Please enter a valid number");
            }
        } while (choice != opts.size());
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream output = new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)))) {
            Object[] objects = {tList, cList, lList};
            output.writeObject(objects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Saved!!!");
        }
    }

    public void loadFromFile(String filename) {
        try (ObjectInputStream input = new ObjectInputStream(Files.newInputStream(Paths.get(filename)))) {
            Object[] objects = (Object[]) input.readObject();
            tList = (TopicList) objects[0];
            cList = (CourseList) objects[1];
            lList = (LearnerList) objects[2];
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("Loaded!!!");
        }
    }
}