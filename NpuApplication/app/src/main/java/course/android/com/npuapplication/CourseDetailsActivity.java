package course.android.com.npuapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import course.android.com.npuapplication.HelpingFunctions.ExpandableListCreation;

public class CourseDetailsActivity extends AppCompatActivity {

    //variables for expandable list view, keys of HashMap should be elements of listHeaderStringArray String Array
    String[] listHeaderStringArray = {"WeeklyInfo", "TeachingInfo"};
    HashMap<String, List<String>> listChildArg;

    private Intent intentFromCurrentSemesterCourseList;
    private ExpandableListCreation expandableListCreationObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        intentFromCurrentSemesterCourseList = getIntent();
        String courseId = intentFromCurrentSemesterCourseList.getStringExtra("CourseId").toString();

        listChildArg = new HashMap<>();
        fillChildHashMap();
        expandableListCreationObj = new ExpandableListCreation();
        expandableListCreationObj.createExpandableListView(this, listHeaderStringArray, listChildArg);
    }

    public void fillChildHashMap() {

        List<String> subItemList1 = new ArrayList<>();
        subItemList1.add("Week1");
        subItemList1.add("Week2");
        subItemList1.add("Week3");
        subItemList1.add("Week4");
        subItemList1.add("Week5");
        listChildArg.put(listHeaderStringArray[0], subItemList1);

        List<String> subItemList2 = new ArrayList<>();
        subItemList2.add("TextBook");
        subItemList2.add("ReferenceBook");
        listChildArg.put(listHeaderStringArray[1], subItemList2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    //Home button(Action bar) onClick event handler
    public void btnGoToHome_onClick(MenuItem item) {
        goToAnotherActivity(this, HomePageActivity.class);
    }

    //Navigate to another activity
    public void goToAnotherActivity(Context currentActivity, Class targetActivity) {
        Intent intentObj = new Intent(currentActivity, targetActivity);
        startActivity(intentObj);
    }
}
