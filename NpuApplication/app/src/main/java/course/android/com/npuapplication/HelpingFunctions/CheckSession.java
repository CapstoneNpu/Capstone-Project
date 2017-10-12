package course.android.com.npuapplication.HelpingFunctions;

import android.app.Activity;
import android.content.Intent;

import course.android.com.npuapplication.HomePageActivity;
import course.android.com.npuapplication.Session;

/**
 * Created by Bansari on 10/9/2017.
 */

public class CheckSession {

    private static Session session;

    public static String checkSession(Activity thisActivity){
        if (session.getusename().equals(null) || session.getusename().equals("")) {
            Intent intentObj = new Intent(thisActivity, HomePageActivity.class);
            thisActivity.startActivity(intentObj);
        }
        return session.getusename();
    }
}
