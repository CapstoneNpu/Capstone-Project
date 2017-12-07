package course.android.com.npuapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ExpandableListView;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import course.android.com.npuapplication.Adaptor.*;
import course.android.com.npuapplication.HelpingFunctions.ExpandableListCreation;
import course.android.com.npuapplication.RecyclerViewFunctions.*;
import course.android.com.npuapplication.RecyclerViewFunctions.academicinfo_ParentCreator;
public class AcademicInfoActivity extends AppCompatActivity {
//    private ExpandableListView expandableListView;
//    private ExpandableListCreation expandableListCreationObj;
//    HashMap<String, List<String>> mapDataFromDatabase;
//
//    List<String> listHeader;
//    List<String> fodcrs;

    RecyclerView acdemicRecyclerview;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ((acdinfo_Adaptor) acdemicRecyclerview.getAdapter()).onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_info);

        acdemicRecyclerview = (RecyclerView) findViewById(R.id.academicinfo_recycler);
        acdemicRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        acdinfo_Adaptor myadaptor = new acdinfo_Adaptor(this, initData());
        myadaptor.setParentClickableViewAnimationDefaultDuration();
        myadaptor.setParentAndIconExpandOnClick(true);
        acdemicRecyclerview.setAdapter(myadaptor);

//        listHeader = new ArrayList<String>(Arrays.asList("Foundation Requirements", "Software Engineering Course Requirements", "Electives","Capstone Course"));
//         fodcrs=new ArrayList<String>();//(Arrays.asList("Foundation Requirements", "Software Engineering Course Requirements", "Electives","Capstone Course"));
//      fodcrs.add("CS501(A) -Advanced Structured Programming and Algorithms");
//        fodcrs.add("CS457(B) - Data Modeling and Implementation Techniques");
//        fodcrs.add("CS480(D) - Java and Internet Applications");
//        List<String> sscs =new ArrayList<String>();
//        sscs.add("CS532(A) - Advanced Internet Programming and Design");
//        sscs.add("CS556(A) - Mobile Applications on iPhone Platform");
//        sscs.add("CS571 - Cloud Management- Hadoop Administration");
//        sscs.add("CS557(A) - Web Front-end Programming for Mobile Devices");
//        List<String> cap = new ArrayList<String>(Arrays.asList("CS595 - Computer Science Capstone Course"));
//        mapDataFromDatabase = new HashMap<String, List<String>>();
//        mapDataFromDatabase.put("Foundation Requirements",fodcrs);
//        mapDataFromDatabase.put("Software Engineering Course Requirements",sscs);
//        mapDataFromDatabase.put("Electives",fodcrs);
//        mapDataFromDatabase.put("Capstone Course",cap);
//        expandableListCreationObj = new ExpandableListCreation();
//        expandableListView = expandableListCreationObj.createExpandableListView(
//                AcademicInfoActivity.this,
//                listHeader,
//                mapDataFromDatabase,
//                R.id.expandview_academicinfo,
//                true
//        );
//        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView elv, View view, int g, int c, long id) {
//                return true;
//            }
//});
    }

    private List<ParentObject> initData() {
        academicinfo_ParentCreator academicinfo_parentCR = academicinfo_ParentCreator.get(this);
        List<acdemicinfo_Parent> titles = academicinfo_parentCR.getAll();
        List<ParentObject> parentObject = new ArrayList<>();

        for (acdemicinfo_Parent title : titles) {

                List<Object> childList = getChildListForParent(title.getAcdemicinfo_title());
            title.setChildObjectList(childList);
            parentObject.add(title);
            }
        return parentObject;

    }
    public List<Object> getChildListForParent(String titleParent) {
        List<Object> childList = new ArrayList<>();
        if (titleParent == "Foundation Requirements") {

            childList.add(new academicinfo_Child("CS501(A) -Advanced Structured Programming and Algorithms", "A+"));
            childList.add(new academicinfo_Child("CS457(B) - Data Modeling and Implementation Techniques", "A"));
            childList.add(new academicinfo_Child("CS480(D) - Java and Internet Applications", "A"));

        }

        if (titleParent == "Software Engineering Course Requirements") {

            childList.add(new academicinfo_Child("CS532(A) - Advanced Internet Programming and Design", "A+"));
            childList.add(new academicinfo_Child("CS556(A) - Mobile Applications on iPhone Platform", "A+"));
            childList.add(new academicinfo_Child("CS571 - Cloud Management- Hadoop Administration", "A"));
            childList.add(new academicinfo_Child("CS557(A) - Web Front-end Programming for Mobile Devices", "A-"));
//            childList.add(new academicinfo_Child("CS501(A) -Advanced Structured Programming and Algorithms", "A+"));
//            childList.add(new academicinfo_Child("CS457(B) - Data Modeling and Implementation Techniques", "A"));
//            childList.add(new academicinfo_Child("CS480(D) - Java and Internet Applications", "A"));

        }
        return childList;
    }
}