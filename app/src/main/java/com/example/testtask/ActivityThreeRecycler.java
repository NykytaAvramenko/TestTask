package com.example.testtask;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testtask.db.ListItemsDbMediator;
import com.example.testtask.db.ListItemsDbMediatorInterface;
import com.example.testtask.model.ListItemModel;
import com.example.testtask.ux.DialogAddItem;
import com.example.testtask.ux.RecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ActivityThreeRecycler extends AppCompatActivity implements DialogAddItem.AddItemDialogListener {


    private RecyclerView mRecycler;
    private RecyclerAdapter mAdapter;

    private ListItemsDbMediatorInterface mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);


        mRecycler = findViewById(R.id.recyclerView);
        mDatabase = ListItemsDbMediator.getListItemsDb(this);
        mAdapter = new RecyclerAdapter(mDatabase.getAllListItems());

        prepareRecyclerView();
        prepareFloatingActionButton();
    }


    private void prepareRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(layoutManager);
        mRecycler.setAdapter(mAdapter);
    }


    private void prepareFloatingActionButton() {
        FloatingActionButton fab = findViewById(R.id.floating_action_button);
        fab.setOnClickListener(view -> openDialog());
    }

    public void openDialog() {
        DialogAddItem dialogAddItem = new DialogAddItem();
        dialogAddItem.show(getSupportFragmentManager(), "DialogAddItem tag");
    }


    @Override
    public void onDataProvided(String iconUrl, String description) {
        mDatabase.addToDB(ListItemModel.getNewListItem(iconUrl, description));
        mAdapter.swapCursor(mDatabase.getAllListItems());
    }
}