package course.android.com.npuapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Home_2Activity extends AppCompatActivity {
    private Session session;

    ImageButton btnmyacc;
    TextView txtmyacct;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getSupportActionBar().hide();

        setContentView(R.layout.activity_home_2);
        btnmyacc = (ImageButton)findViewById(R.id.btn_myacct);
        txtmyacct = (TextView)findViewById(R.id.txt_myacct);
        session = new Session(this);
        if (session.getusename().equals(null) || session.getusename().equals("")) {
            btnmyacc.setVisibility(View.INVISIBLE);
            txtmyacct.setVisibility(View.INVISIBLE);
            //btnLogout.setVisibility(View.INVISIBLE);
        }
        else
        {
            btnmyacc.setVisibility(View.VISIBLE);
            txtmyacct.setVisibility(View.VISIBLE);
        }
    }
        //To display Toolbar (Home Button)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (session.getusename().equals(null) || session.getusename().equals("")) {
            getMenuInflater().inflate(R.menu.home_toolbar, menu);
            return  true;
        }

       return false;
    }

    //Navigate to another activity
    public void goToAnotherActivity(Context currentActivity, Class targetActivity) {
        Intent intentObj = new Intent(currentActivity, targetActivity);
        startActivity(intentObj);
    }

    public void btnLogin_MenuClick(MenuItem item) {
        goToAnotherActivity(Home_2Activity.this, LogInActivity.class);

    }

    public void btn_otherinfo(View view){
        goToAnotherActivity(Home_2Activity.this, MapsActivity.class);
    }

    public void btn_eateries(View view){
        goToAnotherActivity(Home_2Activity.this, NearbyEateriesActivity.class);
    }

    public void btn_lib(View view){
                String libAddress = "https://elib.npu.edu/login";
                Uri webaddress = Uri.parse(libAddress);
                Intent libIntent = new Intent(Intent.ACTION_VIEW,webaddress);
                if (libIntent.resolveActivity(getPackageManager())!=null) {
                    startActivity(libIntent);
                }
    }

    //event button onClick() event handler
    public void btn_eventHomePage_onClick(View view) {
        goToAnotherActivity(Home_2Activity.this, EventActivity.class);
    }

    //course button onClick() event handler
    public void btnCourseHomePage_onClick(View view) {
        goToAnotherActivity(this, CurrentSemsterCourseListActivity.class);
    }

    public void btnSocialMedia_onClick(View view) {
        goToAnotherActivity(this, SocialMediaActivity.class);
    }

    @Override
    public void onBackPressed() {
        goToAnotherActivity(this, Home_2Activity.class);
    }

    public void btn_news_onClick(View view) {
        goToAnotherActivity(Home_2Activity.this, NewsActivity.class);
    }
    public void btn_shuttle_onClick(View view) {
        goToAnotherActivity(this, ShuttleListActivity.class);
    }
    public void btn_events_action(View view) {
        goToAnotherActivity(this, EventInfoActivity.class);
    }
}
