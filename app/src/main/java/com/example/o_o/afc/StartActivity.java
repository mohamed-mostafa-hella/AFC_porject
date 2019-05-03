package com.example.o_o.afc;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import com.example.o_o.afc.modil.memModel;

public class StartActivity extends AppCompatActivity {
    Toolbar mtoolbar;

    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mtoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);

        Intent i = getIntent();
        type = i.getStringExtra("type");
        // op = (memModel) i.getSerializableExtra("op");
        // id=i.getStringExtra("id");
        // utalites=new Utalites(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (type.equals("1")) {
            getMenuInflater().inflate(R.menu.menumanger, menu);

        } else if (type.equals("2")) {
            getMenuInflater().inflate(R.menu.menuemp, menu);

        } else if (type.equals("3")) {
            getMenuInflater().inflate(R.menu.menumemofficer, menu);

        } else if (type.equals("4")) {
            getMenuInflater().inflate(R.menu.menusecurity, menu);
        }

        return true;


    }

}
