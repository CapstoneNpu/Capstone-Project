package course.android.com.npuapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import course.android.com.npuapplication.Database.CourseData;
import uk.co.senab.photoview.PhotoViewAttacher;

public class TextbookActivity extends AppCompatActivity {

    HashMap<String, String> textBookData;

    private Intent intentFromCourseList;
    private CourseData courseDataObj;
    private FirebaseDatabase firebaseDatabase;
    private String courseId;
    private String textBook;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextbookActivity.context = getApplicationContext();
        setContentView(R.layout.activity_textbook);
        intentFromCourseList = getIntent();
        courseId = intentFromCourseList.getStringExtra("CourseId").toString();
        textBook = intentFromCourseList.getStringExtra("textBook").toString();

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
                ImageView image = (ImageView)findViewById(R.id.textBookImage);

                courseDataObj.setAllCourseInfo(courseDataObj.fetchAllCourseData(dataSnapshot));
                courseDataObj.setSelectedCourseInfo(courseDataObj.fetchSelectedCourseInfo(courseId));
                textBookData = courseDataObj.fetchTextBookData(textBook);

                author.setText(textBookData.get("Author"));
                isbn.setText(textBookData.get("ISBN"));
                title.setText(textBookData.get("Title"));
                publisher.setText(textBookData.get("Publisher"));
                Picasso.with(getAppContext()).load(textBookData.get("Url")).into(image);

                PhotoViewAttacher photoView = new PhotoViewAttacher(image);
                photoView.update();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static Context getAppContext() {
        return TextbookActivity.context;
    }
}
