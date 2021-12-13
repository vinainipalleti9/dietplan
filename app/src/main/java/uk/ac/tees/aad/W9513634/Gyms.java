package uk.ac.tees.aad.W9513634;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Gyms extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyms);
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

        SharedPreferences sh = getSharedPreferences("location", Context.MODE_PRIVATE);

        String lat = sh.getString("latitude", "");
        String lng = sh.getString("longitude", "");
        Toast.makeText(getApplicationContext(),lat+lng,Toast.LENGTH_LONG).show();

        mMap = googleMap;

        mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng))).title("current Location"));

        mMap.addMarker(new MarkerOptions().position(new LatLng(54.572811, -1.235541)).title("gym"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(54.574123, -1.244610)).title("gym"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(54.557918, -1.257461)).title("gym"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(54.553307, -1.247160)).title("gym"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(54.570496, -1.226224)).title("gym"));


        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(lat), Double.parseDouble(lng)),14));

    }
}
