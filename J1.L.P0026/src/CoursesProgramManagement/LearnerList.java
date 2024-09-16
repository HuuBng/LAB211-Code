package CoursesProgramManagement;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

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

    private boolean isNotUniqueCourseID(CourseList courses, String id) {
        for (Course x : courses) {
            if (x.getCourseID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void addLearner(CourseList courses) {
        String id;
        do {
            id = generateIDFromStr("learner");
            if (isNotUniqueID(id)) {
                System.out.println("ERROR: ID not unique");
            }
        } while (isNotUniqueID(id));

        Learner learner = new Learner(id);

        String name;
        do {
            name = readStr("Enter LEARNER_NAME");
            if (name.isEmpty()) {
                System.out.println("ERROR: Name must not be empty");
            }
        } while (name.isEmpty());
        learner.setLearnerName(name);

        LocalDate dateOfBirth = readDate("Enter DATE_OF_BIRTH");
        learner.setDateOfBirth(dateOfBirth);

        double score;
        do {
            score = readDouble("Enter SCORE");
            if (score < 0.0 || score > 10.0) {
                System.out.println("ERROR: Please enter a valid SCORE");
            }
        } while (score < 0.0 || score > 10.0);
        learner.setScore(score);

        String courseId;
        int failCount = 0;
        do {
            courseId = generateIDFromStr("course");
            if (!isNotUniqueCourseID(courses, courseId)) {
                System.out.println("ERROR: Please enter a valid COURSE number");
                failCount++;
            }
            if (failCount == 5) {
                System.out.println("ERROR: Please create a COURSE first");
                return;
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
                System.out.println("ERROR: ID not found");
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
            System.out.println("ERROR: ID not found but pass check");
            return;
        }

        Learner learner = this.get(index);

        String scoreStr;
        do {
            scoreStr = readStr("Enter SCORE");
            double score = parseDoubleFromStr(scoreStr);
            if (score >= 0 && score <= 10) {
                learner.setScore(score);
                break;
            } else if (score != -1) {
                System.out.println("ERROR: Please enter a valid SCORE");
            }
        } while (!scoreStr.isEmpty());

        System.out.println("UPDATED: " + learner);

        this.set(index, learner);
    }

    public void deleteLearner() {
        String id;
        do {
            id = generateIDFromStr("learner");
            if (!isNotUniqueID(id)) {
                System.out.println("ERROR: ID not found");
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
            System.out.println("ERROR: ID not found but pass check");
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
            System.out.println("ERROR: DELETE aborted");
        }
    }

    public void displayLearners() {
        if (this.isEmpty()) {
            System.out.println("Empty list");
            return;
        }

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
