package course.android.com.npuapplication.HelpingFunctions;

import android.app.Activity;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

    public void createExpandableListView(Activity activity, String[] listHeaderArg, HashMap<String, List<String>> listChildArg) {
        this.activity = activity;
        expandableListView = (ExpandableListView) this.activity.findViewById(R.id.expandview_course_details_id);
        initData(listHeaderArg, listChildArg);
        expandableListAdapter = new ExpandableListAdapter(this.activity, listHeader, listHashMap);
        expandableListView.setAdapter(expandableListAdapter);
    }

    private void initData(String[] listHeaderArg, HashMap<String, List<String>> listChildArg) {
        listHeader = new ArrayList<>();
        listHashMap = new HashMap<>();


        for (int i = 0; i < listHeaderArg.length; i++) {
            listHeader.add(listHeaderArg[i]);
            List<String> listSubItem = new ArrayList<>();
            Iterator<String> stringIterator = listChildArg.get(listHeaderArg[i]).listIterator();

            while(stringIterator.hasNext()){
                listSubItem.add(stringIterator.next());
            }
            listHashMap.put(listHeaderArg[i], listSubItem);
        }
    }

    public ExpandableListView getExpandableListView() {
        return expandableListView;
    }

    public void setExpandableListView(ExpandableListView expandableListView) {
        this.expandableListView = expandableListView;
    }

    public ExpandableListAdapter getExpandableListAdapter() {
        return expandableListAdapter;
    }

    public void setExpandableListAdapter(ExpandableListAdapter expandableListAdapter) {
        this.expandableListAdapter = expandableListAdapter;
    }

    public List<String> getListHeader() {
        return listHeader;
    }

    public void setListHeader(List<String> listHeader) {
        this.listHeader = listHeader;
    }

    public HashMap<String, List<String>> getListHashMap() {
        return listHashMap;
    }

    public void setListHashMap(HashMap<String, List<String>> listHashMap) {
        this.listHashMap = listHashMap;
    }
}
