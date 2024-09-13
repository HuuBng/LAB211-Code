package CoursesProgramManagement;

import java.io.Serializable;
import java.util.Date;

public class Learner implements Serializable {
    private final String learnerID;
    private String learnerName;
    private Date dateOfBirth;
    private double score;
    private String courseID;

    public Learner(String learnerID) {
        this.learnerID = learnerID;
    }

    public Learner(String learnerID, String learnerName, Date dateOfBirth, double score, String courseID) {
        this.learnerID = learnerID;
        this.learnerName = learnerName;
        this.dateOfBirth = dateOfBirth;
        this.score = score;
        this.courseID = courseID;
    }

    public String getLearnerID() {
        return learnerID;
    }

    public String getLearnerName() {
        return learnerName;
    }

    public void setLearnerName(String learnerName) {
        this.learnerName = learnerName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        if (score > 10) {
            score = 10;
        } else if (score < 0) {
            score = 0;
        }
        this.score = score;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return learnerID + ", " + learnerName + ", " + dateOfBirth + ", " + score + ", " + courseID;
    }
}
