package br.com.brizidiolauro.chapter02.lesson02;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Lesson 02 - Location Provider");

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        makeRequestPermission();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void makeRequestPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,

                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION
                    },

                    REQUEST_LOCATION_PERMISSION);
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);


        Location lastKnowLocation = (Location) locationManager
                .getLastKnownLocation(LocationManager.GPS_PROVIDER);

        onLocationChanged(lastKnowLocation);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_LOCATION_PERMISSION) {

            if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Dialog dialog = new AlertDialog.Builder(this)
                        .setView(R.layout.dialog_not_grant_location)
                        .create();

                dialog.show();
                new Handler().postDelayed(
                        dialog::dismiss, 2000);
            }

        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Toast.makeText(this, "Longitude: "
                + location.getLatitude() + "\nLatitude: "
                + location.getLatitude(), Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        if (provider.equals(LocationManager.GPS_PROVIDER)) {
            Log.i("GPSProvider -> ", "Enabled");
        }
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        if (provider.equals(LocationManager.GPS_PROVIDER)) {
            Log.i("GPSProvider -> ", "Disabled");
        }
    }
}