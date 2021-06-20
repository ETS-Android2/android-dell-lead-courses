package br.com.brizidiolauro.oficinaaula01.data.api;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.Random;

public class ThreadListaPedidos extends Thread{
    private Handler handler;
    public ThreadListaPedidos(Handler handler){
        this.handler = handler;
    }
    @Override
    public void run() {
        super.run();
        int pedidos = new Random().nextInt(20);
        Log.i("Pedidos -> ",String.valueOf(pedidos));
        for(int i = 0; i < pedidos; i++){
            try{
                int price = new Random().nextInt(200);
                Thread.sleep(2000);
                Message msg = handler.obtainMessage();
                msg.arg1 = price;
                msg.arg2 = i;
                handler.dispatchMessage(msg);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
