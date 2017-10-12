package course.android.com.npuapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class NearbyEateriesActivity extends AppCompatActivity {
    ListView eatView;
    GetNearByEateries getNearbyPlacesData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_eateries);

        Object dataTransfer[] = new Object[2];
        getNearbyPlacesData = new GetNearByEateries();
        String resturant = "restuarant";
        String url = getUrl(37.477855, -121.925862, resturant);
        dataTransfer[1] = url;
        getNearbyPlacesData.execute(dataTransfer);

        eatView = (ListView)findViewById(R.id.eateriesView);
        CustomAdapter adapter = new CustomAdapter(getNearbyPlacesData.nearbyPlaceList);
        eatView.setAdapter(adapter);

    }

    private String getUrl(double latitude , double longitude , String nearbyPlace)
    {
        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location="+latitude+","+longitude);
        googlePlaceUrl.append("&radius="+500);
        googlePlaceUrl.append("&type="+nearbyPlace);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key="+"AIzaSyDufrIhHMjvNX3A3V7jz6XDH_3P_FNk-jw");

        Log.d("MapsActivity", "url = "+googlePlaceUrl.toString());

        return googlePlaceUrl.toString();
    }

    class CustomAdapter extends BaseAdapter {
        private final ArrayList mData;
        public CustomAdapter(Map<String, String> map) {
            mData = new ArrayList();
            mData.addAll(map.entrySet());
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Map.Entry<String, String> getItem(int position) {
            return (Map.Entry) mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO implement you own logic with ID
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final View result;

            if (convertView == null) {
                result = LayoutInflater.from(parent.getContext()).inflate(R.layout.customlistview, parent, false);
            } else {
                result = convertView;
            }

            Map.Entry<String, String> item = getItem(position);

            // TODO replace findViewById by ViewHolder
            ((TextView) result.findViewById(android.R.id.text1)).setText(item.getKey());
            ((TextView) result.findViewById(android.R.id.text2)).setText(item.getValue());

            return result;
        }
    }

}
