package CoursesProgramManagement;

import java.io.Serializable;
import java.time.LocalDate;

public class Course implements Serializable {

    public enum Type {
        ONLINE,
        OFFLINE
    }

    public enum Status {
        ACTIVE,
        INACTIVE
    }

    private final String courseID;
    private String courseName;
    private Type courseType;
    private LocalDate beginDate;
    private LocalDate endDate;
    private int tuitionFee;
    private String topicID;
    private Status courseStatus;
    private int maxLearner;
    private int curLearner;

    public Course(String courseID) {
        this.courseID = courseID;
        this.curLearner = 0;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Type getCourseType() {
        return courseType;
    }

    public void setCourseType(Type courseType) {
        this.courseType = courseType;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(int tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public String getTopicID() {
        return topicID;
    }

    public void setTopicID(String topicID) {
        this.topicID = topicID;
    }

    public Status getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(Status courseStatus) {
        this.courseStatus = courseStatus;
    }

    public int getMaxLearner() {
        return maxLearner;
    }

    public void setMaxLearner(int maxLearner) {
        this.maxLearner = maxLearner;
    }

    public int getCurLearner() {
        return curLearner;
    }

    public void curLearnerPlus() {
        if (curLearner < maxLearner) {
            curLearner++;
        }
    }

    public void curLearnerMinus() {
        if (curLearner > 0) {
            curLearner--;
        }
    }

    @Override
    public String toString() {
        return courseID + ", " + courseName + ", " + courseType + ", " + beginDate + ", " + endDate + ", " + tuitionFee + ", " + topicID + ", " + curLearner + "/" + maxLearner + ", " + courseStatus;
    }

}
