package course.android.com.npuapplication;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by sumirna on 12/7/17.
 */

public class EventInfoActivity extends AppCompatActivity {
    private ListView newsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);


        final EventItems[] eventItems = {
                new EventItems(
                        new Date(2017,11,8),
                        "2018 spring trimester application deadline for local and international transfer students"),
                new EventItems(new Date(2017,11,11),
                        "Course review and final exams"),
                new EventItems(new Date(2017,11,23),
                        "Christmas Holiday (12/23 - 12/31); Campus Closed"),
                new EventItems(new Date(2017,11,30),
                        "Posting final grades for 2017 fall trimester"),
                new EventItems(new Date(2018,0,1),
                        "New Year Holiday; Campus Closed."),
                new EventItems(new Date(2018,0,4),
                        "New Year Holiday; Campus Closed.")

        };
        newsListView = (ListView) findViewById(R.id.event_list);
        EventAdapter adapter = new EventAdapter(getApplicationContext(), eventItems, EventInfoActivity.this);
        newsListView.setAdapter(adapter);
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent calIntent = new Intent(Intent.ACTION_INSERT);
                calIntent.setType("vnd.android.cursor.item/event");
                calIntent.putExtra(CalendarContract.Events.TITLE, "NPU Event");
                calIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Northwestern Polytechnic University");
                calIntent.putExtra(CalendarContract.Events.DESCRIPTION, eventItems[position].getDetails());

                GregorianCalendar calDate = new GregorianCalendar(eventItems[position].getEventDate().getYear(), eventItems[position].getEventDate().getMonth(), eventItems[position].getEventDate().getDate());
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                        calDate.getTimeInMillis());
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                        calDate.getTimeInMillis());

                startActivity(calIntent);
            }
       });
    }
}

class EventItems {
    private Date eventDate;
    private String month;
    private String date;
    private String details;
    private String year;
    EventItems(Date eventDate, String details) {
        this.eventDate = eventDate;
        this.details = details;

    }
    public Date getEventDate() {
        return this.eventDate;
    }

    public String getMonth() {

        this.month = (String) DateFormat.format("MMM", eventDate);
        return this.month;
    }

    public String getDate(){

        this.date = (String) DateFormat.format("dd", eventDate);
        return this.date;
    }

    public String getDetails(){ return this.details; }

    public String getYear(){
        this.year = (String) DateFormat.format("yyyy", eventDate);
        return this.year; }
}
