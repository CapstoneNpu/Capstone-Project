package course.android.com.npuapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import course.android.com.npuapplication.Database.UserData;

public class GradesActivity extends AppCompatActivity {

    private Intent intentFromCurrentSemesterCourseList;

    private Session session;

    private UserData userDataObj;
    private String selectedCourseId;
    private Session session;
    private DataSnapshot currentSemesterCourseInfo;
    private DataSnapshot currentSemesterCourseGradeDeatils;
    private ArrayList<String> gradeListItems;
    private ArrayAdapter<String> gradeListItemsAdapter;
    private ListView gradeListView;

    private TextView txtViewTotalGrade;

    //firebase reference objects
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);
      
        session = new Session(this);
        if (session.getusename().equals(null) || session.getusename().equals("")) {
            goToAnotherActivity(this, HomePageActivity.class);
        }
      
        intentFromCurrentSemesterCourseList = getIntent();
        selectedCourseId = intentFromCurrentSemesterCourseList.getStringExtra("CourseId").toString();

        txtViewTotalGrade = (TextView) findViewById(R.id.txtview_total_grade_id);


        userDataObj = new UserData();

        //firebase database reference object
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userDataObj.setAllUserInfo(userDataObj.fetchAllUserInfo(dataSnapshot));
                currentSemesterCourseInfo = userDataObj.fetchCurrentSemesterCourseInfo(session.getusename());

                currentSemesterCourseGradeDeatils = currentSemesterCourseInfo.child(selectedCourseId).child("Grading Policy");

                txtViewTotalGrade.setText("Total: " + currentSemesterCourseGradeDeatils.child("Total").child("Gain").getValue().toString() + "%");
                gradeListItems = new ArrayList<String>();
                dataSnapShotToArray(currentSemesterCourseGradeDeatils);
                gradeListItemsAdapter = new ArrayAdapter<String>(GradesActivity.this, android.R.layout.simple_list_item_1, gradeListItems);
                gradeListView = (ListView) findViewById(R.id.listview_grade_details_id);
                gradeListView.setAdapter(gradeListItemsAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void dataSnapShotToArray(DataSnapshot dataSnapshot) {

        gradeListItems.add("Homework: " + String.valueOf(dataSnapshot.child("Homework").child("Gain").getValue()) + "%");
        gradeListItems.add("Quiz: " + dataSnapshot.child("Quiz").child("Gain").getValue().toString() + "%");
        gradeListItems.add("Midterm: " + dataSnapshot.child("Midterm").child("Gain").getValue().toString() + "%");
        gradeListItems.add("Final: " + dataSnapshot.child("Final").child("Gain").getValue().toString() + "%");
        gradeListItems.add("Participation: " + dataSnapshot.child("Participation").child("Gain").getValue().toString() + "%");
        gradeListItems.add("Project: " + dataSnapshot.child("Project").child("Gain").getValue().toString() + "%");
        gradeListItems.add("Presentation: " + dataSnapshot.child("Presentation").child("Gain").getValue().toString() + "%");
        gradeListItems.add("Others:" + dataSnapshot.child("Others").child("Gain").getValue().toString() + "%");
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
