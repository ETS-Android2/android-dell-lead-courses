package br.com.brizidiolauro.chapter02.oficinaaula02.listeners;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class SpeedListener implements SensorEventListener {

    private SpeedCallback callback;

    public SpeedListener(SpeedCallback callback) {
        this.callback = callback;

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        callback.onSensorChanged(event.values[0]);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public interface SpeedCallback {
        void onSensorChanged(float speed);
    }
}
