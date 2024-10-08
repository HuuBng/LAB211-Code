package CoursesProgramManagement;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

import static CoursesProgramManagement.Tool.*;

public class CourseList extends ArrayList<Course> implements Serializable {

    public CourseList() {
    }

    private boolean isNotUniqueTopicID(TopicList topics, String id) {
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

    private String getCourseID() {
        String id;
        do {
            id = generateIDFromStr("course");
            if (!isNotUniqueID(id)) {
                System.out.println("ERROR: ID not found");
            }
        } while (!isNotUniqueID(id));

        return id;
    }

    private String getTopicID(TopicList tList) {
        String id;
        do {
            id = generateIDFromStr("topic");
            if (!isNotUniqueTopicID(tList, id)) {
                System.out.println("ERROR: ID not found");
            }
        } while (!isNotUniqueTopicID(tList, id));

        return id;
    }

    public void addCourse(TopicList topics) {
        String id;
        do {
            id = generateIDFromStr("course");
            if (isNotUniqueID(id)) {
                System.out.println("ERROR: ID not unique");
            }
        } while (isNotUniqueID(id));

        Course course = new Course(id);

        String name;
        do {
            name = readStr("Enter COURSE_NAME");
            if (name.isEmpty()) {
                System.out.println("ERROR: Name must not be empty");
            }
        } while (name.isEmpty());
        course.setCourseName(name);

        int type;
        do {
            System.out.println("Course type: ");
            type = int_menu("Online", "Offline");
            switch (type) {
                case 1:
                    course.setCourseType(Course.Type.ONLINE);
                    break;
                case 2:
                    course.setCourseType(Course.Type.OFFLINE);
                    break;
                default:
                    System.out.println("ERROR: Please enter a valid number");
                    break;
            }
        } while (type < 1 || type > 2);

        LocalDate begin = readDate("Enter BEGIN_DATE (yyyy-MM-dd)");
        course.setBeginDate(begin);

        LocalDate end = readDateAfter("Enter END_DATE (yyyy-MM-dd)", begin);
        course.setEndDate(end);

        int fee;
        do {
            fee = readInt("Enter TUITION_FEE");
            if (fee < 0) {
                System.out.println("ERROR: Please enter a valid TUITION_FEE");
            }
        } while (fee < 0);
        course.setTuitionFee(fee);

        String topicId;
        int failCount = 0;
        do {
            topicId = generateIDFromStr("topic");
            if (!isNotUniqueTopicID(topics, topicId)) {
                System.out.println("ERROR: Please enter a valid TOPIC number");
                failCount++;
            }
            if (failCount == 5) {
                System.out.println("ERROR: Please create a TOPIC first");
                return;
            }
        } while (!isNotUniqueTopicID(topics, topicId));
        course.setTopicID(topicId);

        int max;
        do {
            max = readInt("Enter MAX_LEARNER");
            if (max <= 0) {
                System.out.println("ERROR: Please enter a valid MAX_LEARNER");
            }
        } while (max <= 0);
        course.setMaxLearner(max);

        course.setCourseStatus(Course.Status.ACTIVE);

        this.add(course);
    }

    public void updateCourse(TopicList topics) {
        String id = getCourseID();

        int index = -1;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getCourseID().equals(id)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("ERROR: ID not found but pass check");
            return;
        }

        Course course = this.get(index);

        String name = readStr("Enter COURSE_NAME");
        if (!name.isEmpty()) {
            course.setCourseName(name);
        }

        System.out.println("Course status ?");
        int type = int_menu("Online", "Offline", "(blank)");
        if (type == 1) {
            course.setCourseType(Course.Type.ONLINE);
        } else if (type == 2) {
            course.setCourseType(Course.Type.OFFLINE);
        }

        boolean changed = false;
        String beginStr = readStr("Enter BEGIN_DATE (yyyy-MM-dd)");
        LocalDate begin = course.getBeginDate();
        if (!beginStr.isEmpty()) {
            begin = parseDate(beginStr);
            changed = true;
        }

        String endStr = readStr("Enter END_DATE (yyyy-MM-dd)");
        LocalDate end = course.getEndDate();
        if (!endStr.isEmpty()) {
            end = parseDate(endStr);
            changed = true;
        }

        if (changed) {
            if (isValidBeginEndDate(begin, end)) {
                course.setBeginDate(begin);
                course.setEndDate(end);
            } else {
                System.out.println("ERROR: BEGIN_DATE must be before END_DATE || END_DATE must be after BEGIN_DATE");
            }
        }

        String feeStr;
        do {
            feeStr = readStr("Enter TUITION_FEE");
            int fee = parseIntFromStr(feeStr);
            if (fee >= 0) {
                course.setTuitionFee(fee);
                break;
            } else if (!feeStr.isEmpty()) {
                System.out.println("ERROR: Please enter a valid TUITION_FEE");
            }
        } while (!feeStr.isEmpty());

        String topicId = null;
        String str = null;
        int failCount = 0;
        do {
            str = readStr("Enter TOPIC number");
            if (str.isEmpty()) {
                break;
            }

            int num = readIntFromStr(str);
            if (num == -1) {
                System.out.println("ERROR: Please enter a valid TOPIC number");
                failCount++;
                continue;
            }

            topicId = generateCode("T", 3, num);
            if (isNotUniqueTopicID(topics, topicId)) {
                course.setTopicID(topicId);
                break;
            } else {
                System.out.println("ERROR: Please enter a valid TOPIC number");
                failCount++;
            }

            if (failCount == 5) {
                System.out.println("ERROR: Please create a TOPIC first");
                break;
            }
        } while (true);

        String maxStr;
        do {
            maxStr = readStr("Enter MAX_LEARNER");
            int max = parseIntFromStr(maxStr);
            if (max > 0) {
                course.setMaxLearner(max);
                break;
            } else if (!maxStr.isEmpty()) {
                System.out.println("ERROR: Please enter a valid MAX_LEARNER");
            }
        } while (!maxStr.isEmpty());

        System.out.println("Course status ?");
        int status = int_menu("Active", "Inactive", "(blank)");
        if (status == 1) {
            course.setCourseStatus(Course.Status.ACTIVE);
        } else if (status == 2) {
            course.setCourseStatus(Course.Status.INACTIVE);
        }

        System.out.println("UPDATED: " + course);

        this.set(index, course);
    }

