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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

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
import course.android.com.npuapplication.HelpingFunctions.ExpandableListCreation;

public class CourseDetailsActivity extends AppCompatActivity {

    //variables for expandable list view, keys of HashMap should be elements of listHeader
    List<String> listHeader;
    HashMap<String, List<String>> listChildArg;
    HashMap<String, String> listDataFromDatabase;

    private Intent intentFromCurrentSemesterCourseList;
    private ExpandableListCreation expandableListCreationObj;
    private ListView courseDetailsList;
    private String[] courseDetailsStrArray;
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
        if (session.getusename().equals(null) || session.getusename().equals("")) {
            goToAnotherActivity(this, HomePageActivity.class);
        }

        courseDetailsList = (ListView) findViewById(R.id.listview_course_details_id);
        courseDetailsStrArray = new String[7];
        courseDetailsStrArray[0] = "Contact Hours";
        courseDetailsStrArray[1] = "Credits";
        courseDetailsStrArray[2] = "Load";
        courseDetailsStrArray[3] = "Location";
        courseDetailsStrArray[4] = "On-line";
        courseDetailsStrArray[5] = "Prerequisite";
        courseDetailsStrArray[6] = "Schedule";

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

                assert courseDetailsList != null;
                courseDetailsList.setAdapter(new MyAdapter());

                /*listChildArg = new HashMap<>();
                fillChildHashMap();
                expandableListCreationObj = new ExpandableListCreation();
                expandableListCreationObj.createExpandableListView(
                        CourseDetailsActivity.this,
                        listHeader,
                        listChildArg,
                        R.id.expandview_course_details_id,
                        false
                );*/
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

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return listDataFromDatabase.size();
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
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_details_listview_components, null);

                viewHolder = new ViewHolder();
                viewHolder.btnCourseDetail = (Button) convertView.findViewById(R.id.btn_course_details_list_item);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.btnCourseDetail.setText(courseDetailsStrArray[position]);

            /*GradientDrawable gd = new GradientDrawable(
                    GradientDrawable.Orientation.TOP_BOTTOM,
                    new int[] {0xFFDDDDDD, 0xFFd2d7aa});
            gd.setCornerRadius(0f);
            convertView.setBackground(gd);*/

            /*viewHolder.btnCourseDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HamButton.Builder hamBtnBuilder_details = new HamButton.Builder()
                            //.normalImageRes(getImageResource())
                            .normalTextRes(R.string.course_details);
                    //viewHolder.btnCourseDetail.addBuilder(hamBtnBuilder_details);
                }
            });*/



            return convertView;
        }

        class ViewHolder {
            Button btnCourseDetail;
            //BoomMenuButton
        }
    }
}
