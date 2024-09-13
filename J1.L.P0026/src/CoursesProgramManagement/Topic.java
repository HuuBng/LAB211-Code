package CoursesProgramManagement;

import java.io.Serializable;

public class Topic implements Serializable {

    public enum Type {
        LONG,
        SHORT
    }

    private final String topicID;
    private String topicName;
    private Type topicType;
    private int topicDurationInWeek;

    public Topic(String topicID) {
        this.topicID = topicID;
    }

    public Topic(String topicID, String topicName, Type topicType, int topicDurationInWeek) {
        this.topicID = topicID;
        this.topicName = topicName;
        this.topicType = topicType;
        this.topicDurationInWeek = topicDurationInWeek;
    }

    public String getTopicID() {
        return topicID;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public Type getTopicType() {
        return topicType;
    }

    public void setTopicType(Type topicType) {
        this.topicType = topicType;
    }

    public int getTopicDurationInWeek() {
        return topicDurationInWeek;
    }

    public void setTopicDurationInWeek(int topicDurationInWeek) {
        this.topicDurationInWeek = topicDurationInWeek;
    }

    @Override
    public String toString() {
        return topicID + ", " + topicName + ", " + topicType + ", " + topicDurationInWeek;
    }
}
