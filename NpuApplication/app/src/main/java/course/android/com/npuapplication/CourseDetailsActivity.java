package course.android.com.npuapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import course.android.com.npuapplication.Database.CourseData;
import course.android.com.npuapplication.HelpingFunctions.ExpandableListCreationForCourseDetails;

public class CourseDetailsActivity extends AppCompatActivity {

    //variables for expandable list view, keys of HashMap should be elements of listHeader
    List<String> listHeader;
    HashMap<String, List<String>> listChildArg;
    HashMap<String, String> listDataFromDatabase;

    private Intent intentFromCurrentSemesterCourseList;
    private ExpandableListCreationForCourseDetails expandableListCreationObj;
    private CourseData courseDataObj;
    private String courseId;
    private Session session;
    //firebase reference objects
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        session = new Session(this);
        intentFromCurrentSemesterCourseList = getIntent();
        courseId = intentFromCurrentSemesterCourseList.getStringExtra("CourseId").toString();

        courseDataObj = new CourseData();
        listHeader = new ArrayList<>();

        //firebase database reference object
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                courseDataObj.setAllCourseInfo(courseDataObj.fetchAllCourseData(dataSnapshot));
                courseDataObj.setSelectedCourseInfo(courseDataObj.fetchSelectedCourseInfo(courseId));
                listDataFromDatabase = courseDataObj.fetchCourseDetailsForCourseDetailsPage();

                listChildArg = new HashMap<>();
                fillChildHashMap();
                expandableListCreationObj = new ExpandableListCreationForCourseDetails();
                expandableListCreationObj.createExpandableListView(
                        CourseDetailsActivity.this,
                        listHeader,
                        listChildArg,
                        R.id.expandview_course_details_id,
                        false
                );
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void fillChildHashMap() {

        if (listDataFromDatabase != null) {
            Iterator listHeaderIterator = listDataFromDatabase.entrySet().iterator();
            while (listHeaderIterator.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry) listHeaderIterator.next();
                listHeader.add(pair.getKey().toString());
                List<String> subItemList = new ArrayList<>();
                subItemList.add(pair.getValue().toString());
                listChildArg.put(pair.getKey().toString(), subItemList);
            }
        }
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
