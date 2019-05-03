package com.example.o_o.afc.empOp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import com.example.o_o.afc.R;
import com.example.o_o.afc.modil.empModil;

public class editemp extends AppCompatActivity {

    empModil op;
    String id;

    Toolbar mtoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editemp);
        mtoolbar=(findViewById(R.id.toolbar));
        setSupportActionBar(mtoolbar);
        mtoolbar.setTitle("");

        Intent i = getIntent();
        op = (empModil) i.getSerializableExtra("op");
        id=i.getStringExtra("id");

        Toast.makeText(this, op.getName(), Toast.LENGTH_SHORT).show();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuempedit,menu);
        return true;
    }

    //use utalits updateEmployee;
}
