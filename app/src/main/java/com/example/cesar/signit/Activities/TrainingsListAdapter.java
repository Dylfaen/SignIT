package com.example.cesar.signit.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cesar.signit.Fragments.TitleDialog;
import com.example.cesar.signit.Model.Data;
import com.example.cesar.signit.Model.TrainingProfile;
import com.example.cesar.signit.R;

import java.util.ArrayList;

/**
 * Created by cesar on 13/03/18.
 */

public class TrainingsListAdapter extends RecyclerView.Adapter<TrainingsListAdapter.ViewHolder> {

    private ArrayList<TrainingProfile> trainingProfiles;
    private Activity activity;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CardView cardView;
        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TrainingsListAdapter(ArrayList<TrainingProfile> myDataset, Activity activity) {
        trainingProfiles = myDataset;
        this.activity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TrainingsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final TrainingProfile profile = trainingProfiles.get(position);

        TextView title = (TextView) holder.cardView.findViewById(R.id.title);
        TextView status = (TextView) holder.cardView.findViewById(R.id.status);
        ImageButton start_button = (ImageButton) holder.cardView.findViewById(R.id.start_button);
        ImageButton edit_button = (ImageButton) holder.cardView.findViewById(R.id.edit_button);
        ImageButton delete_button = (ImageButton) holder.cardView.findViewById(R.id.delete_button);

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrainingsListAdapter.this.activity, TrainingActivity.class);
                TrainingsListAdapter.this.activity.startActivity(intent);
            }
        });

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new TitleDialog();
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data.getInstance().removeTrainingProfile(position);
                notifyItemRemoved(position);
            }
        });



        int signatureCount = profile.getSignatures().size();

        if(signatureCount < 30) {
            status.setTextColor(Color.DKGRAY);
        }

        title.setText(profile.getTitle());
        status.setText(profile.getSignatures().size() + "/30");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return trainingProfiles.size();
    }


}
