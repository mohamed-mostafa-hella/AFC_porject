package com.example.o_o.afc.membOp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.o_o.afc.R;
import com.example.o_o.afc.Utalites;
import com.example.o_o.afc.empOp.editemp;
import com.example.o_o.afc.empOp.findemp;
import com.example.o_o.afc.modil.empModil;
import com.example.o_o.afc.modil.memModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class findmem extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ValueEventListener valueEventListener;

    Utalites utalites;

    RadioGroup type;
    EditText input;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findmem);

        type = findViewById(R.id.inputtype);
        input = findViewById(R.id.input);
        search = findViewById(R.id.search);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        utalites = new Utalites(this);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cheackvalid()) {
                    String in = input.getText().toString().trim();
                    switch (type.getCheckedRadioButtonId()) {
                        case R.id.memberId:
                            search(in, "id");
                            Toast.makeText(findmem.this, "search id", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.nationalID:
                            search(in, "natonalId");
                            Toast.makeText(findmem.this, "search natonalId", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(findmem.this, "chose the type of search", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(findmem.this, "your input is mot valid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void search(final String in, final String id) {
        valueEventListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    boolean found=false;
                    for( DataSnapshot dchild: dataSnapshot.getChildren() ){
                        if(dchild.child(id).getValue(String.class) != null &&  dchild.child(id).getValue(String.class).equals(in) ){
                            memModel op = dchild.getValue(memModel.class);
                            String idoffund = dchild.getKey();
                            opendetals(op,idoffund);
                            found=true;
                            break;
                        }
                    }
                    if(!found){
                        Toast.makeText(findmem.this, "not fund", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        database.child("User").child("mem").addValueEventListener(valueEventListener);
    }

    private void opendetals(memModel op , String id) {
        database.child("User").child("mem").removeEventListener(valueEventListener);
        Intent intent = new Intent(this , editmem.class);

        intent.putExtra("op", op);
        intent.putExtra("id",id);

        startActivity(intent);
    }

    private boolean cheackvalid() {
        if(input.getText().toString().trim().isEmpty()) {
            return false;
        }
        return true;
    }
}
