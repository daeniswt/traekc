package com.example.fitnesstrack;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

public class StarteWasserDialog extends DialogFragment {

    EditText wassereing;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View wasserview = inflater.inflate(R.layout.poput_eingabewasser, null);
        builder.setView(wasserview);

        builder.setTitle("Gib an wieviel Wasser du getrunken hast")
                .setPositiveButton(R.string.wasserbest, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        wassereing = wasserview.findViewById(R.id.eingabeWasser);

                        int valueeing = Integer.parseInt(wassereing.getText().toString());
                        MainActivity_Tracking.getTextViewWasser().setText(Integer.toString(valueeing));
                        //TODO: addieren auf den init
                    }
                })
                .setNegativeButton(R.string.wassercancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // canceln des dialogs und keine änderung!
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}


