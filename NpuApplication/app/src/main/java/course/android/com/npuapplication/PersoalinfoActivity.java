package course.android.com.npuapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class PersoalinfoActivity extends AppCompatActivity {
private TextView txtname,txtemail,txtsem,txtspec,txtGPA,txtaddres,txtdob,txtphone;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persoalinfo);
        UserPersoalInfo userPersoalInfo = new UserPersoalInfo();
        userPersoalInfo.setUser_name("Amy Taylor");
        userPersoalInfo.setUser_email("amy.taylor@npu.edu");
        userPersoalInfo.setUser_cursem("Fall 2017");
        userPersoalInfo.setUser_spec("CS");
        userPersoalInfo.setUser_curgpa("3.9");
        userPersoalInfo.setUser_address("47671 Westinghouse Dr, Fremont, CA 94539");
        userPersoalInfo.setUser_phone("999-000-9999");
        userPersoalInfo.setUser_dob("1 Jan 1990");

        txtname = (TextView)findViewById(R.id.txt_pinfo_name);
        txtname.setText(userPersoalInfo.getUser_name());
        txtemail= (TextView)findViewById(R.id.txt_pinfo_email);
        txtemail.setText(userPersoalInfo.getUser_email());
        txtsem= (TextView)findViewById(R.id.txt_pinfo_cursem);
        txtsem.setText(userPersoalInfo.getUser_cursem());
                txtspec= (TextView)findViewById(R.id.txt_pinfo_spec);
        txtspec.setText(userPersoalInfo.getUser_spec());
                txtGPA= (TextView)findViewById(R.id.txt_pinfo_gpa);
        txtGPA.setText(userPersoalInfo.getUser_curgpa());
                txtaddres= (TextView)findViewById(R.id.txt_pinfo_add);
        txtaddres.setText(userPersoalInfo.getUser_address());
                txtdob= (TextView)findViewById(R.id.txt_pinfo_dob);
        txtdob.setText(userPersoalInfo.getUser_dob());
                txtphone= (TextView)findViewById(R.id.txt_pinfo_phone);
        txtphone.setText(userPersoalInfo.getUser_phone());

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
}
