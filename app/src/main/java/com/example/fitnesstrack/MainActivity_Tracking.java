package com.example.fitnesstrack;



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
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * @author Denise Weinert
 * @version 1.0
 *
 * Die Android App dient dazu, seine Gewohnheiten in den Themen Trinken, Essen und Bewegung zu
 * sehen. Die App hilft dabei, ein Gefühl für den täglichen Fortschritt zu bekommen und eventuelle
 * Defizite in den gennanten Gewohnheiten zu erkennen. Ein Fortschrittsbalken visualisiert bereits
 * hinzugefügte Werte.
 *
 * Diese Klasse ist die Activity, die als erste Ansicht beim öffnen der App gezeigt wird. Hier
 * implementiert sind neben der Basisfunktionen für den Aufbau der App die Methode, mit der der
 * Nutzer die gewünschte Aktivität hinzufügen kann. Zur Speicherung der eingegeben Daten werden
 * Datenbanken verwendet, die lokal auf dem Gerät gespeichert wird.
 *
 */

public class MainActivity_Tracking extends AppCompatActivity{


    //Initialisierungswerte:
    public int mahlzeitcount = 0;
    public int wasserinit = 0;

    //Dialoge
    Dialog AuswahlDialog;

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
    public TextView TextViewMahlzeiten;
    public TextView TextViewWasser;


    //GETTER UND SETTER (WOHIN PACKEN?) TODO



    /*
    Die Methode onCreate() wird aufgerufen beim ersten Starten der App, in der die Aktivität
    initialisiert wird.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Die .xml-Datei des Layouts für die Haupt-Activity wird angebunden
        setContentView(R.layout.activity_main);

        //Der Dialog (das erste Pop-up-Fenster) wird initialisiert
        //Der Context ist "this" -> Referenz auf die Main Activity, wo der Dialog genutzt wird
        AuswahlDialog = new Dialog(this);

        //Ressourcenzuweisung Buttons
        moderat = findViewById(R.id.moderat);
        intensiv = findViewById(R.id.intensiv);
        trinken = findViewById(R.id.trinken);
        mahlzeit = findViewById(R.id.nahrung);



        //Ressourcenzuweisung TextViews
        TextViewMahlzeiten = (TextView) findViewById(R.id.wertanzeigeMahlzeiten);
        TextViewWasser = (TextView) findViewById(R.id.wertanzeigeWasser);
        TextView textViewBewegung = (TextView) findViewById(R.id.wertanzeigeBewegung);

        //Ressourcenzuweisung FloatingActionButtons
        FloatingActionButton addA = findViewById(R.id.addAktivitaet);
        FloatingActionButton removeA = findViewById(R.id.removeAktivitaet);



        /*
        onClick()-Methoden für die Floating Buttons, mit der eine Aktivität hinzugefügt wird oder
        wieder Rückgängig gemacht
         */


        //Initialisierung der Texte über den Fortschritsbalken
        TextViewMahlzeiten.setText(R.string.mahlzeitpräfix + Integer.toString(mahlzeitcount));
        TextViewWasser.setText(R.string.wasserpref + Integer.toString(wasserinit));



