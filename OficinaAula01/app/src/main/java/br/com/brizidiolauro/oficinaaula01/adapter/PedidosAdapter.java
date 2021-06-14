package br.com.brizidiolauro.oficinaaula01.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.com.brizidiolauro.oficinaaula01.R;
import br.com.brizidiolauro.oficinaaula01.data.model.Pedido;

public class PedidosAdapter extends RecyclerView.Adapter<PedidosAdapter.PedidosViewHolder> {
    private ArrayList<Pedido> pedidos;

    public PedidosAdapter(ArrayList<Pedido> list) {
        this.pedidos = list;
    }


    public void setLastPedido(Pedido pedido) {
        pedidos.add(pedido);
        int position = pedidos.lastIndexOf(pedido);
        notifyItemChanged(position);
    }

    public void clearPedidos() {
        pedidos.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PedidosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedidos, parent, false);
        return new PedidosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidosAdapter.PedidosViewHolder holder, int position) {
        holder.bind(pedidos.get(position));
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }


    class PedidosViewHolder extends RecyclerView.ViewHolder {
        View pedidosView;
        TextView description;
        TextView price;

        public PedidosViewHolder(@NonNull View itemView) {
            super(itemView);
            pedidosView = itemView;
            description = pedidosView.findViewById(R.id.item_pedidos_description);
            price = pedidosView.findViewById(R.id.item_pedidos_price);
        }

        public void bind(Pedido pedido) {
            description.setText(pedido.getName());
            price.setText("R$ " + pedido.getValue());

        }
    }
}
