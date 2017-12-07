package course.android.com.npuapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class GradesActivity extends AppCompatActivity {

    private Intent intentFromCurrentSemesterCourseList;
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
                gradeListView = (ListView) findViewById(R.id.listview_grade_details_id);

                assert gradeListView != null;
                gradeListView.setAdapter(new MyAdapter());

                /*gradeListItemsAdapter = new ArrayAdapter<String>(GradesActivity.this, android.R.layout.simple_list_item_1, gradeListItems);
                gradeListView.setAdapter(gradeListItemsAdapter);*/
                setupListViewListener();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void setupListViewListener() {
        gradeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (gradeListView.getItemAtPosition(position).toString().substring(0, 8).equals("Homework")) {
                    Intent homeworkIntent = new Intent(GradesActivity.this, HomeworkGradeActivity.class);
                    homeworkIntent.putExtra("selectedCourseId", selectedCourseId);
                    startActivity(homeworkIntent);
                }
                if (gradeListView.getItemAtPosition(position).toString().substring(0, 4).equals("Quiz")) {
                    Intent quizIntent = new Intent(GradesActivity.this, QuizGradeActivity.class);
                    quizIntent.putExtra("selectedCourseId", selectedCourseId);
                    startActivity(quizIntent);
                }
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

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return gradeListItems.size();
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

            viewHolder.btnGradeDetail.setText(gradeListItems.get(position));

            if (position == 0 || position == 1)
                convertView.findViewById(R.id.imgview_grade_details_navigate_to_next_page_id).setVisibility(View.VISIBLE);

            /*if (viewHolder.btnGradeDetail.getText().toString().substring(0, 8).equals("Homework")) {
                viewHolder.imgViewGradeDetailNavigate.setVisibility(View.VISIBLE);
            }
            if (viewHolder.btnGradeDetail.getText().toString().substring(0, 4).equals("Quiz")) {
                viewHolder.imgViewGradeDetailNavigate.setVisibility(View.VISIBLE);
            }*/
            viewHolder.btnGradeDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (gradeListItems.get(position).substring(0, 8).equals("Homework")) {
                        Intent homeworkIntent = new Intent(GradesActivity.this, HomeworkGradeActivity.class);
                        homeworkIntent.putExtra("selectedCourseId", selectedCourseId);
                        startActivity(homeworkIntent);
                    }
                    if (gradeListItems.get(position).toString().substring(0, 4).equals("Quiz")) {
                        Intent quizIntent = new Intent(GradesActivity.this, QuizGradeActivity.class);
                        quizIntent.putExtra("selectedCourseId", selectedCourseId);
                        startActivity(quizIntent);
                    }
                }
            });
            return convertView;
        }

        class ViewHolder {
            Button btnGradeDetail;
            ImageView imgViewGradeDetailNavigate;
        }
    }
}
