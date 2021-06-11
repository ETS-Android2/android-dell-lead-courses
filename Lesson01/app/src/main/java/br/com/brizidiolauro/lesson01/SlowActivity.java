package br.com.brizidiolauro.lesson01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SlowActivity extends AppCompatActivity {


    private TextView textView;
    private View viewLoading;
    private Button stopButton;
    private ConstraintLayout layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slow);
        setUpVariables();
        setUpThreads();
    }

    private void setUpThreads() {
        new Thread(() -> {
            try {
                layout.post(() -> {
                    try{
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    viewLoading.setVisibility(View.VISIBLE);
                    stopButton.setVisibility(View.VISIBLE);
                });
                for (int i = 1; i <= 10; i++) {
                    Thread.sleep(1000);
                    final int seconds = i;
                    textView.post(() -> {
                        textView.setText("Tempo decorrido: " + seconds + " segundos");
                    });
                }

                //After 10 seconds

                layout.post(() -> {
                    viewLoading.setVisibility(View.GONE);
                    stopButton.setEnabled(false);
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();


    }

    private void setUpVariables() {
        textView = findViewById(R.id.activity_slow_textview);
        viewLoading = findViewById(R.id.activity_slow_loading);
        stopButton = findViewById(R.id.activity_slow_button_stop);
        layout = findViewById(R.id.layout);
    }
}
