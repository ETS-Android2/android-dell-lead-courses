package br.com.brizidiolauro.chapter02.oficinaaula02.listeners;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import androidx.annotation.NonNull;

public class GPSListener implements LocationListener {

    private GPSCallback callback;

    public GPSListener(GPSCallback callback) {
        this.callback = callback;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        callback.onChangeLocation(location.getLatitude(), location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    public interface GPSCallback {
        void onChangeLocation(double latitude, double longitude);
    }
}
