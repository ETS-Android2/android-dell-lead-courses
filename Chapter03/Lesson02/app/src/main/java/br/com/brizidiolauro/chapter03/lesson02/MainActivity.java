package br.com.brizidiolauro.chapter03.lesson02;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener {

    private MediaPlayer player;

    private Button btnPlayAudio;
    private Button btnPlayVideo;

    private Button btnPlay;
    private Button btnStop;
    private Button btnPause;

    private Uri uri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        getSupportActionBar().hide();


        setUpVariables();
        setUpListeners();
    }

    private void setUpVariables() {


        uri = Uri.parse("https://65381g.ha.azioncdn.net/9/9/5/3/onlinepontocom-01-mais-uma-vez.mp3");
        player = new MediaPlayer();

        try {
            player.setDataSource(this, uri);
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setOnPreparedListener(this);
            player.prepareAsync();


        } catch (Exception e) {
            e.printStackTrace();
        }


        btnPlayAudio = findViewById(R.id.main_activity_play_audio);

        btnPlay = findViewById(R.id.main_activity_play);
        btnPause = findViewById(R.id.main_activity_pause);
        btnStop = findViewById(R.id.main_activity_stop);
    }

    private void setUpListeners() {
        btnPlayAudio.setOnClickListener(v -> {

            try {
                player.setDataSource(this, uri);
                player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                player.setOnPreparedListener(this);
                player.prepareAsync();


            } catch (Exception e) {
                e.printStackTrace();
            }

            executeAudio();
        });

        btnPlay.setOnClickListener(v -> {
            executeAudio();
        });

        btnPause.setOnClickListener(v -> {
            pauseAudio();
        });

        btnStop.setOnClickListener(v -> {
            stopAudio();
        });
    }

    private void pauseAudio() {
        if (player.isPlaying())
            player.pause();
    }

    private void stopAudio() {
        if (player.isPlaying())
            player.stop();
    }

    private void executeAudio() {
        if (!player.isPlaying())
            player.start();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        if(!player.isPlaying())
            player.start();
    }
}
