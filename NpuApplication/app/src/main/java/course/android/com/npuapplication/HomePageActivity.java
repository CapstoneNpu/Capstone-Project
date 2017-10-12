package course.android.com.npuapplication;


import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HomePageActivity extends AppCompatActivity {

    Button btnCourse;
    Button btnLogin;
    Button btnLogout;

    //Session class object
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        //btnMap = (Button) findViewById(R.id.btn_map_homePage_id);
        //btnMap.setVisibility(View.GONE);

        btnCourse = (Button) findViewById(R.id.btn_course_homepage_id);
        btnLogin = (Button) findViewById(R.id.btn_login_homepage_id);
        btnLogout = (Button) findViewById(R.id.btn_logout_homepage_id);

        session = new Session(this);

        if (session.getusename().equals(null) || session.getusename().equals("")) {
            btnLogin.setVisibility(View.VISIBLE);
            btnCourse.setVisibility(View.INVISIBLE);
            btnLogout.setVisibility(View.INVISIBLE);
        } else {
            btnLogin.setVisibility(View.INVISIBLE);
            btnCourse.setVisibility(View.VISIBLE);
            btnLogout.setVisibility(View.VISIBLE);
        }
      
      //other info feature
      Button otherInfo = (Button)findViewById(R.id.btn_other_info_id);
        otherInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent otherInfoIntent = new Intent(getApplicationContext(),OtherInfoHome.class);
                startActivity(otherInfoIntent);
            }
        });
    }


    //login button onClick() event handler
    public void btnLoginHomePage_onClick(View view) {
        goToAnotherActivity(HomePageActivity.this, LogInActivity.class);
    }

    //logout button onClick() event handler
    public void btnLogoutHomePage_onClick(View view) {
        session.setusename("");
        goToAnotherActivity(this, HomePageActivity.class);
    }

    //Navigate to another activity
    public void goToAnotherActivity(Context currentActivity, Class targetActivity) {
        Intent intentObj = new Intent(currentActivity, targetActivity);
        startActivity(intentObj);
    }

    //event button onClick() event handler
    public void btn_eventHomePage_onClick(View view) {
        goToAnotherActivity(HomePageActivity.this, EventActivity.class);
    }

    //course button onClick() event handler
    public void btnCourseHomePage_onClick(View view) {
        goToAnotherActivity(this, CurrentSemsterCourseListActivity.class);
    }

    public void btnSocialMedia_onClick(View view) {
        goToAnotherActivity(this, SocialMediaActivity.class);
    }



}
