package course.android.com.npuapplication.RecyclerViewFunctions;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by pranali on 12/6/17.
 */

public class acdemicinfo_Parent implements ParentObject {
private List<Object> acdemicinfo_ChildrenList;
    private UUID _id;

    private String acdemicinfo_title,acdemicinfo_credit;

    public acdemicinfo_Parent(String acdemicinfo_title,String acdemicinfo_credit) {
        this.acdemicinfo_title = acdemicinfo_title;
        this.acdemicinfo_credit = acdemicinfo_credit;
        _id= UUID.randomUUID();
    }

    public UUID get_id() {
        return _id;
    }

    public void set_id(UUID _id) {
        this._id = _id;
    }

    public String getAcdemicinfo_title() {
        return acdemicinfo_title;
    }

    public void setAcdemicinfo_title(String acdemicinfo_title) {
        this.acdemicinfo_title = acdemicinfo_title;
    }
    public String getAcdemicinfo_credit() {
        return acdemicinfo_credit;
    }

    public void setAcdemicinfo_credit(String acdemicinfo_credit) {
        this.acdemicinfo_credit = acdemicinfo_credit;
    }

    @Override
    public List<Object> getChildObjectList() {
        return acdemicinfo_ChildrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        acdemicinfo_ChildrenList = list;
    }
}
