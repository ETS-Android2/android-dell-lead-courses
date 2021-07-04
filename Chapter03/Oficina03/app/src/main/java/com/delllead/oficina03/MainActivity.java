package com.delllead.oficina03;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dellead.oficina03.R;

public class MainActivity extends AppCompatActivity {

    private Button btnGoToSecondScreen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        btnGoToSecondScreen = findViewById(R.id.main_activity_button);

        btnGoToSecondScreen.setOnClickListener(v -> {
            startActivity(new Intent(this, MediaPlayerActivity.class));
        });

    }
}
