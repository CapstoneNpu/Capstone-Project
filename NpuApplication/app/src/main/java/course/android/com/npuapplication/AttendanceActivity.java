package course.android.com.npuapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
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
    ListView weeklistvew;
    final Context context = this;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        session = new Session(this);

        weeklistvew = new ListView(this);
        String [] weeksList = {"Week1","Week2","Week3","Week4"};
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,R.layout.attendace_week_summry,R.id.txtweek,weeksList);
weeklistvew.setAdapter(adapter);
  //     weeklistvew.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//           @Override
//           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//               ViewGroup viewGroup = (ViewGroup) view;
//               TextView txtwk = (TextView)viewGroup.findViewById(R.id.txtweek);
//              // if(R.id.txtweek.getParent()!=null)
//                   ((ViewGroup)txtwk.getParent()).removeView(txtwk);
//           }
//       });

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
               // Toast.makeText(AttendanceActivity.this,e.toString(),Toast.LENGTH_LONG).show();
                //ViewGroup viewGroup = (ViewGroup) view;
                //((ViewGroup)txtwk.getParent()).removeView(txtwk);
                AlertDialog.Builder builder = new AlertDialog.Builder(AttendanceActivity.this);
               builder.setCancelable(true);
                //builder.setPositiveButton("OK",null);
                builder.setView(weeklistvew);
                AlertDialog dialog = builder.create();

//                final Dialog dialog = new Dialog(context);
//                dialog.setContentView(R.layout.activity_dialog_attendance);
//                dialog.setTitle("Title...");


              dialog.show();
                //dialog.hide();

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
