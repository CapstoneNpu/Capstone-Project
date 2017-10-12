package course.android.com.npuapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Home_2Activity extends AppCompatActivity {
    private Session session;

    ImageButton btnmyacc;
    TextView txtmyacct;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    public void btnLgoin_MenuClick(MenuItem item) {
        goToAnotherActivity(Home_2Activity.this, LogInActivity.class);

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

}
