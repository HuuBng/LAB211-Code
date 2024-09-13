package CoursesProgramManagement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import static CoursesProgramManagement.Tool.*;

public class LearnerList extends ArrayList<Learner> implements Serializable {

    public LearnerList() {
    }

    private boolean isNotUniqueID(String id) {
        for (Learner x : this) {
            if (x.getLearnerID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private boolean isNotUniqueCourseID(ArrayList<Course> courses, String id) {
        for (Course x : courses) {
            if (x.getCourseID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void addLearner(ArrayList<Course> courses) {
        String id;
        do {
            id = generateIDFromStr("learner");
            if (isNotUniqueID(id)) {
                System.err.println("ID not unique");
            }
        } while (isNotUniqueID(id));

        Learner learner = new Learner(id);

        String name;
        do {
            name = readStr("Enter LEARNER_NAME");
            if (name.isEmpty()) {
                System.err.println("Name must not be empty");
            }
        } while (name.isEmpty());
        learner.setLearnerName(name);

        Date dateOfBirth = readDate("Enter DATE_OF_BIRTH");
        learner.setDateOfBirth(dateOfBirth);

        double score;
        do {
            score = readDouble("Enter SCORE");
            if (score < 0 || score > 10) {
                System.err.println("Please enter a valid SCORE");
            }
        } while (score < 0 || score > 10);
        learner.setScore(score);

        String courseId;

        do {
            courseId = generateIDFromStr("course");
            if (!isNotUniqueCourseID(courses, courseId)) {
                System.err.println("Please enter a valid COURSE number");
            }
        } while (!isNotUniqueCourseID(courses, courseId));
        learner.setCourseID(courseId);

        this.add(learner);
    }

    public void updateScore() {
        String id;
        do {
            id = generateIDFromStr("learn");
            if (!isNotUniqueID(id)) {
                System.err.println("ID not found");
            }
        } while (!isNotUniqueID(id));

        int index = -1;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getLearnerID().equals(id)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.err.println("ID not found but pass check");
            return;
        }

        Learner learner = this.get(index);

        String scoreStr;
        do {
            scoreStr = readStr("Enter SCORE");
            double score = parseDoubleFromStr(scoreStr);
            if (score >= 0 && score <= 10) {
                learner.setScore(score);
            } else if (score != -1) {
                System.err.println("Please enter a valid SCORE");
            }
        } while (!scoreStr.isEmpty());

        System.out.println("OLD: " + this.get(index));
        System.out.println();
        System.out.println("NEW: " + learner);

        this.set(index, learner);
    }

    public void deleteLearner() {
        String id;
        do {
            id = generateIDFromStr("learner");
            if (!isNotUniqueID(id)) {
                System.err.println("ID not found");
            }
        } while (!isNotUniqueID(id));

        int index = -1;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getLearnerID().equals(id)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.err.println("ID not found but pass check");
            return;
        }

        System.out.println("Delete " + id + " ?");
        int choice = int_menu("Yes", "No");
        if (choice == 1) {
            this.remove(index);
            System.out.println("DELETE success");
        } else if (choice == 2) {
            System.out.println("DELETE canceled");
        } else {
            System.err.println("DELETE aborted");
        }
    }

    public void displayLearners() {
        System.out.println(" === Learner === ");
        for (Learner x : this) {
            if (x.getScore() >= 5) {
                System.out.println(x + ", PASS");
            } else {
                System.out.println(x + ", FAIL");
            }
        }
    }
}
