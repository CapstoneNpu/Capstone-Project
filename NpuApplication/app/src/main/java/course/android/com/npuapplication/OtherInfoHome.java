package course.android.com.npuapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class OtherInfoHome extends AppCompatActivity {
    Button elibrary;
    Button mapBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_info_home);

        mapBtn = (Button)findViewById(R.id.mapBtn);
        mapBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent mapIntent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(mapIntent);
            }
        });

        elibrary = (Button)findViewById(R.id.libBtn);
        elibrary.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String libAddress = "https://elib.npu.edu/login";
                Uri webaddress = Uri.parse(libAddress);
                Intent libIntent = new Intent(Intent.ACTION_VIEW,webaddress);
                if (libIntent.resolveActivity(getPackageManager())!=null) {
                    startActivity(libIntent);
                }
            }
        });
    }
}
