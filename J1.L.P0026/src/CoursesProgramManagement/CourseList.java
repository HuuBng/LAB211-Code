package CoursesProgramManagement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import static CoursesProgramManagement.Tool.*;

public class CourseList extends ArrayList<Course> implements Serializable {

    public CourseList() {
    }

    private boolean isNotUniqueTopicID(ArrayList<Topic> topics, String id) {
        for (Topic x : topics) {
            if (x.getTopicID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    private boolean isNotUniqueID(String id) {
        for (Course x : this) {
            if (x.getCourseID().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void addCourse(ArrayList<Topic> topics) {
        String id;
        do {
            id = generateIDFromStr("course");
            if (isNotUniqueID(id)) {
                System.err.println("ID not unique");
            }
        } while (isNotUniqueID(id));

        Course course = new Course(id);

        String name;
        do {
            name = readStr("Enter TOPIC_NAME");
            if (name.isEmpty()) {
                System.err.println("Name must not be empty");
            }
        } while (name.isEmpty());
        course.setCourseName(name);

        int type;
        do {
            System.out.println("Course type: ");
            type = int_menu("Online", "Offline");
            if (type == 1) {
                course.setCourseType(Course.Type.ONLINE);
            } else if (type == 2) {
                course.setCourseType(Course.Type.OFFLINE);
            } else {
                System.err.println("Please enter a valid number");
            }
        } while (type < 1 || type > 2);

        Date begin = readDate("Enter BEGIN_DATE (yyyy-MM-dd)");
        course.setBeginDate(begin);

        Date end = readDateAfter("Enter END_DATE (yyyy-MM-dd)", begin);
        course.setEndDate(end);

        int fee;
        do {
            fee = readInt("Enter TUITION_FEE");
            if (fee < 0) {
                System.err.println("Please enter a valid TUITION_FEE");
            }
        } while (fee < 0);
        course.setTuitionFee(fee);

        String topicId;
        do {
            topicId = generateIDFromStr("topic");
            if (!isNotUniqueTopicID(topics, topicId)) {
                System.err.println("Please enter a valid TOPIC number");
            }
        } while (!isNotUniqueTopicID(topics, topicId));

        course.setTopicID(topicId);

        this.add(course);
    }

    public void updateCourse(ArrayList<Topic> topics) {
        String id;
        do {
            id = generateIDFromStr("course");
            if (!isNotUniqueID(id)) {
                System.err.println("ID not found");
            }
        } while (!isNotUniqueID(id));

        int index = -1;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getCourseID().equals(id)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.err.println("ID not found but pass check");
        }

        Course course = this.get(index);

        String name = readStr("Enter COURSE_NAME");
        if (!name.isEmpty()) {
            course.setCourseName(name);
        }

        System.out.println("Course type ?");
        int type = int_menu("Online", "Offline");
        if (type == 1) {
            course.setCourseType(Course.Type.ONLINE);
        } else if (type == 2) {
            course.setCourseType(Course.Type.OFFLINE);
        }

        String beginStr = readStr("Enter BEGIN_DATE (yyyy-MM-dd)");
        Date begin = course.getBeginDate();
        if (!beginStr.isEmpty()) {
            begin = parseDate(beginStr);
        }

        String endStr = readStr("Enter END_DATE (yyyy-MM-dd)");
        Date end = course.getEndDate();
        if (!endStr.isEmpty()) {
            end = parseDate(endStr);
        }

        assert begin != null;
        if (isValidBeginEndDate(begin, end)) {
            course.setBeginDate(begin);
            course.setEndDate(end);
        } else {
            System.err.println("BEGIN_DATE must be before END_DATE || END_DATE must be after BEGIN_DATE");
        }

        String feeStr;
        do {
            feeStr = readStr("Enter TUITION_FEE");
            int fee = readIntFromStr(feeStr);
            if (fee >= 0) {
                course.setTuitionFee(fee);
                break;
            } else {
                System.err.println("Please enter a valid TUITION_FEE");
            }
        } while (!feeStr.isEmpty());

        String topicId;
        do {
            topicId = generateIDFromStr("topic");
            if (isNotUniqueTopicID(topics, topicId)) {
                course.setTopicID(topicId);
                break;
            } else {
                System.err.println("Please enter a valid TOPIC number");
            }
        } while (!topicId.isEmpty());

        System.out.println("OLD: " + this.get(index));
        System.out.println();
        System.out.println("NEW: " + course);

        this.set(index, course);
    }

    public

}
