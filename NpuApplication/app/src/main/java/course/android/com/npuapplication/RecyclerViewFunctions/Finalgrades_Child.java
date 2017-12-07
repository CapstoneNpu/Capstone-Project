package course.android.com.npuapplication.RecyclerViewFunctions;

/**
 * Created by pranali on 12/6/17.
 */

public class Finalgrades_Child {
    public String Finalgd_courseNo,Finalgd_courseName,Finalgd_GPA;

    public Finalgrades_Child(String finalgd_courseNo, String finalgd_courseName, String finalgd_GPA) {
        Finalgd_courseNo = finalgd_courseNo;
        Finalgd_courseName = finalgd_courseName;
        Finalgd_GPA = finalgd_GPA;
    }

    public String getFinalgd_courseNo() {
        return Finalgd_courseNo;
    }

    public void setFinalgd_courseNo(String finalgd_courseNo) {
        Finalgd_courseNo = finalgd_courseNo;
    }

    public String getFinalgd_courseName() {
        return Finalgd_courseName;
    }

    public void setFinalgd_courseName(String finalgd_courseName) {
        Finalgd_courseName = finalgd_courseName;
    }

    public String getFinalgd_GPA() {
        return Finalgd_GPA;
    }

    public void setFinalgd_GPA(String finalgd_GPA) {
        Finalgd_GPA = finalgd_GPA;
    }
}
