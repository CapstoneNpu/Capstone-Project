package course.android.com.npuapplication.ViewHolders;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;

import course.android.com.npuapplication.R;

/**
 * Created by pranali on 12/6/17.
 */

public class Finalgrd_Child_viewHolder extends ChildViewHolder{
    public TextView txtFGcourseNo,txtFGcourseName,txtFGcourseGPA;
    public Finalgrd_Child_viewHolder(View itemView) {
        super(itemView);
        txtFGcourseNo =(TextView)itemView.findViewById(R.id.txt_FinalGrd_CourseNo);
        txtFGcourseName =(TextView)itemView.findViewById(R.id.txt_FinalGrd_CoursName);
        txtFGcourseGPA =(TextView)itemView.findViewById(R.id.txt_FinalGrd_GPA);

    }
}
