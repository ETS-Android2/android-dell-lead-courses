package br.com.brizidiolauro.lesson01;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpVariables();
        setUpListeners();
    }

    private void setUpVariables() {
        btn = findViewById(R.id.btn_second_activity);
    }

    private void setUpListeners() {
        btn.setOnClickListener(v -> {
            startActivity(new Intent(this, SecondActivity.class));
        });
    }
}