package br.com.brizidiolauro.chapter03.lesson01;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import br.com.brizidiolauro.chapter03.R;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_TAKE_PIC = 1;
    private static final int REQUEST_CODE_TAKE_VIDEO = 2;


    private ImageView imageView;

    private Button btnTakePicture;
    private Button btnCaptureVideo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        imageView = findViewById(R.id.main_activity_image_view);
        btnTakePicture = findViewById(R.id.btn_take_picture);
        btnCaptureVideo = findViewById(R.id.btn_take_video);

        getSupportActionBar().hide();


        btnTakePicture.setOnClickListener(v -> {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    startActivityForResult(takePictureIntent, REQUEST_CODE_TAKE_PIC);
                }
        );

        btnCaptureVideo.setOnClickListener(v -> {
            Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

            startActivityForResult(takeVideoIntent, REQUEST_CODE_TAKE_VIDEO);

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_TAKE_PIC && resultCode == RESULT_OK) {

            Bitmap img = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(img);


            showMessage("Foto tirada ! e Salva em ");
        } else if (requestCode == REQUEST_CODE_TAKE_VIDEO && resultCode == RESULT_OK) {
            showMessage("Video gravado pelo Usuario! ");
        } else if (resultCode == RESULT_CANCELED) {
            showMessage("Operação cancelada pelo Usuario! ");
        } else {
            showMessage("Falha na operação! ");
        }
    }

    public void showMessage(String msg) {
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
