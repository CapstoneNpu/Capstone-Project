package course.android.com.npuapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartUpActivity extends AppCompatActivity {
    private static int timeout =3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
        new Handler().postDelayed(new Runnable()
                                  {
                                      @Override
                                      public void run()
                                      {
                                          Intent homeintent = new Intent(StartUpActivity.this,Home_2Activity.class);
                                          startActivity(homeintent);
                                      }
                                  }
                ,timeout);
    }
}
