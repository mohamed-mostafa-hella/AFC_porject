package com.example.o_o.afc;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.o_o.afc.*;
import com.example.o_o.afc.empOp.findemp;
import com.example.o_o.afc.empOp.newemployee;
import com.example.o_o.afc.membOp.NewMember;
import com.example.o_o.afc.membOp.findmem;
import com.example.o_o.afc.modil.memModel;
import com.example.o_o.afc.visOp.newVistor;

public class StartActivity extends AppCompatActivity {
    Toolbar mtoolbar;
    Intent next;
    String type;

    Utalites utalites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mtoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);

        utalites=new Utalites(this);

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
        } else {
            getMenuInflater().inflate(R.menu.logoutmenu, menu);
        }

        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newemp:
                next = new Intent(this, newemployee.class);
                break;
            case R.id.oldemp:
                next = new Intent(this, findemp.class);
                break;
            case R.id.newmem:
                next = new Intent(this, NewMember.class);
                break;
            case R.id.oldmem:
                next = new Intent(this, findmem.class);
                break;
            case R.id.getticket:
                next = new Intent(this, newVistor.class);
                break;
            case R.id.LogOut:
                next = new Intent(this, Login.class);
                utalites.logout();
                break;
        }
        getApplicationContext().startActivity(next);
        return true;
    }
}