    public void deleteCourse() {
        String id = getCourseID();

        int index = -1;
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getCourseID().equals(id)) {
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
        switch (choice) {
            case 1:
                this.remove(index);
                System.out.println("DELETE success");
                break;
            case 2:
                System.out.println("DELETE canceled");
                break;
            default:
                System.out.println("ERROR: DELETE aborted");
                break;
        }
    }

    public void displayCourses(LearnerList learners) {
        if (this.isEmpty()) {
            System.out.println("Empty list");
            return;
        }

        System.out.println("\t === Courses ===");

        CourseList cList = this;

        cList.sort(Comparator.comparing(Course::getBeginDate));

        for (Course x : cList) {
            int incomeCount = 0;
            int pass = 0;
            int fail = 0;
            for (Learner y : learners) {
                if (y.getCourseID().equals(x.getCourseID())) {
                    incomeCount++;
                    if (y.getScore() >= 5) {
                        pass++;
                    } else {
                        fail++;
                    }
                }
            }

            int income = x.getTuitionFee() * incomeCount;
            System.out.println(x + ", pass: " + pass + ", fail: " + fail + ", incomes: " + income);
        }
    }

    public void searchByTopic(TopicList topics, LearnerList learners) {
        String id = getTopicID(topics);
        System.out.println();
        int foundCount = 0;
        for (Course x : this) {
            if (!x.getTopicID().equals(id)) {
                continue;
            }
            foundCount++;
            int pass = 0;
            int fail = 0;
            for (Learner y : learners) {
                if (y.getCourseID().equals(x.getCourseID())) {
                    if (y.getScore() >= 5) {
                        pass++;
                    } else {
                        fail++;
                    }
                }
            }

            System.out.println(x + ", pass: " + pass + ", fail: " + fail);
        }
        System.out.println("FOUND: " + foundCount);
    }

    public void searchPartialName(TopicList topics, LearnerList learners) {
        String name = readStr("Enter NAME to search");
        System.out.println();
        int foundCount = 0;
        for (Course x : this) {
            if (!x.getCourseName().toLowerCase().contains(name.toLowerCase())) {
                continue;
            }
            foundCount++;
            int pass = 0;
            int fail = 0;
            for (Learner y : learners) {
                if (y.getCourseID().equals(x.getCourseID())) {
                    if (y.getScore() >= 5) {
                        pass++;
                    } else {
                        fail++;
                    }
                }
            }

            System.out.println(x + ", pass: " + pass + ", fail: " + fail);
        }

        System.out.println("FOUND: " + foundCount);
    }

}
