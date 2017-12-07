package course.android.com.npuapplication.ViewHolders;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;

import course.android.com.npuapplication.R;

/**
 * Created by pranali on 12/6/17.
 */

public class academicinfo_Child_viewHolder extends ChildViewHolder{
    public TextView txt_acdInfo_course , txt_acdInfo_grades;
    public  academicinfo_Child_viewHolder(View itemview)
    {
        super(itemview);
        txt_acdInfo_course =(TextView)itemview.findViewById(R.id.txt_acdinfochild_coursename);
        txt_acdInfo_grades =(TextView)itemview.findViewById(R.id.txt_acdinfochild_grades);


    }
}
