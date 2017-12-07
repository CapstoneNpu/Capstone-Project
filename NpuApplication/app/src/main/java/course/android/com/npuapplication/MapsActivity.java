package course.android.com.npuapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View v = getLayoutInflater().inflate(R.layout.tryinfowindow,null);
                TextView title = (TextView) v.findViewById(R.id.tv_title);
                TextView desc = (TextView) v.findViewById(R.id.tv_desc);
                title.setText(marker.getTitle());
                desc.setText(marker.getSnippet());
                return v;
            }
        });
        // and move the map's camera to the same location.
        LatLng npuAdminOffice = new LatLng(37.477855, -121.925862);
        LatLng npuEastBuilding = new LatLng(37.481365, -121.925140);
        LatLng npuWestBuilding = new LatLng(37.481219, -121.925641);
        LatLng npuSouthBuilding1 = new LatLng(37.479352, -121.924128);
        LatLng npuSouthBuilding2 = new LatLng(37.478877, -121.923936);
        LatLng npuNorthBuilding = new LatLng(37.486154, -121.928666);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.addMarker(new MarkerOptions().position(npuAdminOffice)
                .title(" NPU").snippet(" Administration Office").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        googleMap.addMarker(new MarkerOptions().position(npuEastBuilding)
                .title(" NPU").snippet(" School of Engineering(EAST Bldg.)").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        googleMap.addMarker(new MarkerOptions().position(npuWestBuilding)
                .title(" NPU").snippet(" Library and Student service center(WEST Bldg.)").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        googleMap.addMarker(new MarkerOptions().position(npuSouthBuilding1)
                .title(" NPU").snippet(" Online Education Center").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        googleMap.addMarker(new MarkerOptions().position(npuSouthBuilding2)
                .title(" NPU").snippet(" School Of Business and Info.Tech & Recreation Center").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        googleMap.addMarker(new MarkerOptions().position(npuNorthBuilding)
                .title(" NPU").snippet(" North Building").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(npuAdminOffice));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(16));
    }
}
