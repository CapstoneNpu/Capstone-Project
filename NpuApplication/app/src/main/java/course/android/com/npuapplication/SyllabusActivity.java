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
import java.util.List;

import course.android.com.npuapplication.Database.CourseData;
import course.android.com.npuapplication.HelpingFunctions.ExpandableListCreation;

public class SyllabusActivity extends AppCompatActivity {

    HashMap<String, List<String>> mapDataFromDatabase;

    private Intent intentFromCurrentSemesterCourseList;
    private CourseData courseDataObj;
    private FirebaseDatabase firebaseDatabase;
    private String courseId;
    private Session session;

    private ExpandableListCreation expandableListCreationObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);
        session = new Session(this);
        intentFromCurrentSemesterCourseList = getIntent();
        courseId = intentFromCurrentSemesterCourseList.getStringExtra("CourseId").toString();

        courseDataObj = new CourseData();

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                courseDataObj.setAllCourseInfo(courseDataObj.fetchAllCourseData(dataSnapshot));
                courseDataObj.setSelectedCourseInfo(courseDataObj.fetchSelectedCourseInfo(courseId));
                mapDataFromDatabase = courseDataObj.fetchCourseDetailsForSyllabusPage();
                List<String> listHeader = new ArrayList<String>(mapDataFromDatabase.keySet());
                expandableListCreationObj = new ExpandableListCreation();
                expandableListCreationObj.createExpandableListView(
                        SyllabusActivity.this,
                        listHeader,
                        mapDataFromDatabase,
                        R.id.expandview_course_syllabus_id
                );
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
