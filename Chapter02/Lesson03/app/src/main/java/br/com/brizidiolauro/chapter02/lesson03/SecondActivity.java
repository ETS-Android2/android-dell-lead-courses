package br.com.brizidiolauro.chapter02.lesson03;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SecondActivity extends AppCompatActivity implements LocationListener {
    private final int REQUEST_CODE_PERMISSION = 1;

    private LocationManager locationManager;

    private Sensor sensorAceleremeter;
    private Sensor sensorLuminosity;

    private SensorManager sensorManager;

    private AcelerometerListener acelerometerListener;
    private LuminosityListener luminosityListener;

    private TextView textViewSensorAc_X;
    private TextView textViewSensorAc_Y;
    private TextView textViewSensorAc_Z;

    private TextView textViewSensorLux;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        luminosityListener = new LuminosityListener(luminosityCallback);
        acelerometerListener = new AcelerometerListener(acelerometerCallback);

        sensorAceleremeter = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorLuminosity = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        sensorManager.registerListener(acelerometerListener,
                sensorAceleremeter,
                SensorManager.SENSOR_DELAY_NORMAL);

        sensorManager.registerListener(luminosityListener,
                sensorLuminosity,
                SensorManager.SENSOR_DELAY_NORMAL);

        requestPermissions();

        setUpVariables();

    }

    private void setUpVariables() {
        textViewSensorAc_X = findViewById(R.id.text_sensor_x);
        textViewSensorAc_Y = findViewById(R.id.text_sensor_y);
        textViewSensorAc_Z = findViewById(R.id.text_sensor_z);

        textViewSensorLux = findViewById(R.id.sensor_lum_x);
    }

    private AcelerometerListener.AcelerometerCallback acelerometerCallback =
            (x, y, z) -> {
                textViewSensorAc_X.setText("Sensor X: " + x);
                textViewSensorAc_Y.setText("Sensor Y: " + y);
                textViewSensorAc_Z.setText("Sensor Z: " + z);
            };

    private LuminosityListener.LuminosityCallback luminosityCallback =
            lux -> {
                textViewSensorLux.setText("Sensor Luminosidade: " + lux);
            };


    private void requestPermissions() {

        //Verify Permissions Contacts and Locations
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                ||
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_CONTACTS)
                        != PackageManager.PERMISSION_GRANTED
        ) {

            //Request Permissions
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.READ_CONTACTS
                    }, REQUEST_CODE_PERMISSION);
            return;
        }

        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 0, 0,
                this);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Toast.makeText(this, "Longitude: " + location.getLongitude() + "\n" +
                "Latitude: " + location.getLatitude(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        Log.i("Provider -> ","Enabled");
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        Log.i("Provider -> ","Disabled");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(acelerometerListener);
        sensorManager.unregisterListener(luminosityListener);
    }
}
