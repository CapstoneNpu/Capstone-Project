package course.android.com.npuapplication.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import course.android.com.npuapplication.ViewHolders.Finalgrd_Child_viewHolder;
import course.android.com.npuapplication.ViewHolders.Finalgrd_Parent_viewHolder;
import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

import course.android.com.npuapplication.R;
import course.android.com.npuapplication.ViewHolders.*;
import course.android.com.npuapplication.RecyclerViewFunctions.*;
/**
 * Created by pranali on 12/6/17.
 */

public class FinalGrade_Adaptor extends ExpandableRecyclerAdapter<Finalgrd_Parent_viewHolder,Finalgrd_Child_viewHolder> {
    LayoutInflater inflater;

    public FinalGrade_Adaptor(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        inflater = LayoutInflater.from(context);
    }
    @Override
    public Finalgrd_Parent_viewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.finalgrades_parent,viewGroup,false);
        return new Finalgrd_Parent_viewHolder(view);
    }

    @Override
    public Finalgrd_Child_viewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.finalgrades_child,viewGroup,false);
        return new Finalgrd_Child_viewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(Finalgrd_Parent_viewHolder finalgrd_parent_viewHolder, int i, Object o) {
        Finalgrades_Parent finalgradesParent =(Finalgrades_Parent)o;
        finalgrd_parent_viewHolder.txtFinalgrdSem.setText(finalgradesParent.getFinalgd_title());
        finalgrd_parent_viewHolder.txtFinalgrdGPA.setText(finalgradesParent.getFinalgd_gpa());


    }

    @Override
    public void onBindChildViewHolder(Finalgrd_Child_viewHolder finalgrd_child_viewHolder, int i, Object o) {
        Finalgrades_Child finalgradesChild = (Finalgrades_Child)o;
        finalgrd_child_viewHolder.txtFGcourseNo.setText(finalgradesChild.getFinalgd_courseNo());
        finalgrd_child_viewHolder.txtFGcourseName.setText(finalgradesChild.getFinalgd_courseName());
        finalgrd_child_viewHolder.txtFGcourseGPA.setText(finalgradesChild.getFinalgd_GPA());

    }
}
