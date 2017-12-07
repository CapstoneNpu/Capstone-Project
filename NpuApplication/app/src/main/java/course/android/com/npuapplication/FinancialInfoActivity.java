package course.android.com.npuapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.github.andreilisun.swipedismissdialog.SwipeDismissDialog;

public class FinancialInfoActivity extends AppCompatActivity {

    private Session session;
    Button btnOK;
    TextView txtShowDialog;
    SwipeDismissDialog swipeDismissDialog;
    TableLayout financialTableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_info);

        final FinancialItem[] financialItems = {
                new FinancialItem("8/4/2017","00562530", "Payment: $100", "Balance: $75", "Fee: $175"),
                new FinancialItem("7/31/2017","00567680","Payment: $150","Balance: $100","Fee: $50"),
                new FinancialItem("7/27/2017","00564798","Payment: $200","Balance: $0","Fee: $200"),
                new FinancialItem("7/18/2017","00566872","Payment: $500","Balance: $50","Fee: $550"),
                new FinancialItem("6/24/2017","00561716","Payment: $350","Balance: $0","Fee: $350"),
                new FinancialItem("2/23/2017","00561934","Payment: $250","Balance: $0","Fee: $250")
        };

        financialTableLayout = (TableLayout) findViewById(R.id.financial_table);

//        int[] array = {R.id.test_view, R.id.test_view1, R.id.test_view2, R.id.test_view3, R.id.test_view4};
        for (int i = 0; i < financialItems.length; i++) {
            TableRow row = (TableRow)LayoutInflater.from(FinancialInfoActivity.this).inflate(R.layout.financial_row, null);
            ((TextView)row.findViewById(R.id.financial_date)).setText(financialItems[i].getDate());
            TextView tst = (TextView)row.findViewById(R.id.financial_value);
            tst.setText(financialItems[i].getTransactionId());
            financialTableLayout.addView(row);

            tst.setOnClickListener(new FinancialItemOnClickListener(
                    financialItems[i].getPayment(),
                    financialItems[i].getBalance(),
                    financialItems[i].getFee(),
                    getBaseContext(),
                    FinancialInfoActivity.this
            ));

        }
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

}

class FinancialItem {
    private String date;
    private String transactionId;
    private String payment;
    private String balance;
    private String fee;

    FinancialItem(String date, String transactionId, String payment, String balance, String fee) {
        this.date = date;
        this.transactionId = transactionId;
        this.balance = balance;
        this.payment = payment;
        this.fee = fee;
    }

    public String getDate() {
        return this.date;
    }

    public String getTransactionId(){
        return this.transactionId;
    }

    public String getPayment() { return this.payment; }

    public String getBalance() { return this.balance; }

    public String getFee() { return this.fee; }


}

class FinancialItemOnClickListener implements View.OnClickListener
{

    private String payment;
    private String balance;
    private String fee;
    private Context context;
    private Context activityContext;
    public FinancialItemOnClickListener(String payment, String balance, String fee, Context context, Context activityContext) {
        this.payment = payment;
        this.balance = balance;
        this.fee = fee;
        this.context = context;
        this.activityContext = activityContext;
    }

    @Override
    public void onClick(View v)
    {
        View dialog = LayoutInflater.from(context).inflate(R.layout.dialog_success, null);

        final SwipeDismissDialog swipeDismissDialog = new SwipeDismissDialog.Builder(this.activityContext)
                .setView(dialog)
                .build()
                .show();

        Button btnOK = (Button) dialog.findViewById(R.id.btnOK);
        TextView balancetxt = (TextView)dialog.findViewById(R.id.financial_balance);
        TextView paymenttxt = (TextView)dialog.findViewById(R.id.financial_payment);
        TextView feetxt = (TextView)dialog.findViewById(R.id.financial_fee);

        feetxt.setText(this.fee);
        balancetxt.setText(this.balance);
        paymenttxt.setText(this.payment);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(FinancialInfoActivity.this, "Click OK!!", Toast.LENGTH_SHORT).show();

                swipeDismissDialog.dismiss();
            }
        });
    }

};
