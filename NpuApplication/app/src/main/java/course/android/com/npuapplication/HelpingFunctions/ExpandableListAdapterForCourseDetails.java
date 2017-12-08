package course.android.com.npuapplication.HelpingFunctions;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import course.android.com.npuapplication.R;

/**
 * Created by Bansari on 10/9/2017.
 */

public class ExpandableListAdapterForCourseDetails extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listHeader;
    private HashMap<String, List<String>> listHashMap;
    private boolean isChildClickable;

    public ExpandableListAdapterForCourseDetails(Context context, List<String> listHeader, HashMap<String, List<String>> listHashMap, boolean isChildClickable) {
        this.context = context;
        this.listHeader = listHeader;
        this.listHashMap = listHashMap;
        this.isChildClickable = isChildClickable;
    }

    @Override
    public int getGroupCount() {
        return listHeader.size();
    }

    @Override
    public int getChildrenCount(int groupItem) {
        return listHashMap.get(listHeader.get(groupItem)).size();
    }

    @Override
    public Object getGroup(int groupItem) {
        return listHeader.get(groupItem);
    }

    @Override
    public Object getChild(int groupItem, int childItem) {
        return listHashMap.get(listHeader.get(groupItem)).get(childItem);
    }

    @Override
    public long getGroupId(int groupItem) {
        return groupItem;
    }

    @Override
    public long getChildId(int groupItem, int childItem) {
        return childItem;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupItem, boolean b, View view, ViewGroup viewGroup) {

        String headerTitle = String.valueOf(getGroup(groupItem));
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.expand_view_header_course_details_activity, null);
        }

        TextView txtViewListHeader = (TextView) view.findViewById(R.id.txtview_expand_view_header_course_details);
        txtViewListHeader.setTypeface(null, Typeface.BOLD);
        txtViewListHeader.setText(headerTitle);
        //view.setBackgroundColor(ContextCompat.getColor(context,R.color.expandableViewHeader));
        return view;
    }

    @Override
    public View getChildView(int groupItem, int childItem, boolean b, View view, ViewGroup viewGroup) {

        final String childText = String.valueOf(getChild(groupItem, childItem));
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.expand_view_sub_item_course_details_activity, null);
        }

        TextView txtViewListChild = (TextView) view.findViewById(R.id.txtview_expand_view_sub_item_course_details);
        txtViewListChild.setText(childText);
        //view.setBackgroundColor(ContextCompat.getColor(context,R.color.expandableViewChild));
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return this.isChildClickable;
    }
}
