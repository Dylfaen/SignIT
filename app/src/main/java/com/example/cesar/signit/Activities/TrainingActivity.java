package com.example.cesar.signit.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cesar.signit.Helpers.Writer;
import com.example.cesar.signit.Model.Data;
import com.example.cesar.signit.Model.Signature;
import com.example.cesar.signit.Model.Stroke;
import com.example.cesar.signit.Model.TrainingProfile;
import com.example.cesar.signit.R;
import com.github.gcacace.signaturepad.views.SignaturePad;

import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class TrainingActivity extends AppCompatActivity {

    private SignaturePad mSignaturePad;

    private Button mClearButton;
    private Button mSaveButton;

    private ArrayList<Stroke> strokes;

    public int currentStrokeNumber;
    private long firstStartTime;

    private long startTime;
    private long stopTime;

    private int requestCode;
    private int grantResults[];

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        this.strokes = new ArrayList<>();

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
                Stroke stroke = new Stroke(TrainingActivity.this.startTime, TrainingActivity.this.stopTime);
                TrainingActivity.this.strokes.add(stroke);

            }

            @Override
            public void onClear() {

            }
        });


        Button button = findViewById(R.id.next_signature_button);

        this.updateCount();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = TrainingActivity.this.mSignaturePad.getSignatureBitmap();
                String filename = "sign_" + System.currentTimeMillis() + ".png";
                FileOutputStream fileOutputStream = null;

                File file = new File(Writer.getPublicAlbumStorageDir("data"), filename);

                try {
                    fileOutputStream = new FileOutputStream(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                bitmap.compress(CompressFormat.PNG, 100, fileOutputStream);

                Signature signature = new Signature(filename);
                signature.setStrokes((ArrayList<Stroke>)TrainingActivity.this.strokes.clone());


                int profileIndex = getIntent().getIntExtra("profileId", 0);

                TrainingProfile profile = Data.getInstance().getData().get(profileIndex);

                profile.add(signature);

                try {
                    Data.saveData();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                TrainingActivity.this.updateCount();

                TrainingActivity.this.clear();

            }
        });

        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        requestPermissions(permissions, 1);


        onRequestPermissionsResult(requestCode,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},grantResults);

    }

    private void updateCount() {

        final TextView counter = findViewById(R.id.signature_count);

        int profileIndex = getIntent().getIntExtra("profileId", 0);


        TrainingProfile profile = Data.getInstance().getData().get(profileIndex);
        counter.setText(profile.getSignatures().size() + "/30");

    }

    private void clear() {
        mSignaturePad.clear();
        this.strokes.clear();
        this.currentStrokeNumber = 0;
        this.firstStartTime = 0;
        this.startTime = 0;
        this.stopTime = 0;
    }

    public long signatureTime() {
        return this.stopTime - this.firstStartTime;
    }

    @Override // android recommended class to handle permissions
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.d("permission","granted");
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.uujm
                    Toast.makeText(TrainingActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();

                    //app cannot function without this permission for now so close it...
                    onDestroy();
                }
                return;
            }

            // other 'case' line to check fosr other
            // permissions this app might request
        }

    }
}
