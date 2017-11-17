package course.android.com.npuapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import course.android.com.npuapplication.HelpingFunctions.ExpandableListCreation;

public class AcademicInfoActivity extends AppCompatActivity {
    private ExpandableListView expandableListView;
    private ExpandableListCreation expandableListCreationObj;
    HashMap<String, List<String>> mapDataFromDatabase;

    List<String> listHeader;
    List<String> fodcrs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_info);
        listHeader = new ArrayList<String>(Arrays.asList("Foundation Requirements", "Software Engineering Course Requirements", "Electives","Capstone Course"));
         fodcrs=new ArrayList<String>();//(Arrays.asList("Foundation Requirements", "Software Engineering Course Requirements", "Electives","Capstone Course"));
      fodcrs.add("CS501(A) -Advanced Structured Programming and Algorithms");
        fodcrs.add("CS457(B) - Data Modeling and Implementation Techniques");
        fodcrs.add("CS480(D) - Java and Internet Applications");
        List<String> sscs =new ArrayList<String>();
        sscs.add("CS532(A) - Advanced Internet Programming and Design");
        sscs.add("CS556(A) - Mobile Applications on iPhone Platform");
        sscs.add("CS571 - Cloud Management- Hadoop Administration");
        sscs.add("CS557(A) - Web Front-end Programming for Mobile Devices");
        List<String> cap = new ArrayList<String>(Arrays.asList("CS595 - Computer Science Capstone Course"));
        mapDataFromDatabase = new HashMap<String, List<String>>();
        mapDataFromDatabase.put("Foundation Requirements",fodcrs);
        mapDataFromDatabase.put("Software Engineering Course Requirements",sscs);
        mapDataFromDatabase.put("Electives",fodcrs);
        mapDataFromDatabase.put("Capstone Course",cap);
        expandableListCreationObj = new ExpandableListCreation();
        expandableListView = expandableListCreationObj.createExpandableListView(
                AcademicInfoActivity.this,
                listHeader,
                mapDataFromDatabase,
                R.id.expandview_academicinfo,
                true
        );
//        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView elv, View view, int g, int c, long id) {
//                return true;
//            }
//});
    }
}