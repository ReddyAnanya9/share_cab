package com.example.sharecab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class cabbookingform extends AppCompatActivity {
    Button confirm,cancel,newform;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    EditText mdes,mpick_up_pt,m_pick_time,spl_req,mlugg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabbookingform);
        confirm=findViewById(R.id.button4);
        cancel=findViewById(R.id.button5);
        newform=findViewById(R.id.button3);
        mdes=findViewById(R.id.editText2);
        mpick_up_pt=findViewById(R.id.editText3);
        m_pick_time=findViewById(R.id.editText11);
        spl_req=findViewById(R.id.editText10);
        mlugg=findViewById(R.id.editText9);
        mAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(cabbookingform.this,"Details noted",Toast.LENGTH_SHORT).show();
                final String des=mdes.getText().toString();
                final String pickpt=mpick_up_pt.getText().toString();
                final String picktime=m_pick_time.getText().toString();
                final String luggage=mlugg.getText().toString();
                final String splreq=spl_req.getText().toString();
                CollectionReference Bookings=db.collection("User Bookings");
                UserBookings booking= new UserBookings(des,pickpt,splreq,picktime,Integer.parseInt(luggage));
                /*Bookings.add(booking).
                        addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(cabbookingform.this,"Booking registered",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(cabbookingform.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });*/
                Bookings.document(mAuth.getCurrentUser().getUid()).set(booking).
                        addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(cabbookingform.this,"Booking registered",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(cabbookingform.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        newform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),cabbookingform.class));
            }
        });
    }
}
