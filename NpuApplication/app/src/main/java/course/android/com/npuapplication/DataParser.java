package course.android.com.npuapplication;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by sumirna on 10/11/17.
 */

public class DataParser {
    private HashMap<String, String> getPlace(JSONObject googlePlaceJson)
    {
        HashMap<String, String> googlePlaceMap = new HashMap<>();
        String placeName = "--NA--";
        String vicinity= "--NA--";
        String latitude= "";
        String longitude="";
        String reference="";

        Log.d("DataParser","jsonobject ="+googlePlaceJson.toString());


        try {
            if (!googlePlaceJson.isNull("name")) {
                placeName = googlePlaceJson.getString("name");
            }
            if (!googlePlaceJson.isNull("vicinity")) {
                vicinity = googlePlaceJson.getString("vicinity");
            }

            latitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude = googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lng");

            reference = googlePlaceJson.getString("reference");
            googlePlaceMap.put(placeName,vicinity);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return googlePlaceMap;

    }

    private HashMap<String, String> getPlaces(JSONArray jsonArray) {
        int count = jsonArray.length();
        HashMap<String, String> placelist = new HashMap<>();
        // HashMap<String, String> placeMap = null;

        for (int i = 0; i < count; i++) {
            try {
                // placeMap = getPlace((JSONObject) jsonArray.get(i));
                //placelist.add(placeMap);
                String placeName = "--NA--";
                String vicinity = "--NA--";
                String latitude = "";
                String longitude = "";
                String reference = "";
                JSONObject googlePlaceJson = (JSONObject) jsonArray.get(i);
                Log.d("DataParser", "jsonobject =" + googlePlaceJson.toString());


                try {
                    if (!googlePlaceJson.isNull("name")) {
                        placeName = googlePlaceJson.getString("name");
                    }
                    if (!googlePlaceJson.isNull("vicinity")) {
                        vicinity = googlePlaceJson.getString("vicinity");
                    }
                    placelist.put(placeName,vicinity);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }return placelist;
    }
    public HashMap<String, String> parse(String jsonData)
    {
        JSONArray jsonArray = null;
        JSONObject jsonObject;

        Log.d("json data", jsonData);

        try {
            jsonObject = new JSONObject(jsonData);
            jsonArray = jsonObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getPlaces(jsonArray);
    }
}