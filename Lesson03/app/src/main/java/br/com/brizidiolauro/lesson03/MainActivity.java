package br.com.brizidiolauro.lesson03;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import br.com.brizidiolauro.lesson03.data.ItemObject;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    private static boolean hideCancelDialog = true;
    private AdapterObject adapter;
    private RecyclerView recyclerView;
    private ConstraintLayout viewDialogCancel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setOnTouchListener(this);
        setUpAdapter();
        setUpVariables();

    }

    private void setUpVariables() {
        viewDialogCancel = findViewById(R.id.activity_main_view_dialog_cancel);
    }

    private void setUpAdapter() {

        ArrayList<ItemObject> array = new ArrayList<>();
        array.add(new ItemObject("ola", getResources().getDrawable(R.drawable.icon)));
        array.add(new ItemObject("Lauro", getResources().getDrawable(R.drawable.icon)));

        adapter = new AdapterObject(array);
        recyclerView = findViewById(R.id.activity_main_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("TouchEvent: ",String.valueOf(event.getAction()));
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.i("TouchEvent: ",String.valueOf(event.getAction()));
        return false;
    }
}