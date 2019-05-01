package com.example.o_o.afc.visOp;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;

import com.example.o_o.afc.R;

import net.glxn.qrgen.javase.QRCode;

public class newVistor extends AppCompatActivity {
    ImageView myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_vistor);
        String myBitmapString = QRCode.from("mohamed25").toString();

        myImage = (ImageView) findViewById(R.id.ticketcode);


        byte[] ecode = Base64.decode(myBitmapString , Base64.DEFAULT);
        Bitmap myBitMap = BitmapFactory.decodeByteArray(ecode , 0  , ecode.length );



        myImage.setImageBitmap(myBitMap);

    }
}
