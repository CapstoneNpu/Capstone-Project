package course.android.com.npuapplication.RecyclerViewFunctions;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;
import java.util.UUID;

/**
 * Created by pranali on 12/6/17.
 */

public class Finalgrades_Parent implements ParentObject {

    private List<Object> finalgrades_ChildrenList;
    private UUID _id;

    private String finalgd_title,finalgd_gpa;

    public Finalgrades_Parent(String finalgd_title, String finalgd_gpa) {
        this.finalgd_title = finalgd_title;
        this.finalgd_gpa = finalgd_gpa;
        _id= UUID.randomUUID();
    }

    public UUID get_id() {
        return _id;
    }

    public void set_id(UUID _id) {
        this._id = _id;
    }

    public String getFinalgd_title() {
        return finalgd_title;
    }

    public void setFinalgd_title(String finalgd_title) {
        this.finalgd_title = finalgd_title;
    }

    public String getFinalgd_gpa() {
        return finalgd_gpa;
    }

    public void setFinalgd_gpa(String finalgd_gpa) {
        this.finalgd_gpa = finalgd_gpa;
    }

    @Override
    public List<Object> getChildObjectList() {
        return finalgrades_ChildrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        finalgrades_ChildrenList =list;
    }
}
