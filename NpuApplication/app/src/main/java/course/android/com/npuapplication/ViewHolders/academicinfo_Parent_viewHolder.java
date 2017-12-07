package course.android.com.npuapplication.ViewHolders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import course.android.com.npuapplication.*;


import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

/**
 * Created by pranali on 12/6/17.
 */

public class academicinfo_Parent_viewHolder extends ParentViewHolder {
public TextView txtAcdinfoParent,txtAcdinfoParentCredit;
    public ImageButton imgAcdinfoParent;
public  academicinfo_Parent_viewHolder(View itemview)
{

    super(itemview);
    txtAcdinfoParent =(TextView)itemview.findViewById(R.id.txtAcdinfoParentTitle);
    txtAcdinfoParentCredit =(TextView)itemview.findViewById(R.id.txtAcdinfoParentCreditTotal);

    //imgAcdinfoParent =(ImageButton) itemview.findViewById(R.id.exapnarrow);
}
}
