package course.android.com.npuapplication.ViewHolders;

import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import course.android.com.npuapplication.R;

/**
 * Created by pranali on 12/6/17.
 */

public class Finalgrd_Parent_viewHolder extends ParentViewHolder {
    public TextView txtFinalgrdSem,txtFinalgrdGPA;
    public Finalgrd_Parent_viewHolder(View itemView) {
        super(itemView);
        txtFinalgrdSem =(TextView)itemView.findViewById(R.id.txtFinalGradesParentTitle);
        txtFinalgrdGPA =(TextView)itemView.findViewById(R.id.txt_acdinfochild_grades);

    }
}
