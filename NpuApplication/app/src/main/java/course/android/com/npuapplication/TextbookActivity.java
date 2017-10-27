package course.android.com.npuapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.view.View;
import android.widget.TextView;

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

public class TextbookActivity extends AppCompatActivity {

    HashMap<String, String> textBookData;

    private Intent intentFromCourseList;
    private CourseData courseDataObj;
    private FirebaseDatabase firebaseDatabase;
    private String courseId;
    private String textBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textbook);
        intentFromCourseList = getIntent();
        courseId = intentFromCourseList.getStringExtra("CourseId").toString();
        textBook = intentFromCourseList.getStringExtra("textBook").toString();
        System.out.println(courseId);
        System.out.println(textBook);

        courseDataObj = new CourseData();

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TextView publisher = (TextView) findViewById(R.id.text_book_publisher);
                TextView isbn = (TextView) findViewById(R.id.text_book_isbn);
                TextView title = (TextView) findViewById(R.id.text_book_name);
                TextView author = (TextView) findViewById(R.id.text_book_author);
                courseDataObj.setAllCourseInfo(courseDataObj.fetchAllCourseData(dataSnapshot));
                courseDataObj.setSelectedCourseInfo(courseDataObj.fetchSelectedCourseInfo(courseId));
                textBookData = courseDataObj.fetchTextBookData(textBook);
                author.setText(textBookData.get("Author"));
                isbn.setText(textBookData.get("ISBN"));
                title.setText(textBookData.get("Title"));
                publisher.setText(textBookData.get("Publisher"));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
