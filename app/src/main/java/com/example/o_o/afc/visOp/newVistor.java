package com.example.o_o.afc.visOp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.o_o.afc.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class newVistor extends AppCompatActivity {
    ImageView myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_vistor);

        myImage = findViewById(R.id.ticketcode);

        setQrCode("mohamed hella ");







    }

    public void setQrCode(String s){
        MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
        try {
            BitMatrix bitMatrix=multiFormatWriter.encode(s, BarcodeFormat.QR_CODE , 200 , 200);

            BarcodeEncoder barcodeEncoder=new BarcodeEncoder();
            Bitmap myBitMap=barcodeEncoder.createBitmap(bitMatrix);

            myImage.setImageBitmap(myBitMap);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
