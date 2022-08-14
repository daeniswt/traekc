package com.example.fitnesstrack;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity_Tracking extends AppCompatActivity {

    //variablen der Textanzeige
    private int bewegerg;
    public int mahlzeiterg;
    private int finaleswassererg;
    private int wassererg;
    private int eingtrinken;


    //Dialoge
    Dialog AuswahlDialog;

    Dialog MahlzeitDialog;

    //Buttons im Hauptdialog
    private Button moderat;
    private Button intensiv;
    private Button trinken;
    private Button mahlzeit;

    //Fortschrittsbalken
    private ProgressBar fortWasser;
    private ProgressBar fortMahlzeit;
    private ProgressBar fortBewegung;

    //Texte über den Fortschrittsbalken
    private TextView TextViewMahlzeiten;
    private TextView TextViewTrinken;


    private static TextView TextViewWasser;
    private TextView TextViewBewegung;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AuswahlDialog = new Dialog(this);


        moderat = findViewById(R.id.moderat);
        intensiv = findViewById(R.id.intensiv);
        trinken = findViewById(R.id.trinken);
        mahlzeit = findViewById(R.id.nahrung);

        //INIT
        int wassererg = 0;
        int mahlzeiterg = 0;

        TextViewMahlzeiten = findViewById(R.id.wertanzeigeMahlzeiten);
        TextViewMahlzeiten.setText("3 / " + String.valueOf(mahlzeiterg));
        TextViewWasser = findViewById(R.id.wertanzeigeWasser);
        TextViewWasser.setText(R.string.wasserpref + " / " + wassererg);

        TextViewBewegung = findViewById(R.id.wertanzeigeBewegung);


        FloatingActionButton addA = findViewById(R.id.addAktivitaet);
        FloatingActionButton removeA = findViewById(R.id.removeAktivitaet);


        //ON-CLICK für die Floating Buttons

        addA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Starten der Methode, die das Auswahlfeld für einen Eintrag öffnet
                aktivitaetauswahl();
            }
        });

        removeA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Methode, um den letzten eintrag rückgängig zu machen
            }
        });

    }

    //Methoden für die Dialoge:

    public void aktivitaetauswahl() {
        //methode, um den Hauptdialog zu starten &
        //um die Funktionen zuzuweisen

        //Dialog eine COntentView zuweisen, hier das layout für das erste Pop-Up Fenster
        AuswahlDialog.setContentView(R.layout.popup_aktivitaetswahl);

        moderat = AuswahlDialog.findViewById(R.id.moderat);
        intensiv = AuswahlDialog.findViewById(R.id.intensiv);
        trinken = AuswahlDialog.findViewById(R.id.trinken);
        mahlzeit = AuswahlDialog.findViewById(R.id.nahrung);


        mahlzeit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuswahlDialog.dismiss();
                ZeigMahlzeitDialog();
            }
        });

        intensiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuswahlDialog.dismiss();
                ZeigIntensivDialog();
            }
        });

        moderat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuswahlDialog.dismiss();
                ZeigModeratDialog();
            }
        });

        trinken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuswahlDialog.dismiss();

                DialogFragment newWasser = new StarteWasserDialog();
                newWasser.show(getSupportFragmentManager(), "wasserdia");


            }
        });

        AuswahlDialog.show();
    }


    //--Dialoge für die einzelnen Buttons + Funktion + Wertzuweisung -----------------------------


    //-------------------------------------------------------------------------


    public void ZeigIntensivDialog() {
        //platz für bewegendialog
    }

    public void ZeigModeratDialog() {
        //platz für bewegendialog
    }

    public void ZeigMahlzeitDialog() {
        this.mahlzeiterg = mahlzeiterg;
        Dialog MahlzeitDialog = new Dialog(this);
        MahlzeitDialog.setContentView(R.layout.popup_mahlzeit);

        Button schliessen = MahlzeitDialog.findViewById(R.id.bestätigemahlzeit);


        mahlzeit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mahlzeiterg = +1;

                //Dialog nachdem inkrementirt wurde als bestätigung

                schliessen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MahlzeitDialog.dismiss();
                    }
                });

            }


        });

        //mahlzeiterg mit klick auf button um 1 inkrementieren


        MahlzeitDialog.show();


    }

    //keinen Dialog für mahlzeiten weil diese immer nur um 1 erhöht wird - Umsetzung?


    //--Toolbar Menü -----------------------------------------------------------------


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_home) {

            //zurückwechseln zum tracker wenn in anderem menüpunkt
            //TODO

        }

        if (id == R.id.menu_tipps) {

            Intent wechselzutipps = new Intent(this, ActivityTipps.class);
            startActivity(wechselzutipps);

            //Methode startActivity mit intent, activity mit tipps


            //startActivity(new Intent(MainActivity_Tracking.this, ActivityTipps.class));
            //neues Intent, indem die zweite aktivität aufgerufen wird
        }

        return super.onOptionsItemSelected(item);
    }

    //GETTER UND SETTER (WOHIN PACKEN?) TODO

    public static TextView getTextViewWasser() {
        return TextViewWasser;
    }

    public static void setTextViewWasser(TextView textViewWasser) {
        TextViewWasser = textViewWasser;
    }



}



    //----------------------------------------------

