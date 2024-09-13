package CoursesProgramManagement;

import java.io.Serializable;

public class Topic implements Serializable {

    public enum topicType {
        ONLINE,
        OFFLINE
    }

    private final String topicID;
    private String topicName;
    private topicType topicType;
    private int topicDurationInWeek;

    public Topic(String topicID) {
        this.topicID = topicID;
    }

    public Topic(String topicID, String topicName, topicType topicType, int topicDurationInWeek) {
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

    public Topic.topicType getTopicType() {
        return topicType;
    }

    public void setTopicType(Topic.topicType topicType) {
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
