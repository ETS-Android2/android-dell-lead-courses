package com.delllead.oficina03;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dellead.oficina03.R;

public class MediaPlayerActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener {

    private VideoView videoView;

    private Button play;
    private Button stop;
    private Button pause;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_player_activity);

        videoView = findViewById(R.id.VideoViewer);

        play = findViewById(R.id.play);
        stop = findViewById(R.id.stop);
        pause = findViewById(R.id.pause);


        videoView.setVideoURI(getVideo());
        videoView.setMediaController(new MediaController(this));


        Log.i("beforeStart()", "beforeStart()");
        videoView.start();
        videoView.setOnPreparedListener(this);

        play.setOnClickListener(v -> {
            if (!videoView.isPlaying()) {
                playVideo();
            }
        });

        pause.setOnClickListener(v -> {
            if (videoView.isPlaying()) {
                pauseVideo();
            }
        });

        stop.setOnClickListener(v -> {
            stopVideo();
        });

    }

    private Uri getVideo() {
        String uriPath = "android.resource://" + getPackageName() + "/raw/" + R.raw.dell;

        Uri uri = Uri.parse(uriPath);
        return uri;
    }

    public void playVideo() {
        videoView.start();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.i("onPrepared", "onPrepared");
        mp.start();
    }

    public void pauseVideo() {
        videoView.pause();
    }

    public void stopVideo() {
        videoView.stopPlayback();
    }
}
