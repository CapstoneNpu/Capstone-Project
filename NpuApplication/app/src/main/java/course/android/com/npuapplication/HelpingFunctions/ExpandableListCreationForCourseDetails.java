package course.android.com.npuapplication.HelpingFunctions;

import android.app.Activity;
import android.widget.ExpandableListView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Bansari on 10/9/2017.
 */

public class ExpandableListCreationForCourseDetails {

    public Activity activity;
    private ExpandableListView expandableListView;
    private ExpandableListAdapterForCourseDetails expandableListAdapter;
    private List<String> listHeader;
    private HashMap<String, List<String>> listHashMap;

    public ExpandableListView createExpandableListView(
            Activity activity,
            List<String> listHeader,
            HashMap<String, List<String>> listHashMap,
            int id,
            boolean isChildClickable) {
        this.activity = activity;
        expandableListView = (ExpandableListView) this.activity.findViewById(id);
        expandableListAdapter = new ExpandableListAdapterForCourseDetails(this.activity, listHeader, listHashMap, isChildClickable);
        expandableListView.setAdapter(expandableListAdapter);
        return expandableListView;
    }
}
