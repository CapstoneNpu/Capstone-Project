package course.android.com.npuapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import course.android.com.npuapplication.Encryption.EncryptionAlgorithm;

public class LogInActivity extends AppCompatActivity {

    //define components
    private EditText userNameEditText;
    private EditText passwordEditText;
    private TextView txtViewErrorMsgLogin;

    //define variables
    private String encryptedPassword;
    private EncryptionAlgorithm encryptionAlgorithmObj;
    private DataSnapshot userLoginDataSnapObj;
    private Session session;


    //define Animation variables
    private Animation animation;

    //firebase reference objects
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        //class objects creation
        encryptionAlgorithmObj = new EncryptionAlgorithm();
        session = new Session(this);

        //component reference creation
        userNameEditText = (EditText) findViewById((R.id.edit_text_username_login));
        passwordEditText = (EditText) findViewById(R.id.edit_text_password_login);
        txtViewErrorMsgLogin = (TextView) findViewById(R.id.txtview_login_error_msg_id);

        //set error message textview invisible on activity creation
        txtViewErrorMsgLogin.setVisibility(View.INVISIBLE);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);

        //firebase database reference object
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userLoginDataSnapObj = dataSnapshot.child("UserList");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //To display Toolbar (Home Button)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_toolbar, menu);
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

    //Login button onClick event handler
    public void btn_login_onClick(View view) throws Exception {
        encryptedPassword = "";
        boolean errorFlag = false;

        if(!checkUserName()){
            userNameEditText.setAnimation(animation);
            userNameEditText.startAnimation(animation);
            userNameEditText.clearAnimation();
            errorFlag = true;
        }

        if(!checkPassword()){
            passwordEditText.setAnimation(animation);
            passwordEditText.startAnimation(animation);
            passwordEditText.clearAnimation();
            errorFlag = true;
        }

        if (errorFlag){
            return;
        }
        encryptedPassword = encryptionAlgorithmObj.encrypt(userNameEditText.getText().toString(), passwordEditText.getText().toString());

        if (userLoginDataSnapObj != null && userLoginDataSnapObj.child(encryptedPassword).hasChildren()) {
            txtViewErrorMsgLogin.setVisibility(View.INVISIBLE);
            session.setusename(encryptedPassword);

            goToAnotherActivity(LogInActivity.this, CurrentSemsterCourseListActivity.class);
        }
        else{
            txtViewErrorMsgLogin.setVisibility(View.VISIBLE);
        }
    }

    private boolean checkUserName(){
        if (userNameEditText.getText().toString().trim().isEmpty()){
            userNameEditText.setError("Please enter Username");
            return false;
        }
        return true;
    }

    private boolean checkPassword(){
        if (passwordEditText.getText().toString().trim().isEmpty()){
            passwordEditText.setError("Please enter Password");
            return false;
        }
        return true;
    }
}
