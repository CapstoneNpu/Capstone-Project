package course.android.com.npuapplication.Database;

import com.google.firebase.database.DataSnapshot;

import java.util.Calendar;

/**
 * Created by Bansari on 10/8/2017.
 */

public class UserData {

    private DataSnapshot allUserInfo;
    private DataSnapshot userCourseInfo;
    private DataSnapshot loginUserInfo;
    private DataSnapshot currentSemCourseInfo;

    public DataSnapshot getLoginUserInfo() {
        return loginUserInfo;
    }

    public void setLoginUserInfo(DataSnapshot loginUserInfo) {
        this.loginUserInfo = loginUserInfo;
    }

    public DataSnapshot getCurrentSemCourseInfo() {
        return currentSemCourseInfo;
    }

    public void setCurrentSemCourseInfo(DataSnapshot currentSemCourseInfo) {
        this.currentSemCourseInfo = currentSemCourseInfo;
    }

    public DataSnapshot getAllUserInfo() {

        return allUserInfo;
    }

    public void setAllUserInfo(DataSnapshot allUserInfo) {
        this.allUserInfo = allUserInfo;
    }

    public DataSnapshot getUserCourseInfo() {
        return userCourseInfo;
    }

    public void setUserCourseInfo(DataSnapshot userCourseInfo) {
        this.userCourseInfo = userCourseInfo;
    }

    //return all users' information (entire UserList from firebase)
    public DataSnapshot fetchAllUserInfo(DataSnapshot dataSnapshot) {
        allUserInfo = dataSnapshot.child("UserList");
        return allUserInfo;
    }

    //return login user details
    public DataSnapshot fetchLoginUserInfo(String userName) {
        loginUserInfo = allUserInfo.child(userName);
        return loginUserInfo;
    }

    //return login user all the course information
    public DataSnapshot fetchUserCourseInfo(String userName) {
        userCourseInfo = fetchLoginUserInfo(userName).child("Course Information");
        return userCourseInfo;
    }

    //return login user current semester details
    public DataSnapshot fetchCurrentSemesterCourseInfo(String userName) {
        Calendar c = Calendar.getInstance();
        int currentYear = c.get(Calendar.YEAR);
        int currentMonth = c.get(Calendar.MONTH);
        String currentSemester = "";

        if (currentMonth < 4) {
            currentSemester = "Spring";
        }
        else if(currentMonth < 8){
            currentSemester = "Summer";
        }
        else{
            currentSemester = "Fall";
        }
        currentSemCourseInfo = fetchUserCourseInfo(userName).child(String.valueOf(currentYear)).child(currentSemester);
        return currentSemCourseInfo;
    }
}
