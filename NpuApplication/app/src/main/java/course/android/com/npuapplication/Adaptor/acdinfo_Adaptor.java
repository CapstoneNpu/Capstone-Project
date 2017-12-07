package course.android.com.npuapplication.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

import course.android.com.npuapplication.R;
import course.android.com.npuapplication.ViewHolders.*;
import course.android.com.npuapplication.RecyclerViewFunctions.*;
/**
 * Created by pranali on 12/6/17.
 */

public class acdinfo_Adaptor extends ExpandableRecyclerAdapter<academicinfo_Parent_viewHolder,academicinfo_Child_viewHolder>{
    LayoutInflater inflater;

    public acdinfo_Adaptor(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        inflater = LayoutInflater.from(context);
    }

    @Override

    public academicinfo_Parent_viewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.academicinfo_parent,viewGroup,false);
        return new academicinfo_Parent_viewHolder(view);
    }

    @Override
    public academicinfo_Child_viewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.academicinfo_child,viewGroup,false);
        return new academicinfo_Child_viewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(academicinfo_Parent_viewHolder academicinfo_parent_viewHolder, int i, Object o) {
        acdemicinfo_Parent acdemicinfoParent =(acdemicinfo_Parent)o;
        academicinfo_parent_viewHolder.txtAcdinfoParent.setText(acdemicinfoParent.getAcdemicinfo_title());
        academicinfo_parent_viewHolder.txtAcdinfoParentCredit.setText(acdemicinfoParent.getAcdemicinfo_credit());
    }

    @Override
    public void onBindChildViewHolder(academicinfo_Child_viewHolder academicinfo_child_viewHolder, int i, Object o) {

       academicinfo_Child academicinfoChild = (academicinfo_Child)o;
        academicinfo_child_viewHolder.txt_acdInfo_course.setText(academicinfoChild.getAcdinfoChild_coursename());
    academicinfo_child_viewHolder.txt_acdInfo_grades.setText(academicinfoChild.getAcdinfoChild_grades());
    }
}
