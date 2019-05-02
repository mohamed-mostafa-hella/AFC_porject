package com.example.o_o.afc;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

public class StartActivity extends AppCompatActivity {
    Toolbar mtoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mtoolbar=findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);

    }
@Override
public boolean onCreateOptionsMenu(Menu menu) {
        ///لو كان موظف شئون عاملين
  getMenuInflater().inflate(R.menu.menuemp,menu);
  //لوكان مدير نادي اونائب
  getMenuInflater().inflate(R.menu.menumanger,menu);
  //لو كان فرد امن او مدير امن
  getMenuInflater().inflate(R.menu.menusecurity,menu);
  //لو كان موظف عضوية او مدير عضوية
  getMenuInflater().inflate(R.menu.menumemofficer,menu);

  return true ;


}

}
