package com.example.fitnesstrack;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class StarteWasserDialog extends DialogFragment {

    private int valueeing;
    private int prevwasser = 0;
    EditText wassereing;

    @NonNull
    //Ovverride?
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View wasserview = inflater.inflate(R.layout.poput_eingabewasser, null);
        builder.setView(wasserview);

        wassereing = wasserview.findViewById(R.id.eingabeWasser);


        builder.setTitle("Gib an wieviel Wasser du getrunken hast")
                .setPositiveButton(R.string.wasserbest, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        try {

                            valueeing = Integer.parseInt(wassereing.getText().toString());
                            updateWasser();

                            prevwasser = prevwasser + valueeing;
                            //TODO


                        }catch (NumberFormatException nfe) {

                            //Toast der den User benachrichtigt, nur ganzzahken einzugeben

                        }catch (Exception e){
                            if(wassereing.length() == 0) {
                                //Toast, der den User benachrichtigt, etwas einzugeben
                            }
                        }

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


    public void updateWasser() {

        int wasserfinal = prevwasser + valueeing;

        MainActivity_Tracking.getTextViewWasser().setText(R.string.wasserpref + " / " + wasserfinal);

    }


    public int getValueeing() {

        return valueeing;
    }

    public void setValueeing(int valueeing) {

        this.valueeing = valueeing;
    }

    public int getPrevwasser() {
        return prevwasser;
    }

    public void setPrevwasser(int prevwasser) {
        this.prevwasser = prevwasser;
    }





}


