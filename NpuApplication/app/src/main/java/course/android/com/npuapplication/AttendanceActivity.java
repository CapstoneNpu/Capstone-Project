package course.android.com.npuapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class AttendanceActivity extends AppCompatActivity {

    private Intent intentFromCurrentSemesterCourseList;
 PieChart attenChrt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        intentFromCurrentSemesterCourseList = getIntent();
        String courseId = intentFromCurrentSemesterCourseList.getStringExtra("CourseId").toString();
        attenChrt = (PieChart) findViewById(R.id.attendence_chart);
        attenChrt.setUsePercentValues(true);
        attenChrt.getDescription().setEnabled(false);
        attenChrt.setExtraOffsets(5,10,5,5);
        attenChrt.setDragDecelerationFrictionCoef(0.95f);
        attenChrt.setDrawHoleEnabled(true);
        attenChrt.setHoleColor(R.color.colorGrey);
        attenChrt.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<>();
        yValues.add(new PieEntry(5f,"Absent"));
        yValues.add(new PieEntry(34f,"Present"));
        yValues.add(new PieEntry(12f,"Early Leave"));
        yValues.add(new PieEntry(15f,"Late come"));

        PieDataSet dataset =  new PieDataSet(yValues,"Attemdace");
        dataset.setSliceSpace(3f);
        dataset.setSelectionShift(5f);
        dataset.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData(dataset);
        data.setValueTextSize(10f);
       // data.setValueTextColors("#2E2E2E");
        attenChrt.setData(data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    //Home button(Action bar) onClick event handler
    public void btnGoToHome_onClick(MenuItem item) {
        goToAnotherActivity(this, HomePageActivity.class);
    }

    //Navigate to another activity
    public void goToAnotherActivity(Context currentActivity, Class targetActivity) {
        Intent intentObj = new Intent(currentActivity, targetActivity);
        startActivity(intentObj);
    }
}
