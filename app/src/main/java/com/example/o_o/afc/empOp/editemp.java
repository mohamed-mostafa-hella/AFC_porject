package com.example.o_o.afc.empOp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.example.o_o.afc.R;

public class editemp extends AppCompatActivity {

    Toolbar mtoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editemp);
        mtoolbar=(findViewById(R.id.toolbar));
        setSupportActionBar(mtoolbar);
        mtoolbar.setTitle("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuempedit,menu);
        return true;
    }
}
