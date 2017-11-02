package course.android.com.npuapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.HashMap;

import course.android.com.npuapplication.Database.CourseData;

public class WeekActivity extends AppCompatActivity {

    HashMap<String, String> weekData;

    private Intent intentFromCourseList;
    private CourseData courseDataObj;
    private FirebaseDatabase firebaseDatabase;
    private String courseId;
    private String weekNum;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WeekActivity.context = getApplicationContext();
        setContentView(R.layout.activity_week);
        intentFromCourseList = getIntent();
        courseId = intentFromCourseList.getStringExtra("CourseId").toString();
        weekNum = intentFromCourseList.getStringExtra("WeekNum").toString();

        courseDataObj = new CourseData();

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                courseDataObj.setAllCourseInfo(courseDataObj.fetchAllCourseData(dataSnapshot));
                courseDataObj.setSelectedCourseInfo(courseDataObj.fetchSelectedCourseInfo(courseId));
                weekData = courseDataObj.fetchWeeklyData(weekNum);

                ExpandableTextView expandableTextView;
                ExpandableTextView expandableTextView1;

                courseDataObj.setAllCourseInfo(courseDataObj.fetchAllCourseData(dataSnapshot));
                courseDataObj.setSelectedCourseInfo(courseDataObj.fetchSelectedCourseInfo(courseId));
                weekData = courseDataObj.fetchWeeklyData(weekNum);

                ((TextView) findViewById(R.id.weekly_assignments).findViewById(R.id.title)).setText("Assignments");
                expandableTextView = (ExpandableTextView)findViewById(R.id.weekly_assignments).findViewById(R.id.expandable_text_view);
                expandableTextView.setText(weekData.get("Assignments"));

                ((TextView) findViewById(R.id.weekly_activities).findViewById(R.id.title)).setText("Instructive Coverage & Activities");
                expandableTextView = (ExpandableTextView)findViewById(R.id.weekly_activities).findViewById(R.id.expandable_text_view);
                expandableTextView.setText(weekData.get("Instructive Coverage & Activities"));

                ((TextView) findViewById(R.id.weekly_objectives).findViewById(R.id.title)).setText("Objectives");
                expandableTextView1 = (ExpandableTextView)findViewById(R.id.weekly_objectives).findViewById(R.id.expandable_text_view);
                expandableTextView1.setText(weekData.get("Objectives"));

                ((TextView) findViewById(R.id.weekly_others).findViewById(R.id.title)).setText("Others");
                expandableTextView = (ExpandableTextView)findViewById(R.id.weekly_others).findViewById(R.id.expandable_text_view);
                expandableTextView.setText(weekData.get("Others"));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static Context getAppContext() {
        return WeekActivity.context;
    }
}
