package com.example.cesar.signit.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.cesar.signit.R;

/**
 * Created by cesar on 13/03/18.
 */

public class EditTitleDialog extends DialogFragment {

    private EditText titleEditText;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();

        View view = layoutInflater.inflate(R.layout.fragment_profile_title, null);

        this.titleEditText = (EditText) view.findViewById(R.id.profile_title);


        builder.setPositiveButton(R.string.validate, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogEditPositiveClick(EditTitleDialog.this);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogEditNegativeClick(EditTitleDialog.this);
                    }
                })
                .setView(view);
        // Create the AlertDialog object and return it

        return builder.create();
    }

    public interface EditTitleListener {
        public void onDialogEditPositiveClick(DialogFragment dialog);
        public void onDialogEditNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    EditTitleListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeCardListener
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeCardListener so we can send events to the host
            mListener = (EditTitleListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement NoticeCardListener");
        }
    }

    public EditText getTitleEditText() {
        return titleEditText;
    }
}