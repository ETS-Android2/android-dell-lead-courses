package br.com.brizidiolauro.chapter02.oficinaaula02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import br.com.brizidiolauro.chapter02.oficinaaula02.listeners.GPSListener;
import br.com.brizidiolauro.chapter02.oficinaaula02.listeners.SpeedListener;

public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private GPSListener gpsListener;

    private SensorManager sensorManager;
    private Sensor speedSensor;

    private SpeedListener speedListener;

    private TextView textLatitude;
    private TextView textLongitude;

    private TextView textSpeed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        gpsListener = new GPSListener(gpsCallback);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        speedSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        speedListener = new SpeedListener(speedCallback);

        sensorManager.registerListener(speedListener, speedSensor,
                SensorManager.SENSOR_DELAY_NORMAL);

        textLatitude = findViewById(R.id.text_latitude);
        textLongitude = findViewById(R.id.text_longitude);

        textSpeed = findViewById(R.id.text_speed);

        requestPermission();
    }

    private GPSListener.GPSCallback gpsCallback = (latitude, longitude) -> {
        textLatitude.setText("Latitude: " + latitude);
        textLongitude.setText("Longitude: " + longitude);
    };

    private SpeedListener.SpeedCallback speedCallback = speed -> {
        textSpeed.setText("Velocidade: " + speed);
    };

    private void requestPermission() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, 0);

            return;
        }
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 0, 0, gpsListener);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 0){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER, 0, 0, gpsListener);
        }
    }
}
