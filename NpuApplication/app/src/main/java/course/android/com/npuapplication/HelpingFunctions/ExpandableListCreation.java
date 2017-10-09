package course.android.com.npuapplication.HelpingFunctions;

import android.app.Activity;
import android.widget.ExpandableListView;

import java.util.HashMap;
import java.util.List;

import course.android.com.npuapplication.R;

/**
 * Created by Bansari on 10/9/2017.
 */

public class ExpandableListCreation {

    public Activity activity;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> listHeader;
    private HashMap<String, List<String>> listHashMap;

    public void createExpandableListView(Activity activity, List<String> listHeader, HashMap<String, List<String>> listHashMap) {
        this.activity = activity;
        expandableListView = (ExpandableListView) this.activity.findViewById(R.id.expandview_course_details_id);
        expandableListAdapter = new ExpandableListAdapter(this.activity, listHeader, listHashMap);
        expandableListView.setAdapter(expandableListAdapter);
    }
}
