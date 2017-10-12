package course.android.com.npuapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SocialMediaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);
    }

    public void startBrowserActivity(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    public void onFbButtonClick(View view) {
        startBrowserActivity("https://www.facebook.com/NPUadmissions/");
    }

    public void onInstagramButtonClick(View view) {
        startBrowserActivity("http://instagram.com/npugetinvolved");
    }

    public void onLinkedInButtonClick(View view) {
        startBrowserActivity("https://www.linkedin.com/school/232915/");
    }


    public void onTwitterButtonClick(View view) {
        startBrowserActivity("https://twitter.com/npuadmissions");
    }

    public void onYoutubeButtonClick(View view) {
        startBrowserActivity("https://www.youtube.com/channel/UCQSPTI7-auYyIuT7cXap0vw");
    }


    public void onNpuButtonClick(View view) {
        startBrowserActivity("http://npu.edu/");

    }
}
