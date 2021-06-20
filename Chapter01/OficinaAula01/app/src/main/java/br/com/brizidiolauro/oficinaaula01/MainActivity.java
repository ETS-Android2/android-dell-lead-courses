package br.com.brizidiolauro.oficinaaula01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import br.com.brizidiolauro.oficinaaula01.adapter.PedidosAdapter;
import br.com.brizidiolauro.oficinaaula01.data.api.ThreadListaPedidos;
import br.com.brizidiolauro.oficinaaula01.data.model.Pedido;

public class MainActivity extends AppCompatActivity {
    private Handler handler;
    private PedidosAdapter adapter;

    private RecyclerView recyclerView;

    private Button btnClearList;
    private ImageView updateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpVariables();
        setUpListeners();
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                recyclerView.post(() -> {
                    adapter.setLastPedido(new Pedido("Pedido " + (msg.arg2 + 1), Double.valueOf(msg.arg1)));
                });

            }
        };
    }

    private void setUpListeners() {
        btnClearList.setOnClickListener(v -> {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setView(R.layout.dialog_cancel)
                    .create();
            dialog.show();


            dialog.findViewById(R.id.dialog_cancel_btn_yes).setOnClickListener(view -> {
                adapter.clearPedidos();
                dialog.dismiss();
            });

            dialog.findViewById(R.id.dialog_cancel_btn_no).setOnClickListener(view -> {
                dialog.dismiss();
            });

        });


        updateList.setOnClickListener(v -> {
            new ThreadListaPedidos(handler).start();
        });

    }

    private void setUpVariables() {

        ArrayList<Pedido> pedidos = new ArrayList<>();
        pedidos.add(new Pedido("Ola", 23.00));
        pedidos.add(new Pedido("Dois", 23.00));
        adapter = new PedidosAdapter(new ArrayList<>());
        recyclerView = findViewById(R.id.activity_main_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        updateList = findViewById(R.id.icon);
        btnClearList = findViewById(R.id.btn_clear_list);


    }
}