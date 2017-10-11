package course.android.com.npuapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class HomePageActivity extends AppCompatActivity {

    Button btnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        btnMap = (Button) findViewById(R.id.btn_map_homePage_id);
        //btnMap.setVisibility(View.GONE);

        Button otherInfo = (Button)findViewById(R.id.btn_other_info_id);
        otherInfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent otherInfoIntent = new Intent(getApplicationContext(),OtherInfoHome.class);
                startActivity(otherInfoIntent);
            }
        });
    }
}
