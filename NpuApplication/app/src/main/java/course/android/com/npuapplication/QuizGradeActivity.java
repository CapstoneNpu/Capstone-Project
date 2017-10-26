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

public class QuizGradeActivity extends AppCompatActivity {

    ArrayList<String> quizGradeArrayList;
    ArrayAdapter<String> quizGradeArrayAdapter;
    ListView quizGradeListView;

    private UserData userDataObj;
    private Session session;
    private DataSnapshot currentSemesterCourseInfo;
    private DataSnapshot currentSemesterCourseQuizGradeDeatils;
    private Intent gradeIntent;

    //firebase reference objects
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_grade);

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
                currentSemesterCourseQuizGradeDeatils = currentSemesterCourseInfo.child(gradeIntent.getStringExtra("selectedCourseId")).child("Grading Policy").child("Quiz");

                quizGradeArrayList = new ArrayList<String>();
                dataSnapShotToArray(currentSemesterCourseQuizGradeDeatils);
                quizGradeArrayAdapter = new ArrayAdapter<String>(QuizGradeActivity.this, android.R.layout.simple_list_item_1, quizGradeArrayList);
                quizGradeListView = (ListView) findViewById(R.id.listview_quiz_grade_id);
                quizGradeListView.setAdapter(quizGradeArrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void dataSnapShotToArray(DataSnapshot dataSnapshot) {

        quizGradeArrayList.add("Week 1:      " + dataSnapshot.child("Week1").child("Gain").getValue().toString());
        quizGradeArrayList.add("Week 2:      " + dataSnapshot.child("Week2").child("Gain").getValue().toString());
        quizGradeArrayList.add("Week 3:      " + dataSnapshot.child("Week3").child("Gain").getValue().toString());
        quizGradeArrayList.add("Week 4:      " + dataSnapshot.child("Week4").child("Gain").getValue().toString());
        quizGradeArrayList.add("Week 5:      " + dataSnapshot.child("Week5").child("Gain").getValue().toString());
        quizGradeArrayList.add("Week 6:      " + dataSnapshot.child("Week6").child("Gain").getValue().toString());
        quizGradeArrayList.add("Week 7:      " + dataSnapshot.child("Week7").child("Gain").getValue().toString());
        quizGradeArrayList.add("Week 8:      " + dataSnapshot.child("Week8").child("Gain").getValue().toString());
        quizGradeArrayList.add("Week 9:      " + dataSnapshot.child("Week9").child("Gain").getValue().toString());
        quizGradeArrayList.add("Week 10:     " + dataSnapshot.child("Week10").child("Gain").getValue().toString());
        quizGradeArrayList.add("Week 11:     " + dataSnapshot.child("Week11").child("Gain").getValue().toString());
        quizGradeArrayList.add("Week 12:     " + dataSnapshot.child("Week12").child("Gain").getValue().toString());
        quizGradeArrayList.add("Week 13:     " + dataSnapshot.child("Week13").child("Gain").getValue().toString());
        quizGradeArrayList.add("Week 14:     " + dataSnapshot.child("Week14").child("Gain").getValue().toString());
        quizGradeArrayList.add("Week 15:     " + dataSnapshot.child("Week15").child("Gain").getValue().toString());
    }

    //Navigate to another activity
    public void goToAnotherActivity(Context currentActivity, Class targetActivity) {
        Intent intentObj = new Intent(currentActivity, targetActivity);
        startActivity(intentObj);
    }
}
