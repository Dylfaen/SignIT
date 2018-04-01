package com.example.cesar.signit.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.cesar.signit.Fragments.EditTitleDialog;
import com.example.cesar.signit.Fragments.TitleDialog;
import com.example.cesar.signit.Model.Data;
import com.example.cesar.signit.Model.TrainingProfile;
import com.example.cesar.signit.R;

public class TrainingsListActivity extends AppCompatActivity implements TitleDialog.NoticeDialogListener, EditTitleDialog.EditTitleListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainings_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Log.d("DATA", Data.getInstance().getData().toString());


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new TrainingsListAdapter(Data.getInstance().getData(), this);
        mRecyclerView.setAdapter(mAdapter);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTitleDialog();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void startTitleDialog() {
        DialogFragment newFragment = new TitleDialog();
        newFragment.show(getSupportFragmentManager(), "missiles");
    }


    public void initTrainings() {

    }

    @Override
    public void onDialogEditPositiveClick(DialogFragment dialog) {
        Log.d("FRAGMENT", "Validé");
        String title = ((TitleDialog) dialog).getTitleEditText().getText().toString();
        try {
            Data.getInstance().addTrainingProfile(new TrainingProfile(title));
        } catch(Exception e) {
            Toast.makeText(TrainingsListActivity.this, "Ce nom est déjà pris", Toast.LENGTH_SHORT).show();
        }
        Log.d("DATA", Data.getInstance().getData().toString());

    }

    @Override
    public void onDialogEditNegativeClick(DialogFragment dialog) {
        Log.d("FRAGMENT", "Annulé");
        Toast.makeText(TrainingsListActivity.this, "Annulé", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Log.d("FRAGMENT", "Validé");
        String title = ((TitleDialog) dialog).getTitleEditText().getText().toString();
        try {
            Data.getInstance().addTrainingProfile(new TrainingProfile(title));
        } catch(Exception e) {
            Toast.makeText(TrainingsListActivity.this, "Ce nom est déjà pris", Toast.LENGTH_SHORT).show();
        }
        Log.d("DATA", Data.getInstance().getData().toString());
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Log.d("FRAGMENT", "Annulé");
        Toast.makeText(TrainingsListActivity.this, "Annulé", Toast.LENGTH_SHORT).show();
    }
}
