package course.android.com.npuapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import course.android.com.npuapplication.Database.UserData;

public class HomeworkGradeActivity extends AppCompatActivity {

    ArrayList<String> homeworkGradeArrayList;
    ArrayAdapter<String> homeworkGradeArrayAdapter;
    ListView homeworkGradeListView;

    private UserData userDataObj;
    private Session session;
    private DataSnapshot currentSemesterCourseInfo;
    private DataSnapshot currentSemesterCourseHomeworkGradeDeatils;
    private Intent gradeIntent;

    //firebase reference objects
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework_grade);

        session = new Session(this);
        if (session.getusename().equals(null) || session.getusename().equals("")) {
            goToAnotherActivity(this, HomePageActivity.class);
        }

        userDataObj = new UserData();
        gradeIntent = getIntent();

        //firebase database reference object
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userDataObj.setAllUserInfo(userDataObj.fetchAllUserInfo(dataSnapshot));
                currentSemesterCourseInfo = userDataObj.fetchCurrentSemesterCourseInfo(session.getusename());
                currentSemesterCourseHomeworkGradeDeatils = currentSemesterCourseInfo.child(gradeIntent.getStringExtra("selectedCourseId")).child("Grading Policy").child("Homework");

                homeworkGradeArrayList = new ArrayList<String>();
                dataSnapShotToArray(currentSemesterCourseHomeworkGradeDeatils);
                homeworkGradeArrayAdapter = new ArrayAdapter<String>(HomeworkGradeActivity.this, android.R.layout.simple_list_item_1, homeworkGradeArrayList);
                homeworkGradeListView = (ListView) findViewById(R.id.listview_homework_grade_id);
                homeworkGradeListView.setAdapter(homeworkGradeArrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void dataSnapShotToArray(DataSnapshot dataSnapshot) {

        homeworkGradeArrayList.add("Week 1:      " + dataSnapshot.child("Week1").child("Gain").getValue().toString());
        homeworkGradeArrayList.add("Week 2:      " + dataSnapshot.child("Week2").child("Gain").getValue().toString());
        homeworkGradeArrayList.add("Week 3:      " + dataSnapshot.child("Week3").child("Gain").getValue().toString());
        homeworkGradeArrayList.add("Week 4:      " + dataSnapshot.child("Week4").child("Gain").getValue().toString());
        homeworkGradeArrayList.add("Week 5:      " + dataSnapshot.child("Week5").child("Gain").getValue().toString());
        homeworkGradeArrayList.add("Week 6:      " + dataSnapshot.child("Week6").child("Gain").getValue().toString());
        homeworkGradeArrayList.add("Week 7:      " + dataSnapshot.child("Week7").child("Gain").getValue().toString());
        homeworkGradeArrayList.add("Week 8:      " + dataSnapshot.child("Week8").child("Gain").getValue().toString());
        homeworkGradeArrayList.add("Week 9:      " + dataSnapshot.child("Week9").child("Gain").getValue().toString());
        homeworkGradeArrayList.add("Week 10:     " + dataSnapshot.child("Week10").child("Gain").getValue().toString());
        homeworkGradeArrayList.add("Week 11:     " + dataSnapshot.child("Week11").child("Gain").getValue().toString());
        homeworkGradeArrayList.add("Week 12:     " + dataSnapshot.child("Week12").child("Gain").getValue().toString());
        homeworkGradeArrayList.add("Week 13:     " + dataSnapshot.child("Week13").child("Gain").getValue().toString());
        homeworkGradeArrayList.add("Week 14:     " + dataSnapshot.child("Week14").child("Gain").getValue().toString());
        homeworkGradeArrayList.add("Week 15:     " + dataSnapshot.child("Week15").child("Gain").getValue().toString());
    }

    //Navigate to another activity
    public void goToAnotherActivity(Context currentActivity, Class targetActivity) {
        Intent intentObj = new Intent(currentActivity, targetActivity);
        startActivity(intentObj);
    }
}
