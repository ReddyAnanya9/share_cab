package com.example.sharecab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button logoutbtn,book;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // mAuth = FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();
        logoutbtn = findViewById(R.id.button);
        book= findViewById(R.id.button2);
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
    }
  //  public void signout(View view){
    //    mAuth.signOut();
    //}
}
