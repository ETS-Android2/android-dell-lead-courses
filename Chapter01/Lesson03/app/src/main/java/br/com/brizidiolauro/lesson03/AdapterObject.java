package br.com.brizidiolauro.lesson03;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.com.brizidiolauro.lesson03.data.ItemObject;

public class AdapterObject extends RecyclerView.Adapter<AdapterObject.ObjectViewHolder> {

    private ArrayList<ItemObject> itemObjectList;


    public AdapterObject(ArrayList<ItemObject> list){
        this.itemObjectList = list;
    }

    @NonNull
    @Override
    public ObjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_object,parent,false);
        return new ObjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ObjectViewHolder holder, int position) {
        holder.bind(itemObjectList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemObjectList.size();
    }

    class ObjectViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView icon;

        public ObjectViewHolder(@NonNull View itemView) {
            super(itemView);
            setUpVariables(itemView);
        }

        private void setUpVariables(@NonNull View itemView) {
            textView = itemView.findViewById(R.id.item_object_text);
            icon = itemView.findViewById(R.id.item_object_icon);
        }

        public void bind(ItemObject item){
            textView.setText(item.getFilename());
            icon.setImageDrawable(item.getIcon());
        }
    }
}
