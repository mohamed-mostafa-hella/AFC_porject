package com.example.o_o.afc.membOp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.o_o.afc.R;
import com.example.o_o.afc.Utalites;
import com.example.o_o.afc.modil.empModil;
import com.example.o_o.afc.modil.memModel;

import java.util.Calendar;
import java.util.Date;

public class editmem extends AppCompatActivity {
    Toolbar mtoolbar;
    TextInputEditText memid,nid,name,memdate,expirydate,address,job;
    RadioGroup gender;
    RadioButton male ,female;
    Button save;

    memModel op;
    String id;

    Utalites utalites;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editmem);
        mtoolbar=findViewById(R.id.toolbar);

        Intent i = getIntent();
        op = (memModel) i.getSerializableExtra("op");
        id=i.getStringExtra("id");
        utalites=new Utalites(this);

        setSupportActionBar(mtoolbar);
        mtoolbar.setTitle("");
        memid=findViewById(R.id.memberId);
        nid=findViewById(R.id.nationalID);
        name=findViewById(R.id.fullname);
        memdate=findViewById(R.id.dateofmembership);
        expirydate=findViewById(R.id.dateofexpiry);
        address=findViewById(R.id.address);
        job=findViewById(R.id.job) ;
        gender=findViewById(R.id.gender);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        save=findViewById(R.id.save);
        setdata();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menumemedit,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete:
                utalites.deletemem(id);
                finish();
                break;
            case R.id.edit:
                enabled();
                break;
            case R.id.renewal:
                Date newex=op.getExdata();
                newex.setYear(newex.getYear()+1);
                op.setExdata(newex);
                expirydate.setText(newex.getYear());
                disabled();
                break;
        }
        return true;
    }
    void  enabled()
    {
        name.setEnabled(true);
        male.setEnabled(true);
        female.setEnabled(true);
        address.setEnabled(true);
        job.setEnabled(true);
        nid.setEnabled(true);
        save.setEnabled(true);
    }
    void disabled()
    {
        name.setEnabled(false);
        male.setEnabled(false);
        female.setEnabled(false);
        address.setEnabled(false);
        job.setEnabled(false);
        nid.setEnabled(false);
        save.setEnabled(false);
    }
    void setdata()
    {
        name.setText(op.getName());

        if(op.getGender() == 1)
            male.setChecked(true);
        else
            female.setChecked(true);
        nid.setText(op.getNatonalId());
        memdate.setText( op.getDate().getYear()+"");
        memid.setText(op.getID());
        job.setText(op.getJop());
        address.setText(op.getAddress());
        expirydate.setText(op.getExdata().toString());
    }
    public void save(View view) {
       op.setName( name.getText().toString());
        op.setAddress( address.getText().toString());
        op.setJop( job.getText().toString());
        op.setNatonalId( nid.getText().toString());
        op.setName( name.getText().toString());
        if(male.isChecked())
            op.setGender(1);
        else
            op.setGender(0);
        disabled();
        utalites.updateMemper(op,id);
    }
}