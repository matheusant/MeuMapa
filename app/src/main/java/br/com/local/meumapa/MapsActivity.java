package br.com.local.meumapa;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

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
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Add a marker in Parque Ecológico and move the camera

        LatLng parqueEco = new LatLng(-23.70041250143166, -46.75122587879555);


        //Marcador
        mMap.addMarker(new MarkerOptions().
                position(parqueEco).
                title("Parque Ecológico"));

        // Posicionamento/Zoom da câmera
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(parqueEco, 15));

        //Evento de clique
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                double latitude, longitude;

                latitude = latLng.latitude;
                longitude = latLng.longitude;

                Toast.makeText(MapsActivity.this,
                        "Latitude: " + latitude + "\nLongitude: " + longitude,
                        Toast.LENGTH_SHORT).show();

                mMap.addMarker(new MarkerOptions().
                        position(latLng).
                        title("Você clicou aqui!!!"));

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            }
        });


    }
}