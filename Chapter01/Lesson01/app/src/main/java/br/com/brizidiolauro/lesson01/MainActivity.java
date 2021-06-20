package br.com.brizidiolauro.lesson01;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button button;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpVariables();

        setUpListeners();
    }

    private void setUpListeners() {
        button.setOnClickListener(v -> {
            startActivity(new Intent(this, SlowActivity.class));
        });
    }

    private void setUpVariables() {
        button = findViewById(R.id.activity_main_button_start_task);
    }


}
