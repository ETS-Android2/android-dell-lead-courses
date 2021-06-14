package br.com.brizidiolauro.lesson02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static br.com.brizidiolauro.lesson02.ThreadsMessages.INICIO_TAREFA;

public class MainActivity extends AppCompatActivity {

    TextView text;
    Button button;
    private Handler handler;
    static int times = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);
        button = findViewById(R.id.button);
        TextViewUtil.setTextview(text);

        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        TextViewUtil.setAsyncText("Inicio Tarefa");
                        TextViewUtil.setAsyncText("Thread " +  msg.arg1);
                        break;
                    case 1:
                        TextViewUtil.setAsyncText("Fim da Tarefa " + msg.arg1);
                        break;
                    default:
                        break;
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();

        button.setOnClickListener(v -> {
            new ThreadComTempoDeExecucao(times * 500, times,handler).start();
            times++;
        });

    }
}