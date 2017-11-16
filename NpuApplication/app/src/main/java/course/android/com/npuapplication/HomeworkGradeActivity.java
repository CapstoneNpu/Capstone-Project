package course.android.com.npuapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
    TextView homeworkTotalScoreTextView;

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

        homeworkTotalScoreTextView = (TextView) findViewById(R.id.txtview_homework_grade_title_id);

        //firebase database reference object
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userDataObj.setAllUserInfo(userDataObj.fetchAllUserInfo(dataSnapshot));
                currentSemesterCourseInfo = userDataObj.fetchCurrentSemesterCourseInfo(session.getusename());
                currentSemesterCourseHomeworkGradeDeatils = currentSemesterCourseInfo.child(gradeIntent.getStringExtra("selectedCourseId")).child("Grading Policy").child("Homework");

                homeworkTotalScoreTextView.setText("Homework Score: " + String.valueOf(currentSemesterCourseHomeworkGradeDeatils.child("Gain").getValue())+"%");

                homeworkGradeArrayList = new ArrayList<String>();
                dataSnapShotToArray(currentSemesterCourseHomeworkGradeDeatils);
                homeworkGradeListView = (ListView) findViewById(R.id.listview_homework_grade_id);

                homeworkGradeListView.setAdapter(new MyAdapter());

                /*homeworkGradeArrayAdapter = new ArrayAdapter<String>(HomeworkGradeActivity.this, android.R.layout.simple_list_item_1, homeworkGradeArrayList);
                homeworkGradeListView.setAdapter(homeworkGradeArrayAdapter);*/
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

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return homeworkGradeArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {

            final ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grade_details_listview_components, null);

                viewHolder = new ViewHolder();
                viewHolder.btnGradeDetail = (Button) convertView.findViewById(R.id.btn_grade_details_list_item);
                viewHolder.imgViewGradeDetailNavigate = (ImageView) convertView.findViewById(R.id.imgview_grade_details_navigate_to_next_page_id);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.btnGradeDetail.setText(homeworkGradeArrayList.get(position));

            /*GradientDrawable gd = new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    new int[]{0xFFDDDDDD, 0xFFd2d7aa});
            gd.setCornerRadius(0f);
            convertView.setBackground(gd);*/

            return convertView;
        }

        class ViewHolder {
            Button btnGradeDetail;
            ImageView imgViewGradeDetailNavigate;
        }
    }
}
