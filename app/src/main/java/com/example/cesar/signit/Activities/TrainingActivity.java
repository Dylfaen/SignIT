package com.example.cesar.signit.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.cesar.signit.R;
import com.github.gcacace.signaturepad.views.SignaturePad;

import java.util.ArrayList;

public class TrainingActivity extends AppCompatActivity {

    private SignaturePad mSignaturePad;

    private Button mClearButton;
    private Button mSaveButton;

    private ArrayList<Long> strokesTimes;

    public int currentStrokeNumber;
    private long firstStartTime;

    private long startTime;
    private long stopTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        this.strokesTimes = new ArrayList<>();

        this.currentStrokeNumber = 0;
        this.startTime = 0;
        this.stopTime = 0;


        this.firstStartTime = 0;

        mSignaturePad = (SignaturePad) findViewById(R.id.signature_pad);
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
                TrainingActivity.this.startTime = System.currentTimeMillis();


                if(TrainingActivity.this.currentStrokeNumber == 0) {
                    TrainingActivity.this.firstStartTime = TrainingActivity.this.startTime;
                }

            }

            @Override
            public void onSigned() {
                TrainingActivity.this.stopTime = System.currentTimeMillis();
                TrainingActivity.this.currentStrokeNumber++;
                TrainingActivity.this.strokesTimes.add(TrainingActivity.this.stopTime - TrainingActivity.this.startTime);
                Toast.makeText(TrainingActivity.this, "total time : " + TrainingActivity.this.signatureTime() + "ms", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onClear() {

            }
        });

    }

    public long signatureTime() {
        return this.stopTime - this.firstStartTime;
    }

    public long totalStrokes() {
        long sum = 0;
        for (Long strokeTime: this.strokesTimes) {
            sum += strokeTime;
        }

        return sum;

    }
}
