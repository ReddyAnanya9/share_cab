package com.example.sharecab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button logoutbtn,book,show;
    private FirebaseAuth mAuth;
    private FirebaseDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

       // mAuth = FirebaseAuth.getInstance();

        logoutbtn = findViewById(R.id.button);
        book= findViewById(R.id.button6);
        show=findViewById(R.id.button2);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              mAuth.signOut();
              startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),cabbookingform.class));
                Toast.makeText(MainActivity.this,"Redirecting to form",Toast.LENGTH_SHORT).show();
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),List_bookings.class));
            }
        });
       if(mAuth.getCurrentUser()==null){
           startActivity(new Intent(getApplicationContext(),Login.class));
       }

    }




}
