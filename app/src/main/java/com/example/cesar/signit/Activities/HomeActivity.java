package com.example.cesar.signit.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cesar.signit.Model.Data;
import com.example.cesar.signit.R;

public class HomeActivity extends AppCompatActivity {

    private Button trainButton;
    private Button estimateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        this.trainButton = (Button) findViewById(R.id.train_button);
        this.estimateButton = (Button) findViewById(R.id.estimate_button);



        this.trainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTrainingsListActivity();
            }
        });

        this.estimateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTrainingActivity();
            }
        });
    }

    public void startTrainingsListActivity() {
        Intent intent = new Intent(this, TrainingsListActivity.class);
        startActivity(intent);


    }

    public void startTrainingActivity() {
        Intent intent = new Intent(this, TrainingActivity.class);
        startActivity(intent);
    }
}
