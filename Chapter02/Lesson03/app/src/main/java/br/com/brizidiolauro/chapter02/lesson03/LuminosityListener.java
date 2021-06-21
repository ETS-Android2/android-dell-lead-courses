package br.com.brizidiolauro.chapter02.lesson03;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class LuminosityListener implements SensorEventListener {

    private float lux;
    private LuminosityCallback callback;

    public LuminosityListener(LuminosityCallback callback){
        this.callback = callback;

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        lux = event.values[0];

        callback.onEvent(lux);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    interface LuminosityCallback {
        void onEvent(float lux);
    }
}
