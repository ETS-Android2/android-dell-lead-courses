package br.com.brizidiolauro.chapter02.lesson03;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class AcelerometerListener implements SensorEventListener {

    private float x,y,z;
    private AcelerometerCallback callback;

    public AcelerometerListener(AcelerometerCallback callback){
        this.callback = callback;

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];

        callback.onEvent(x,y,z);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    interface AcelerometerCallback {
        void onEvent(float x,float y, float z);
    }
}
