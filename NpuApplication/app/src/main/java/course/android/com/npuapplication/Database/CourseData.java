package course.android.com.npuapplication.Database;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by Bansari on 10/9/2017.
 */

public class CourseData {
    private DataSnapshot allCourseInfo;
    private DataSnapshot selectedCourseInfo;

    public DataSnapshot getAllCourseInfo() {
        return allCourseInfo;
    }

    public void setAllCourseInfo(DataSnapshot allCourseInfo) {
        this.allCourseInfo = allCourseInfo;
    }

    public DataSnapshot getSelectedCourseInfo() {
        return selectedCourseInfo;
    }

    public void setSelectedCourseInfo(DataSnapshot selectedCourseInfo) {
        this.selectedCourseInfo = selectedCourseInfo;
    }

    //return all courses info (entire course list)
    public DataSnapshot fetchAllCourseData(DataSnapshot dataSnapshot){
        allCourseInfo = dataSnapshot.child("Course");
        return allCourseInfo;
    }

    //return specific course info
    public DataSnapshot fetchSelectedCourseInfo(String courseId){
        selectedCourseInfo = allCourseInfo.child(courseId);
        return selectedCourseInfo;
    }

    //return credits of specific course (courseId as an argument)
    public String fetchCourseCredits(String courseId){
        return String.valueOf(fetchSelectedCourseInfo(courseId).child("Credits").getValue());
    }

    //return Credits of specific course (if we already have data of specific course, then no need to specify courseId in argument)
    public String fetchCourseCredits(){
        return String.valueOf(selectedCourseInfo.child("Credits").getValue());
    }

    //return course name of specific course (courseId as an argument)
    public String fetchCourseName(String courseId){
        return String.valueOf(fetchSelectedCourseInfo(courseId).child("Name").getValue());
    }

    //return course name of specific course (if we already have data of specific course, then no need to specify courseId in argument)
    public String fetchCourseName(){
        return String.valueOf(selectedCourseInfo.child("Name").getValue());
    }
}
