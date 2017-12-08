package course.android.com.npuapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.ArrayList;
import java.util.List;

import course.android.com.npuapplication.Adaptor.FinalGrade_Adaptor;
import course.android.com.npuapplication.Adaptor.acdinfo_Adaptor;
import course.android.com.npuapplication.RecyclerViewFunctions.Finalgrades_Child;
import course.android.com.npuapplication.RecyclerViewFunctions.Finalgrades_Parent;
import course.android.com.npuapplication.RecyclerViewFunctions.Finalgrades_ParentCreator;
import course.android.com.npuapplication.RecyclerViewFunctions.academicinfo_Child;
import course.android.com.npuapplication.RecyclerViewFunctions.academicinfo_ParentCreator;
import course.android.com.npuapplication.RecyclerViewFunctions.acdemicinfo_Parent;


public class FinalGradesActvity extends AppCompatActivity {
    RecyclerView finalGDRecyclerview;
    private Session session;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        ((FinalGrade_Adaptor) finalGDRecyclerview.getAdapter()).onSaveInstanceState(outState);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_grades);
        finalGDRecyclerview = (RecyclerView) findViewById(R.id.finalGrades_recycler);
        finalGDRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        FinalGrade_Adaptor myadaptor = new FinalGrade_Adaptor(this, initData());
        myadaptor.setParentClickableViewAnimationDefaultDuration();
        myadaptor.setParentAndIconExpandOnClick(true);
        finalGDRecyclerview.setAdapter(myadaptor);
    }

    private List<ParentObject> initData() {
        Finalgrades_ParentCreator finalGrdParentCR = Finalgrades_ParentCreator.get(this);
        List<Finalgrades_Parent> titles = finalGrdParentCR.getAll();
        List<ParentObject> parentObject = new ArrayList<>();

        for (Finalgrades_Parent title : titles) {

            List<Object> childList = getChildListForParent(title.getFinalgd_title());
            title.setChildObjectList(childList);
            parentObject.add(title);
        }
        return parentObject;

    }
    public List<Object> getChildListForParent(String titleParent) {
        List<Object> childList = new ArrayList<>();
        if (titleParent == "2016 Fall") {

            childList.add(new Finalgrades_Child("P450","Career Development (1)","A"));
            childList.add(new Finalgrades_Child("CS457","Data Modeling and Implementation Techniques (3)","A+"));
            childList.add(new Finalgrades_Child("CS457L" ,"Database Technologies Lab (1)", "A"));
            childList.add(new Finalgrades_Child("CS480" ,"Java and Internet Applications (3)", "A"));
            childList.add(new Finalgrades_Child("CS480L" ,"Java Programming Lab (1)", "A+"));

        }

        if (titleParent == "2017 Spring") {

            childList.add(new Finalgrades_Child("CS557","Web Front-end Programming for Mobile Devices (3)","A"));
            childList.add(new Finalgrades_Child("CS501","Advanced Structured Programming & Algorithms (3)","A+"));
            childList.add(new Finalgrades_Child("CS556" ,"Mobile Applications on iPhone Platform (3)", "A"));

        }
        if (titleParent == "2017 Fall") {

            childList.add(new Finalgrades_Child("CS548","Web Services Techniques and REST Technologies (3)","IP"));
            childList.add(new Finalgrades_Child("CS551","Mobile Computing for Android Mobile Devices (3)","IP"));
            childList.add(new Finalgrades_Child("CS595" ,"Computer Science Capstone Course (3)", "IP"));

        }
        return childList;
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
}

