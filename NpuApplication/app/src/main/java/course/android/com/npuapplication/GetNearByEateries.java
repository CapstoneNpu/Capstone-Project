package course.android.com.npuapplication;

import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sumirna on 10/11/17.
 */

public class GetNearByEateries extends AsyncTask<Object,String,String> {
    String googleplacesData;
    ListView eatView;
    String url;
    HashMap<String, String> nearbyPlaceList;
    @Override
    protected String doInBackground(Object... params) {
        url = (String)params[1];
        eatView = (ListView)params[2];
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
         CustomAdapter adapter = new CustomAdapter(nearbyPlaceList);
         eatView.setAdapter(adapter);

        //showNearbyPlaces(nearbyPlaceList);
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

            result.setPadding(10, 15, 5, 15);

            return result;
        }
    }
}
