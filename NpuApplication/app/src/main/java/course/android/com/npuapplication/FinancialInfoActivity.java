package course.android.com.npuapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import com.github.andreilisun.swipedismissdialog.SwipeDismissDialog;

public class FinancialInfoActivity extends AppCompatActivity {

    Button btnOK;
    TextView txtShowDialog;
    SwipeDismissDialog swipeDismissDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_info);

        int[] array = {R.id.test_view, R.id.test_view1, R.id.test_view2, R.id.test_view3};
        for (int i = 0; i < array.length; i++) {
            final TextView tst = (TextView) findViewById(array[i]);
            tst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View dialog = LayoutInflater.from(getBaseContext()).inflate(R.layout.dialog_success, null);

                    swipeDismissDialog = new SwipeDismissDialog.Builder(FinancialInfoActivity.this)
                            .setView(dialog)
                            .build()
                            .show();

                    btnOK = (Button) dialog.findViewById(R.id.btnOK);
                    btnOK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Toast.makeText(FinancialInfoActivity.this, "Click OK!!", Toast.LENGTH_SHORT).show();
                            swipeDismissDialog.dismiss();
                        }
                    });
                }
            });

        }
    }
}