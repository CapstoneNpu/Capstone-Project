package course.android.com.npuapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.ArrayList;
import java.util.List;

import course.android.com.npuapplication.Adaptor.acdinfo_Adaptor;
import course.android.com.npuapplication.RecyclerViewFunctions.academicinfo_Child;
import course.android.com.npuapplication.RecyclerViewFunctions.academicinfo_ParentCreator;
import course.android.com.npuapplication.RecyclerViewFunctions.acdemicinfo_Parent;
public class AcademicInfoActivity extends AppCompatActivity {
//    private ExpandableListView expandableListView;
//    private ExpandableListCreation expandableListCreationObj;
//    HashMap<String, List<String>> mapDataFromDatabase;
//
//    List<String> listHeader;
//    List<String> fodcrs;

    RecyclerView acdemicRecyclerview;
    private Session session;
    TextView txttotal;
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
        txttotal = (TextView)findViewById(R.id.txt_total);
        txttotal.setText("Degree Requirements (27.0/36.0)");

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    //Home button(Action bar) onClick event handler
    public void btnGoToHome_onClick(MenuItem item) {
        goToAnotherActivity(this, Home_2Activity.class);
    }
    //Home button(Action bar) onClick event handler
    public void btnLogOut_onClick(MenuItem item) {
        session.setusename("");
        goToAnotherActivity(this, Home_2Activity.class);
    }
    //Navigate to another activity
    public void goToAnotherActivity(Context currentActivity, Class targetActivity) {
        Intent intentObj = new Intent(currentActivity, targetActivity);
        startActivity(intentObj);
    }
    public List<Object> getChildListForParent(String titleParent) {
        List<Object> childList = new ArrayList<>();
        if (titleParent == "Foundation Requirements") {

            childList.add(new academicinfo_Child("CS501(A) -Advanced Structured Programming and Algorithms", "A+"));
            childList.add(new academicinfo_Child("CS457(B) - Data Modeling and Implementation Techniques", "A"));
            childList.add(new academicinfo_Child("CS480(D) - Java and Internet Applications", "A"));

        }

        if (titleParent == "Software Engineering Course Requirements") {

            childList.add(new academicinfo_Child("CS571 - Cloud Management- Hadoop Administration", "A+"));
            childList.add(new academicinfo_Child("CS556(A) - Mobile Applications on iPhone Platform", "A+"));
            childList.add(new academicinfo_Child("CS5557 - Web Front-end Programming for Mobile Devices", "IP"));
            childList.add(new academicinfo_Child("CS548(B) - Web Services Techniques and REST Technologies", "A+"));
           // childList.add(new academicinfo_Child("CS571 - Cloud Management- Hadoop Administration", "A"));
            //childList.add(new academicinfo_Child("CS557(A) - Web Front-end Programming for Mobile Devices", "A-"));
            //childList.add(new academicinfo_Child("CS501(A) -Advanced Structured Programming and Algorithms", "A+"));
//            childList.add(new academicinfo_Child("CS457(B) - Data Modeling and Implementation Techniques", "A"));
//            childList.add(new academicinfo_Child("CS480(D) - Java and Internet Applications", "A"));

        }
        if (titleParent == "Electives") {
            childList.add(new academicinfo_Child("P450 - Career Development", "A+"));
            childList.add(new academicinfo_Child("CCS551 - Mobile Computing for Android Mobile Devices", "A+"));
            childList.add(new academicinfo_Child("CS532(A) - Advanced Internet Programming and Design", "A"));
        }
        if (titleParent == "Capstone Course") {
            childList.add(new academicinfo_Child("CS595 - Computer Science Capstone Course ", "IP"));
        }
        return childList;
    }
}