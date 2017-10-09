package course.android.com.npuapplication.Domain;

/**
 * Created by Bansari on 10/9/2017.
 */

public class Course {
    private String CourseId;
    private String CourseName;
    private String Credit;

    public Course(String courseId, String courseName, String credit) {
        CourseId = courseId;
        CourseName = courseName;
        Credit = credit;
    }

    public String getCourseId() {
        return CourseId;
    }

    public void setCourseId(String courseId) {
        CourseId = courseId;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getCredit() {
        return Credit;
    }

    public void setCredit(String credit) {
        Credit = credit;
    }
}
