package course.android.com.npuapplication;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by sumirna on 10/11/17.
 */

public class GetNearByEateries extends AsyncTask<Object,String,String> {
    String googleplacesData;
    String url;
    HashMap<String, String> nearbyPlaceList;
    @Override
    protected String doInBackground(Object... params) {
        url = (String)params[1];
        DownloadUrl downloadUrl = new DownloadUrl();
        try {
            googleplacesData = downloadUrl.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return googleplacesData;
    }

    @Override
    protected void onPostExecute(String s) {

        DataParser parser = new DataParser();
        nearbyPlaceList = parser.parse(s);
        Log.d("nearbyplacesdata","called parse method");
        //showNearbyPlaces(nearbyPlaceList);
    }
}
