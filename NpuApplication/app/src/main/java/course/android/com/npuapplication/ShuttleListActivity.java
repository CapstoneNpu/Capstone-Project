package course.android.com.npuapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by sumirna on 12/6/17.
 */

public class ShuttleListActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuttle_info);


        final shuttleDetails[] shuttleItems = {
                new shuttleDetails(
                        "NPU AC Transit EasyPass",
                        "http://www.npu.edu/transportation-easypass"),
                new shuttleDetails(
                        "NPU Airport Pickup Service",
                        "http://www.npu.edu/campus/pickup"),
                new shuttleDetails(
                        "Bus Schedules (AC Transit)",
                        "http://www.actransit.org/"),
                new shuttleDetails(
                        "Bus Schedules (VTA)",
                        "http://www.vta.org/"),
                new shuttleDetails(
                        "Bay Area Rapid Transit (train)",
                        "http://www.bart.gov/"),
               new shuttleDetails("Amtrak (train)",
                       "https://www.amtrak.com/"),
                new shuttleDetails(
                        "CAL Train",
                        "http://www.caltrain.com/")
        };
        listView = (ListView) findViewById(R.id.list_shuttle);
        ShuttleAdapter adapter = new ShuttleAdapter(getApplicationContext(), shuttleItems, ShuttleListActivity.this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ShuttleListActivity.this, WebViewActivity.class);
                i.putExtra("url", shuttleItems[position].getUrl());
                startActivity(i);
            }
        });
    }
}

class shuttleDetails {
    private String title;
    private String url;

    shuttleDetails(String title,String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUrl(){
        return this.url;
    }

}