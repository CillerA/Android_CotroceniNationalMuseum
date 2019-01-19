package com.example.ciller.egov_tema1;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;

public class ss extends AppCompatActivity {
    private Button pdf;
    String fileName;
    File imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ss);

        TextView numeTicket = (TextView) findViewById(R.id.textViewNumeSiPrenumeSS);
        numeTicket.setText(getIntent().getExtras().getString("numeSiPrenume"));

        TextView tipTicket = (TextView) findViewById(R.id.textViewTipBiletSS);
        tipTicket.setText(getIntent().getExtras().getString("tipBilet"));

        TextView discountTicket = (TextView) findViewById(R.id.textViewDiscountSS);
        discountTicket.setText(getIntent().getExtras().getString("discount"));

        TextView pretTicket = (TextView) findViewById(R.id.textViewPretBiletSS);
        pretTicket.setText(getIntent().getExtras().getString("pret"));

        TextView fotoTicket = (TextView) findViewById(R.id.textViewFotoSS);
        fotoTicket.setText(getIntent().getExtras().getString("foto"));

        TextView videoTicket = (TextView) findViewById(R.id.textViewVideoSS);
        videoTicket.setText(getIntent().getExtras().getString("video"));

        TextView audioTicket = (TextView) findViewById(R.id.textViewAudioSS);
        audioTicket.setText(getIntent().getExtras().getString("audio"));

        TextView dataTicket = (TextView) findViewById(R.id.textViewDataSS);
        dataTicket.setText(getIntent().getExtras().getString("data"));

        TextView totalTicket = (TextView) findViewById(R.id.textViewTotalSS);
        totalTicket.setText(getIntent().getExtras().getString("total"));

        pdf = (Button)findViewById(R.id.takeScreenshotButton);
        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = takeScreenshot();
                saveBitmap(bitmap);
                Toast.makeText(getApplicationContext(), "Biletul dvs s-a salvat in directorul "+imagePath, Toast.LENGTH_LONG).show();
                convertToPdf();

            }
        });

    }
    public Bitmap takeScreenshot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap) {
         imagePath = new File(Environment.getExternalStorageDirectory() +"/biletElectronic.jpg");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }

    public void convertToPdf() {
        Document document = new Document();
        String directoryPath = Environment.getExternalStorageDirectory()+"/ElectronicTicket.pdf";
        try {
            PdfWriter.getInstance(document, new FileOutputStream(directoryPath));
            document.open();
            Image image = Image.getInstance(imagePath.toString());
            float scaler = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin() - 0) / image.getWidth()) * 100;
            image.scalePercent(scaler);
            image.setAlignment(Image.ALIGN_CENTER | Image.ALIGN_TOP);
            document.add(image);
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
