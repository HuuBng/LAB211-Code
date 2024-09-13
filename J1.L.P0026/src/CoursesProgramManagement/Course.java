package CoursesProgramManagement;

import java.io.Serializable;
import java.util.Date;

public class Course implements Serializable {

    public enum Type {
        ONLINE,
        OFFLINE
    }

    private final String courseID;
    private String courseName;
    private Type courseType;
    private Date beginDate;
    private Date endDate;
    private int tuitionFee;
    private String topicID;

    public Course(String courseID) {
        this.courseID = courseID;
    }

    public Course(String courseID, String courseName, Course.Type courseType, Date beginDate, Date endDate, int tuitionFee, String topicID) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseType = courseType;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.tuitionFee = tuitionFee;
        this.topicID = topicID;
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

    public Course.Type getCourseType() {
        return courseType;
    }

    public void setCourseType(Type courseType) {
        this.courseType = courseType;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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

    @Override
    public String toString() {
        return courseID + ", " + courseName + ", " + courseType + ", " + beginDate + ", " + endDate + ", " + tuitionFee + ", " + topicID;
    }
}
