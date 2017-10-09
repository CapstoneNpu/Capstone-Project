package course.android.com.npuapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Hashtable;
import java.util.List;

import course.android.com.npuapplication.Database.CourseData;
import course.android.com.npuapplication.Database.UserData;
import course.android.com.npuapplication.Domain.Course;

public class CurrentSemsterCourseListActivity extends AppCompatActivity {

    //define variables
    private DataSnapshot courseDataSnapObj;
    private Session session;
    private UserData userDataObj;
    private CourseData courseDataObj;
    private Course courseObj;
    private String[] courseCode;
    private String[] courseDesc;
    private String[] courseCredits;
    private CustomeAdaptor customeAdaptor;
    private Hashtable<String, Course> courseHashTable;
    private String[] courseIdStringArray;

    //selected course from popup
    private String selectedCourseId;


    //define components
    private ListView courseList;
    private ImageButton imgbtn;

    //firebase reference objects
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_semster_course_list);

        session = new Session(this);
        userDataObj = new UserData();
        courseDataObj = new CourseData();

        courseList = (ListView) findViewById(R.id.courseList);

        if (session.getusename().equals(null) || session.getusename().equals("")) {
            goToAnotherActivity(this, HomePageActivity.class);
        }

        //firebase database reference object
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userDataObj.setAllUserInfo(userDataObj.fetchAllUserInfo(dataSnapshot));
                courseDataSnapObj = userDataObj.fetchCurrentSemesterCourseInfo(session.getusename());

                courseDataObj.setAllCourseInfo(courseDataObj.fetchAllCourseData(dataSnapshot));
                dataSnapShotToHashTable(courseDataSnapObj);
                CustomeAdaptor customeAdaptor = new CustomeAdaptor();
                courseList.setAdapter(customeAdaptor);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public Hashtable<String, Course> dataSnapShotToHashTable(DataSnapshot dataSnapshot) {
        courseHashTable = new Hashtable<>();
        courseIdStringArray = new String[Integer.valueOf(String.valueOf(dataSnapshot.getChildrenCount()))];
        int index = 0;

        Iterable<DataSnapshot> dataSnapshotIterable = dataSnapshot.getChildren();
        if (dataSnapshotIterable instanceof List) {
            return (Hashtable<String, Course>) dataSnapshotIterable;
        }

        if (dataSnapshotIterable != null) {
            for (DataSnapshot e : dataSnapshotIterable) {

                courseDataObj.setSelectedCourseInfo(courseDataObj.fetchSelectedCourseInfo(String.valueOf(e.getKey())));
                courseObj = new Course(String.valueOf(e.getKey()), courseDataObj.fetchCourseName(), courseDataObj.fetchCourseCredits());

                courseIdStringArray[index] = String.valueOf(e.getKey());
                index++;
                courseHashTable.put(String.valueOf(e.getKey()), courseObj);
            }
        }
        return courseHashTable;
    }

    ;

    //Navigate to another activity
    public void goToAnotherActivity(Context currentActivity, Class targetActivity) {
        Intent intentObj = new Intent(currentActivity, targetActivity);
        startActivity(intentObj);
    }

    public void courseDetails_onClick(MenuItem item) {
        Intent courseDetailsIntent = new Intent(this, CourseDetailsActivity.class);
        courseDetailsIntent.putExtra("CourseId", selectedCourseId);
        startActivity(courseDetailsIntent);
    }

    public void courseGrades_onClick(MenuItem item) {
        Intent courseGradesIntent = new Intent(this, GradesActivity.class);
        courseGradesIntent.putExtra("CourseId", selectedCourseId);
        startActivity(courseGradesIntent);
    }

    public void courseAttendance_onClick(MenuItem item) {
        Intent courseAttendanceIntent = new Intent(this, AttendanceActivity.class);
        courseAttendanceIntent.putExtra("CourseId", selectedCourseId);
        startActivity(courseAttendanceIntent);
    }

    public void courseHandout_onClick(MenuItem item) {
        Intent courseHandoutIntent = new Intent(this, HandoutActivity.class);
        courseHandoutIntent.putExtra("CourseId", selectedCourseId);
        startActivity(courseHandoutIntent);
    }

    public void courseSyllabus_onClick(MenuItem item) {
        Intent courseSyallbusIntent = new Intent(this, SyllabusActivity.class);
        courseSyallbusIntent.putExtra("CourseId", selectedCourseId);
        startActivity(courseSyallbusIntent);
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

    private void showPopupMenu(View view, String courseId) {

        PopupMenu popup = new PopupMenu(this, view);
        selectedCourseId = courseId;
        popup.getMenuInflater().inflate(R.menu.popupmenu_course, popup.getMenu());
        popup.show();
    }

    public void test(final String courseId) {
        imgbtn.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          showPopupMenu(view, courseId);
                                      }
                                  }
        );
    }


    class CustomeAdaptor extends BaseAdapter {


        @Override
        public int getCount() {
            return courseHashTable.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.courselistitem, null);
            ImageView bookimg = (ImageView) view.findViewById(R.id.imageView3);
            TextView txtCourseCode = (TextView) view.findViewById(R.id.txt_coursecode);
            TextView txtCourseDesc = (TextView) view.findViewById(R.id.txt_courename);
            TextView txtCredit = (TextView) view.findViewById(R.id.txt_credits);
            imgbtn = (ImageButton) view.findViewById(R.id.imageButton);
            //  getMenuInflater().inflate(R.menu.toolbar_menu,menu);
            //bookimg.setImageResource(bookImg[i]);
            txtCourseCode.setText(courseIdStringArray[i]);
            txtCourseDesc.setText(courseHashTable.get(courseIdStringArray[i]).getCourseName());
            txtCredit.setText(courseHashTable.get(courseIdStringArray[i]).getCredit());
            test(courseIdStringArray[i]);
            return view;
        }

        @Override
        public CharSequence[] getAutofillOptions() {
            return new CharSequence[0];
        }
    }
}
