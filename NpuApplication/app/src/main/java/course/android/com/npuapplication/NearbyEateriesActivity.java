package course.android.com.npuapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

public class NearbyEateriesActivity extends AppCompatActivity {
    ListView eatView;
    GetNearByEateries getNearbyPlacesData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_eateries);

        Object dataTransfer[] = new Object[3];
        getNearbyPlacesData = new GetNearByEateries();
        String resturant = "restaurant";
        String url = getUrl(37.477855, -121.925862, resturant);
        eatView = (ListView)findViewById(R.id.eateriesView);
        dataTransfer[1] = url;
        dataTransfer[2] = eatView;
        getNearbyPlacesData.execute(dataTransfer);
    }

    private String getUrl(double latitude , double longitude , String nearbyPlace) {
        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location=" + latitude + "," + longitude);
        googlePlaceUrl.append("&radius=" + 500);
        googlePlaceUrl.append("&type=" + nearbyPlace);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key=" + "AIzaSyB00iUkei1AMYqhE8x3b2a2sxP_Qe_m7D8");

        Log.d("MapsActivity", "url = " + googlePlaceUrl.toString());

        return googlePlaceUrl.toString();
    }

}
