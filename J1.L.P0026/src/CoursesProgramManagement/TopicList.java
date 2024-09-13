package CoursesProgramManagement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

import static CoursesProgramManagement.Tool.*;

public class TopicList extends ArrayList<Topic> implements Serializable {

    public TopicList() {
    }

    public boolean isNotUniqueID(String id) {
        for (Topic x : this) {
            if (x.getTopicID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void addTopic() {
        String id;
        do {
            id = generateIDFromStr("topic");
            if (isNotUniqueID(id)) {
                System.err.println("ID not unique");
            }
        } while (isNotUniqueID(id));

        Topic topic = new Topic(id);

        String name;
        do {
            name = readStr("Enter TOPIC_NAME");
            if (name.isEmpty()) {
                System.err.println("Name must not be empty");
            }
        } while (name.isEmpty());
        topic.setTopicName(name);

        int type;
        do {
            System.out.println("Topic type: ");
            type = int_menu("Long", "Short");
            if (type == 1) {
                topic.setTopicType(Topic.Type.LONG);
            } else if (type == 2) {
                topic.setTopicType(Topic.Type.SHORT);
            } else {
                System.err.println("Please enter a valid number");
            }
        } while (type < 1 || type > 2);

        int duration;
        do {
            duration = readInt("Enter DURATION in week");
            if (duration <= 0 || duration > 52) {
                System.err.println("Please enter a valid number");
            }
        } while (duration <= 0 || duration > 52);

        this.add(topic);
    }

    public void updateTopic() {
        String id;
        do {
            id = generateIDFromStr("topic");
            if (!isNotUniqueID(id)) {
                System.err.println("ID not found");
            }
        } while (!isNotUniqueID(id));

        int index = -1;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getTopicID().equals(id)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.err.println("ID not found but pass check");
            return;
        }

        Topic topic = this.get(index);
        String name = readStr("Enter TOPIC_NAME");
        if (!name.isEmpty()) {
            topic.setTopicName(name);
        }

        System.out.println("Topic type ?");
        int type = int_menu("Long", "Short", "(blank)");
        if (type == 1) {
            topic.setTopicType(Topic.Type.LONG);
        } else if (type == 2) {
            topic.setTopicType(Topic.Type.SHORT);
        }

        String duraStr;
        do {
            duraStr = readStr("Enter DURATION in week");
            int duration = readIntFromStr(duraStr);
            if (duration > 0 && duration <= 52) {
                topic.setTopicDurationInWeek(duration);
                break;
            } else {
                System.err.println("Please enter a valid number");
            }
        } while (!duraStr.isEmpty());

        System.out.println("OLD: " + this.get(index));
        System.out.println();
        System.out.println("NEW" + topic);

        this.set(index, topic);

    }

    public void deleteTopic() {
        String id;
        do {
            id = generateIDFromStr("topic");
            if (!isNotUniqueID(id)) {
                System.err.println("ID not found");
            }
        } while (!isNotUniqueID(id));

        int index = -1;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getTopicID().equals(id)) {
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

    public void displayALL() {
        ArrayList<Topic> tList = this;

        tList.sort(Comparator.comparing(Topic::getTopicName));

        tList.forEach(System.out::println);
    }

}
