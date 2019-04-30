package com.example.o_o.afc.empOp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.o_o.afc.R;

public class newemployee extends AppCompatActivity {
    String [] Jobs={"مدير النادي","نائب مديرالنادي ","مدير شئون قانونية","باحث بشئون قانونية","مدير نشاط رياضي","باحث بالنشاط الرياضي","مدير آمن","فرد آمن","مدير العضويات","باحث العضويات","رئيس شئون العاملين","موظف شئون عاملين","مسئول نظافة","مدير ثقافى ورحلات","باحث ثقافى ورحلات","مدرب"};
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newemployee);
        spinner=findViewById(R.id.jobs);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Jobs);
     spinner.setAdapter(adapter);
    }
}