        addA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Aufruf der Methode, um den ersten Dialog zu öffnen
                aktivitaetauswahl();

            }
        });

        removeA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code, um den letzen Eintrag rückgängig zu machen //TODO
            }
        });




    }


    //Methode zum Auswahl der Aktivität, onClick() für jeden Button.

    public void aktivitaetauswahl() {

        //Dialog eine ContentView zuweisen, popup_aktivitaetswahl.xml wird hier eingebunden
        Dialog AuswahlDialog = new Dialog(this);
        AuswahlDialog.setContentView(R.layout.popup_aktivitaetswahl);


        // Buttons im ersten Dialog ("AuswahlDialog"); zuweisung der Elemente vom Layout in diesem
        //Scope
        moderat = AuswahlDialog.findViewById(R.id.moderat);
        intensiv = AuswahlDialog.findViewById(R.id.intensiv);
        trinken = AuswahlDialog.findViewById(R.id.trinken);
        mahlzeit = AuswahlDialog.findViewById(R.id.nahrung);


        mahlzeit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuswahlDialog.dismiss();
                //zuweisen des xml-Layouts für den Mahlzeiten-Dialog
                Dialog MahlzeitDialog = new Dialog(MainActivity_Tracking.this);
                MahlzeitDialog.setContentView(R.layout.popup_mahlzeit);


                //Inkrementieren der Variable mahlzeitcount um 1, bei jede tippen auf den Button
                // mahlzeit, und aktualisieren der TextView

                    mahlzeitcount++;
                    TextViewMahlzeiten.setText("3: " + (mahlzeitcount));



                Button schliessen = MahlzeitDialog.findViewById(R.id.bestätigemahlzeit);
                schliessen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //mittels dismiss() wird der Dialog geschlossen
                        MahlzeitDialog.dismiss();
                    }

                });
                MahlzeitDialog.show();

            }
        });

        intensiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuswahlDialog.dismiss();
                //TODO
            }
        });

        moderat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuswahlDialog.dismiss();
                //TODO
            }
        });

        trinken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuswahlDialog.dismiss();

                final AlertDialog.Builder wasserdialog = new AlertDialog.Builder(MainActivity_Tracking.this);
                AlertDialog dialogInstance = wasserdialog.create();
                LayoutInflater inflater = MainActivity_Tracking.this.getLayoutInflater();
                View wasserview = inflater.inflate(R.layout.poput_eingabewasser, null);
                wasserdialog.setView(wasserview);

                EditText wassereing = findViewById(R.id.eingabeWasser);

                wasserdialog.setTitle("Gib an wieviel Wasser du getrunken hast")
                        .setPositiveButton(R.string.wasserbest, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {


                                try {
                                    Wasser wasser = new Wasser(-1, Integer.parseInt(wassereing.getText().toString()));
                                    Toast.makeText(MainActivity_Tracking.this, wasser.toString(), Toast.LENGTH_SHORT().show);

                                }catch (Exception e){
                                        Toast.makeText(MainActivity_Tracking.this, "Feld nicht leer lassen oder auf 'Abbrechen' tippen. " , Toast.LENGTH_LONG);
                                }


                                /*Instanzieren eines neuen Objekts und mit Funktion addOneW()
                                einen Eintrag in die Datenbank hinzufügen
                                 */


                                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity_Tracking.this);
                                boolean erfolg = databaseHelper.addOneW(wasser);

                                /*Aktualisierung der TextView, nachdem mit der Funktion getSumW()
                                 die Spalte mit den Wasserwerten zusammengerechnet wird
                                 */
                                TextViewWasser.setText("1500 : " + databaseHelper.getSumW());

                            }
                        })
                        .setNegativeButton(R.string.wassercancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //dialogInstance mit der Funktio dismiss() aufrufen für den Abbruch
                                dialogInstance.dismiss();
                            }
                        });

                // mittels der Funktion show() den Dialog aufrufen, nachdem er erstellt wurde.
                wasserdialog.show();






            }
        });

        //Aufrufen des Hauptdialogs
        AuswahlDialog.show();
    }


    //-- Toolbar: Menü zum auswählen der Aktivitäten -----------------------------------------------------------------


    /**
     * @param menu Parameter übergeben zum zuweisen des Layouts der Toolbar
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * @param item um direkten Zugriff auf Menu Objekte zu haben, ID wird abgerufen und in
     *             der Integer-Variable id gespeichert
     * @return
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //mittels getItemId() wird die ID des MenuItems zurückgegeben
        int id = item.getItemId();


        if (id == R.id.menu_home) {
            //zurückwechseln zum tracker wenn in anderem menüpunkt
            //TODO
        }


        if (id == R.id.menu_tipps) {

            Intent wechselzutipps = new Intent(this, ActivityTipps.class);
            startActivity(wechselzutipps);
            //Methode startActivity mit intent, activity mit tipps
            //neues Intent, indem die zweite Aktivität aufgerufen wird.
        }

        return super.onOptionsItemSelected(item);
    }

}


