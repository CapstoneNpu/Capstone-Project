package course.android.com.npuapplication.RecyclerViewFunctions;

/**
 * Created by pranali on 12/6/17.
 */

public class academicinfo_Child {

    public String acdinfoChild_coursename;

    public String acdinfoChild_grades;

    public academicinfo_Child(String acdinfoChild_coursename, String acdinfoChild_grades) {
        this.acdinfoChild_coursename = acdinfoChild_coursename;
        this.acdinfoChild_grades = acdinfoChild_grades;
    }

    public String getAcdinfoChild_coursename() {
        return acdinfoChild_coursename;
    }

    public void setAcdinfoChild_coursename(String acdinfoChild_coursename) {
        this.acdinfoChild_coursename = acdinfoChild_coursename;
    }

    public String getAcdinfoChild_grades() {
        return acdinfoChild_grades;
    }

    public void setAcdinfoChild_grades(String acdinfoChild_grades) {
        this.acdinfoChild_grades = acdinfoChild_grades;
    }
}
