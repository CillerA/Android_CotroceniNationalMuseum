package com.example.ciller.egov_tema1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RadioButton adult;
    RadioButton elev;
    RadioButton pensionar;

    CheckBox foto;
    CheckBox video;
    CheckBox ghidAudio;

    TextView textView;
    TextView textViewDiscount;
    TextView textViewTotal;

    RadioButton discountAdult;
    RadioButton discountElev;
    RadioButton discountPensionar;

    public static final int PRET_ADULT = 50;
    public static final int FOTO_PRET = 20;
    public static final int VIDEO_PRET = 50;
    public static final int AUDIO_PRET = 10;
    public static final int REDUCERE_ELEV = 50;
    public static final int REDUCERE_PENSIONAR = 30;

    String pretElev;
    String pretPensionar;
    int suma = 0;

    ArrayList<Ticket> tickets  = new ArrayList<>();
    Integer discounttttt, pretttt;

    DatabaseHelper myDb;
    Button btnAddData;
    EditText numeET;
    EditText emailET;
    EditText telefonET;
    RadioGroup tipRG;
    int idRadioButton;
    RadioButton tipRB;
    TextView discountTV;
    TextView pretTV;
    CheckBox fotoCB;
    CheckBox videoCB;
    TextView totalTV;
    CheckBox audioCB;
    DatePicker dataDP;

    RadioButton rezultat;
    private Button generate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        discountAdult = (RadioButton) findViewById(R.id.radioButtonAdulti);
        discountElev = (RadioButton) findViewById((R.id.radioButtonElevi));
        discountPensionar = (RadioButton) findViewById(R.id.radioButtonPensionari);

        adult = (RadioButton) findViewById(R.id.radioButtonAdulti);
        elev = (RadioButton) findViewById((R.id.radioButtonElevi));
        pensionar = (RadioButton) findViewById(R.id.radioButtonPensionari);

        foto = (CheckBox) findViewById(R.id.checkBoxTaxaFoto);
        video = (CheckBox) findViewById(R.id.checkBoxTaxaVideo);
        ghidAudio = (CheckBox) findViewById(R.id.checkBoxGhidAudio);

        textViewTotal = findViewById(R.id.textViewTotalPlata);

        View.OnClickListener optionOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView = findViewById(R.id.textViewPret);
                textViewDiscount = findViewById(R.id.textViewDiscountGenerat);

                int discount = 0;
                if (discountAdult.isChecked()) {
                    textViewDiscount.setText("0");
                } else if (discountElev.isChecked()) {
                    textViewDiscount.setText(String.valueOf(REDUCERE_ELEV));
                } else if (discountPensionar.isChecked()) {
                    textViewDiscount.setText(String.valueOf(REDUCERE_PENSIONAR));
                } else {
                    textViewDiscount.setText("0");
                }
                discount = Integer.parseInt(textViewDiscount.getText().toString());
                float procent = (float) discount / 100;
                if (adult.isChecked()) {
                    discounttttt = 0;
                    pretttt = 50;
                    textView.setText(String.valueOf(PRET_ADULT));
                    suma = PRET_ADULT;
                } else if (elev.isChecked()) {
                    discounttttt = 50;
                    pretElev = String.valueOf(PRET_ADULT - procent * PRET_ADULT);
                    textView.setText(pretElev);
                    pretttt = 25;
                    suma = 25;
                } else if (pensionar.isChecked()) {
                    pretttt = 35;
                    discounttttt = 30;
                    pretPensionar = String.valueOf(PRET_ADULT - procent * PRET_ADULT);
                    textView.setText(pretPensionar);
                    suma = 35;
                } else {
                    textView.setText("0");
                }

                if (foto.isChecked()) {
                    suma = suma + FOTO_PRET;
                }
                if (video.isChecked()) {
                    suma = suma + VIDEO_PRET;
                }
                if (ghidAudio.isChecked()) {
                    suma = suma + AUDIO_PRET;
                }
                textViewTotal.setText(String.valueOf(suma));
            }
        };

        discountAdult.setOnClickListener(optionOnClickListener);
        discountElev.setOnClickListener(optionOnClickListener);
        discountPensionar.setOnClickListener(optionOnClickListener);
        adult.setOnClickListener(optionOnClickListener);
        elev.setOnClickListener(optionOnClickListener);
        pensionar.setOnClickListener(optionOnClickListener);
        foto.setOnClickListener(optionOnClickListener);
        ghidAudio.setOnClickListener(optionOnClickListener);
        video.setOnClickListener(optionOnClickListener);

        addData();

        final EditText ssNume = (EditText) findViewById(R.id.editTextNume);
        final TextView discountSS = (TextView) findViewById(R.id.textViewDiscountGenerat);
        final TextView pretSS = (TextView) findViewById(R.id.textViewPret);
        // final double pSS = Double.valueOf(pretSS.getText().toString());
        final CheckBox fotoSS = (CheckBox) findViewById(R.id.checkBoxTaxaFoto);
        final CheckBox videoSS = (CheckBox) findViewById(R.id.checkBoxTaxaVideo);
        final CheckBox audioSS = (CheckBox) findViewById(R.id.checkBoxGhidAudio);
        final DatePicker dataSS = (DatePicker) findViewById(R.id.datePicker);
        final TextView totalSS = (TextView) findViewById(R.id.textViewTotalPlata);


        generate = (Button) findViewById(R.id.genereazaButon);
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(MainActivity.this, ss.class);
                intent.putExtra("numeSiPrenume", ssNume.getText().toString());
                final RadioGroup tipBilet = (RadioGroup) findViewById(R.id.radioGroupCategorie);
                int selectedRadioButtonID = tipBilet.getCheckedRadioButtonId();
                if (selectedRadioButtonID != -1) {
                            RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioButtonID);
                            String selectedRadioButtonText = selectedRadioButton.getText().toString();
                            intent.putExtra("tipBilet", selectedRadioButtonText);
                        }
                        else{
                            intent.putExtra("tipBilet", "");
                        }

                boolean fotoIsChecked = fotoSS.isChecked();
                String fotoString = String.valueOf(fotoIsChecked);
                boolean videoIsChecked = videoSS.isChecked();
                String videoString = String.valueOf(videoIsChecked);
                boolean audioIsChecked = audioSS.isChecked();
                String audioString = String.valueOf(audioIsChecked);
                intent.putExtra("discount", discountSS.getText().toString());
                intent.putExtra("pret", pretSS.getText().toString());
                intent.putExtra("foto", fotoString);
                intent.putExtra("video", videoString);
                intent.putExtra("audio", audioString);
                intent.putExtra("data", dataSS.getDayOfMonth() + "-" + dataSS.getMonth() + "-" + dataSS.getYear());
                intent.putExtra("total", totalSS.getText().toString());

                startActivity(intent);
            }
        });
    }


    public void addData() {
        btnAddData = (Button) findViewById(R.id.cumparaButon);
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double p = 0;
                int ok = 1;

                numeET = (EditText) findViewById(R.id.editTextNume);

                if (numeET.getText() == null || numeET.getText().toString().trim().isEmpty())
                    Toast.makeText(getApplicationContext(), "Va rog completati toate campurile", Toast.LENGTH_LONG).show();

                emailET = (EditText) findViewById(R.id.editTextEmail);
                telefonET = (EditText) findViewById(R.id.editTextTelefon);
                tipRG = (RadioGroup) findViewById(R.id.radioGroupCategorie);
                idRadioButton = tipRG.getCheckedRadioButtonId();
                tipRB = (RadioButton) findViewById(idRadioButton);
                discountTV = (TextView) findViewById(R.id.textViewDiscountGenerat);
                pretTV = (TextView) findViewById(R.id.textViewPret);

// if (!adult.isChecked() || !pensionar.isChecked() || !elev.isChecked()) {
// discountTV.setText(String.valueOf(0));
// pretTV.setText(String.valueOf(0));
// p = Double.valueOf(pretTV.getText().toString());
//}
                p = Double.valueOf(pretTV.getText().toString());
                totalTV = (TextView) findViewById(R.id.textViewTotalPlata);
                fotoCB = (CheckBox) findViewById(R.id.checkBoxTaxaFoto);
                videoCB = (CheckBox) findViewById(R.id.checkBoxTaxaVideo);
                audioCB = (CheckBox) findViewById(R.id.checkBoxGhidAudio);
                dataDP = (DatePicker) findViewById(R.id.datePicker);

                if (numeET.getText().length() < 5) {
                    numeET.setError("Acest camp este obligatoriu. Introduceti numele si prenumele");
                    ok = 0;
                }
                if (emailET.getText() == null || emailET.getText().toString().trim().isEmpty()) {
                    emailET.setError("Acest camp este obligatoriu. Introduceti email-ul dvs");
                    ok = 0;
                }
                if (telefonET.getText().length() != 10) {
                    telefonET.setError("Numarul de telefon trebuie sa contina 10 cifre");
                    ok = 0;
                }

                if (ok == 1) {
                    boolean isInserted = myDb.insertData(numeET.getText().toString(), emailET.getText().toString(),
                            telefonET.getText().toString(), tipRB.getText().toString(), Integer.valueOf(discountTV.getText().toString()), p,
                            fotoCB.isChecked(), videoCB.isChecked(), audioCB.isChecked(), Integer.valueOf(totalTV.getText().toString()), dataDP.getDayOfMonth(), dataDP.getMonth() + 1, dataDP.getYear());

                    if (isInserted == true) {
                        Toast.makeText(MainActivity.this, "Felicitari! Rezervare efectuata!", Toast.LENGTH_LONG).show();
                        writeToXmlFile(view);
                    }
                    else
                        Toast.makeText(MainActivity.this, "Mai incercati o data!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Va rog completati toate campurile", Toast.LENGTH_LONG).show();
                }
                }
        });
    }

    public void writeToXmlFile(View view){
        tickets.add(new Ticket(numeET.getText().toString(), emailET.getText().toString(),
                telefonET.getText().toString(), tipRB.getText().toString(), discountTV.getText().toString(), pretTV.getText().toString(),
                fotoCB.isChecked(), videoCB.isChecked(), audioCB.isChecked(), totalTV.getText().toString(), dataDP.getDayOfMonth(), dataDP.getMonth() + 1, dataDP.getYear()));

        XmlSerializer serializer = Xml.newSerializer();
        StringWriter writer = new StringWriter();
        //FileWriter writer = new FileWriter("ElectronicTickets.xml", true);
        try {
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);
            serializer.startTag("", "tickets");
            serializer.attribute("", "number", String.valueOf(tickets.size()));
            for(Ticket t : tickets) {
                serializer.startTag("", "name");
                serializer.text(t.getName());
                serializer.endTag("", "name");

                serializer.startTag("", "email");
                serializer.text(t.getEmail());
                serializer.endTag("", "email");

                serializer.startTag("", "telephone");
                serializer.text(t.getTelephone());
                serializer.endTag("", "telephone");

                serializer.startTag("", "category");
                serializer.text(t.getCategory());
                serializer.endTag("", "category");

                serializer.startTag("", "discount");
                serializer.text(String.valueOf(t.getDiscount()));
                serializer.endTag("", "discount");

                serializer.startTag("", "price");
                serializer.text(String.valueOf(t.getPrice()));
                serializer.endTag("", "price");

                serializer.startTag("", "foto");
                serializer.text(String.valueOf(t.isPhoto()));
                serializer.endTag("", "foto");

                serializer.startTag("", "video");
                serializer.text(String.valueOf(t.isVideo()));
                serializer.endTag("", "video");

                serializer.startTag("", "audio");
                serializer.text(String.valueOf(t.isAudio()));
                serializer.endTag("", "audio");

                serializer.startTag("", "total");
                serializer.text(String.valueOf(t.getTotal()));
                serializer.endTag("", "total");

            }
            serializer.endTag("", "tickets");
            serializer.endDocument();
            String result = writer.toString();
            MainActivity.writeToFile (this,"ElecronicTickets.xml", result);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(Context context, String fileName, String str) {
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(str.getBytes(), 0, str.length());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}