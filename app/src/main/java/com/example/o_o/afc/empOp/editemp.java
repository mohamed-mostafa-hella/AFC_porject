package com.example.o_o.afc.empOp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.o_o.afc.Constant;
import com.example.o_o.afc.R;
import com.example.o_o.afc.Utalites;
import com.example.o_o.afc.modil.empModil;

public class editemp extends AppCompatActivity {
    TextInputEditText empid,nid,name,salary,date,address;
    Spinner job;
    RadioGroup gender;
    RadioButton male ,female;
    Button save;

    Utalites utalites;
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
        empid=findViewById(R.id.employeeid);
        nid=findViewById(R.id.nationalID);
        name=findViewById(R.id.fullname);
        salary=findViewById(R.id.salary);
        date=findViewById(R.id.dateofstart);
        address=findViewById(R.id.address);
        job=findViewById(R.id.jobs) ;
        gender=findViewById(R.id.gender);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        save=findViewById(R.id.save);

        utalites=new Utalites(this);

    }
    void enabled()
    {
        name.setEnabled(true);
        male.setEnabled(true);
        job.setEnabled(true);
        female.setEnabled(true);
        save.setEnabled(true);
        address.setEnabled(true);

    }
    void disable()
    {
        name.setEnabled(false);
        male.setEnabled(false);
        job.setEnabled(false);
        female.setEnabled(false);
        save.setEnabled(false);
        address.setEnabled(false);

    }
    void setdata()
    {
        name.setText(op.getName());
        if(op.getGender() == 1)
            male.setChecked(true);
        else
            female.setChecked(true);
        nid.setText(op.getNationalId());
        date.setText( op.getDate().getYear()+"");
        empid.setText(op.getID());
        address.setText(op.getAddress());
        salary.setText(op.getSalary());
       job.setSelection(Constant.Jobs.indexOf(op.getJop()));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuempedit,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.edit:
                enabled();
                break;
            case R.id.delete:
                utalites.deleteemp(id);
                finish();
                break;
        }
        return true;
    }

    public void save(View view) {
        disable();
        op.setName(name.getText().toString());
        op.setAddress(address.getText().toString());
        op.setJop(job.getSelectedItem().toString());
        disable();
        utalites.updateEmployee(op,id);
    }
}
