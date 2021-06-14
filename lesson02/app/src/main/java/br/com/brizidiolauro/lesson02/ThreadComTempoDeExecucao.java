package br.com.brizidiolauro.lesson02;

import android.os.Handler;
import android.os.Message;

public class ThreadComTempoDeExecucao extends Thread{

    private int time;
    private int name;
    private Handler handler;
    public ThreadComTempoDeExecucao(int time, int name, Handler handler){
        this.time = time;
        this.name = name;
        this.handler = handler;
    }

    @Override
    public void run() {
        super.run();
        Message msg = handler.obtainMessage();
        msg.what = ThreadsMessages.INICIO_TAREFA;
        msg.arg1 = name;
        handler.dispatchMessage(msg);
        try{

            Thread.sleep(time);
            msg.what = ThreadsMessages.FIM_TAREFA;
            msg.arg1 = name;
            handler.dispatchMessage(msg);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
