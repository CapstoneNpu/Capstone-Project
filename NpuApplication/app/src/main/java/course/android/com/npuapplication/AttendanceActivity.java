package course.android.com.npuapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class AttendanceActivity extends AppCompatActivity {

    private Intent intentFromCurrentSemesterCourseList;
 PieChart attenChrt;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        session = new Session(this);
        intentFromCurrentSemesterCourseList = getIntent();
       // String courseId = intentFromCurrentSemesterCourseList.getStringExtra("CourseId").toString();
        attenChrt = (PieChart) findViewById(R.id.attendence_chart);
       // attenChrt.setUsePercentValues(true);
        attenChrt.getDescription().setEnabled(false);
       // attenChrt.setExtraOffsets(5,10,5,5);
        attenChrt.setDragDecelerationFrictionCoef(0.95f);
        attenChrt.setDrawHoleEnabled(true);
       // attenChrt.setHoleColor(R.color.colorWhite);
        attenChrt.setHoleRadius(27f);
        attenChrt.setCenterText("Attendance");
        attenChrt.setCenterTextSize(14f);
        attenChrt.setDrawEntryLabels(true);
        attenChrt.setTransparentCircleAlpha(0);

        attenChrt.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(5f,"Absent"));
        yValues.add(new PieEntry(34f,"Present"));
        yValues.add(new PieEntry(12f,"Early Leave"));
        yValues.add(new PieEntry(15f,"Late come"));

        PieDataSet dataset =  new PieDataSet(yValues,"Attemdace");
        dataset.setSliceSpace(3f);
        dataset.setSelectionShift(5f);
        dataset.setColors(ColorTemplate.MATERIAL_COLORS);
dataset.setValueTextSize(12f);
        dataset.setValueTextColor(Color.BLACK);
        PieData data = new PieData(dataset);
        data.setValueTextSize(18f);
       // data.setValueTextColors(Color.BLACK);
        attenChrt.setData(data);
        attenChrt.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                String temp = e.toString();
                Toast.makeText(AttendanceActivity.this,e.toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
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
