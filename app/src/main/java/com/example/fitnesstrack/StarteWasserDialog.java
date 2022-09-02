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

    private int prevwasser = 0;
    EditText wassereing;



    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View wasserview = inflater.inflate(R.layout.poput_eingabewasser, null);
        builder.setView(wasserview);

        wassereing = wasserview.findViewById(R.id.eingabeWasser);


        builder.setTitle("Gib an wieviel Wasser du getrunken hast")
                .setPositiveButton(R.string.wasserbest, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Wasser wasser;
                        try {

                            wasser = new Wasser(-1, Integer.parseInt(wassereing.getText().toString()));
                            //valueeing = Integer.parseInt(wassereing.getText().toString());


                            //TODO


                        }catch (Exception e){
                            if(wassereing.length() == 0) {
                                //Toast, der den User benachrichtigt, etwas einzugeben
                            }
                        }


                        DatabaseHelper databaseHelper = new DatabaseHelper( );
                        databaseHelper.addOne(wasser); //TODO


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









    public int getPrevwasser() {
        return prevwasser;
    }

    public void setPrevwasser(int prevwasser) {
        this.prevwasser = prevwasser;
    }





}


