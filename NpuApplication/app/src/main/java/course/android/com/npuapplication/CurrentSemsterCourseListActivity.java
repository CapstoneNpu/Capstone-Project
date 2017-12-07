package course.android.com.npuapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
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
import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.OnBoomListener;

import java.util.Hashtable;
import java.util.List;

import course.android.com.npuapplication.Database.CourseData;
import course.android.com.npuapplication.Database.UserData;
import course.android.com.npuapplication.Domain.Course;

import static course.android.com.npuapplication.R.id.bmb_courseList;
import static course.android.com.npuapplication.R.id.drawer;

public class CurrentSemsterCourseListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

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
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
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
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setIcon(R.mipmap.ic_instagram);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
 getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setNavigationViewListener();

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
                //CustomeAdaptor customeAdaptor = new CustomeAdaptor();
                //courseList.setAdapter(customeAdaptor);

                assert courseList != null;
                courseList.setAdapter(new MyAdapter());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {

            case R.id.financial_info: {
                goToAnotherActivity(CurrentSemsterCourseListActivity.this, FinancialInfoActivity.class);
                break;
            }
            case R.id.personal_info: {
                goToAnotherActivity(CurrentSemsterCourseListActivity.this, PersoalinfoActivity.class);
                break;
            }
            case R.id.academic_info:
            {
                goToAnotherActivity(CurrentSemsterCourseListActivity.this, AcademicInfoActivity.class);
                break;
            }
            case R.id.final_grades:
            {
                goToAnotherActivity(CurrentSemsterCourseListActivity.this, FinalGradesActvity.class);
                break;
            }
        }
        //close navigation drawer
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setNavigationViewListener() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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


    //Navigate to another activity
    public void goToAnotherActivity(Context currentActivity, Class targetActivity) {
        Intent intentObj = new Intent(currentActivity, targetActivity);
        startActivity(intentObj);
    }

    public void goToAnotherActivityBoomButton(Class targetActivity, String courseId) {
        Intent intentObj = new Intent(CurrentSemsterCourseListActivity.this, targetActivity);
        intentObj.putExtra("CourseId", courseId);
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

    public void personainfo_onClick(MenuItem item) {
        // Intent courseSyallbusIntent = new Intent(this, PersonalinfoActivity.class);
        // courseSyallbusIntent.putExtra("CourseId", selectedCourseId);
        //  startActivity(courseSyallbusIntent);
        goToAnotherActivity(this, PersoalinfoActivity.class);

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

    public void boomButtonListener(final int position, MyAdapter.ViewHolder viewHolder) {
        viewHolder.bmb_courseList.setOnBoomListener(new OnBoomListener() {
            @Override
            public void onClicked(int index, BoomButton boomButton) {

                if (boomButton.getTextView().getText().equals("Details")) {
                    goToAnotherActivityBoomButton(CourseDetailsActivity.class, courseIdStringArray[position]);
                } else if (boomButton.getTextView().getText().equals("Grade")) {
                    goToAnotherActivityBoomButton(GradesActivity.class, courseIdStringArray[position]);
                } else if (boomButton.getTextView().getText().equals("Attendance")) {
                    goToAnotherActivityBoomButton(AttendanceActivity.class, courseIdStringArray[position]);
                } else if (boomButton.getTextView().getText().equals("Handout")) {

                } else if (boomButton.getTextView().getText().equals("Syllabus")) {
                    goToAnotherActivityBoomButton(SyllabusActivity.class, courseIdStringArray[position]);
                } else if (boomButton.getTextView().getText().equals("Personal Profile")) {
                    goToAnotherActivity(CurrentSemsterCourseListActivity.this, PersoalinfoActivity.class);
                }
            }

            @Override
            public void onBackgroundClick() {

            }

            @Override
            public void onBoomWillHide() {

            }

            @Override
            public void onBoomDidHide() {

            }

            @Override
            public void onBoomWillShow() {

            }

            @Override
            public void onBoomDidShow() {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goToAnotherActivity(this, Home_2Activity.class);
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
            //TextView txtCredit = (TextView) view.findViewById(R.id.txt_credits);
            //imgbtn = (ImageButton) view.findViewById(R.id.imageButton);
            //  getMenuInflater().inflate(R.menu.toolbar_menu,menu);
            //bookimg.setImageResource(bookImg[i]);
            txtCourseCode.setText(courseIdStringArray[i]);
            txtCourseDesc.setText(courseHashTable.get(courseIdStringArray[i]).getCourseName());
            //txtCredit.setText(courseHashTable.get(courseIdStringArray[i]).getCredit());
            test(courseIdStringArray[i]);
            return view;
        }

        @Override
        public CharSequence[] getAutofillOptions() {
            return new CharSequence[0];
        }
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return courseHashTable.size();
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
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.courselistitem, null);

                viewHolder = new ViewHolder();
                viewHolder.txtCourseCode = (TextView) convertView.findViewById(R.id.txt_coursecode);
                viewHolder.txtCourseDesc = (TextView) convertView.findViewById(R.id.txt_courename);
                viewHolder.bmb_courseList = (BoomMenuButton) convertView.findViewById(bmb_courseList);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.txtCourseCode.setText(courseIdStringArray[position]);
            viewHolder.txtCourseDesc.setText(courseHashTable.get(courseIdStringArray[position]).getCourseName());

            /*imgbtn = (ImageButton) convertView.findViewById(R.id.imageButton);
            test(courseIdStringArray[position]);*/

            viewHolder.bmb_courseList.clearBuilders();
            /*GradientDrawable gd = new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    new int[]{0xFFDDDDDD, 0xFFd2d7aa});
            gd.setCornerRadius(0f);
            convertView.setBackground(gd);*/

            HamButton.Builder hamBtnBuilder_details = new HamButton.Builder()
                    //.normalImageRes(getImageResource())
                    .normalTextRes(R.string.course_details);

            HamButton.Builder hamBtnBuilder_grade = new HamButton.Builder()
                    //.normalImageRes(getImageResource())
                    .normalTextRes(R.string.course_grade);

            HamButton.Builder hamBtnBuilder_attendance = new HamButton.Builder()
                    //.normalImageRes(getImageResource())
                    .normalTextRes(R.string.course_attendance);

            HamButton.Builder hamBtnBuilder_handout = new HamButton.Builder()
                    //.normalImageRes(getImageResource())
                    .normalTextRes(R.string.course_handout);

            HamButton.Builder hamBtnBuilder_syallbus = new HamButton.Builder()
                    //.normalImageRes(getImageResource())
                    .normalTextRes(R.string.course_syallbus);

            HamButton.Builder hamBtnBuilder_personalProfile = new HamButton.Builder()
                    .normalTextRes(R.string.personal_profile);

            viewHolder.bmb_courseList.addBuilder(hamBtnBuilder_details);
            viewHolder.bmb_courseList.addBuilder(hamBtnBuilder_grade);
            viewHolder.bmb_courseList.addBuilder(hamBtnBuilder_attendance);
            viewHolder.bmb_courseList.addBuilder(hamBtnBuilder_handout);
            viewHolder.bmb_courseList.addBuilder(hamBtnBuilder_syallbus);
            viewHolder.bmb_courseList.addBuilder(hamBtnBuilder_personalProfile);

            boomButtonListener(position, viewHolder);

            return convertView;
        }

        class ViewHolder {
            TextView txtCourseCode;
            TextView txtCourseDesc;
            BoomMenuButton bmb_courseList;
        }
    }
}
