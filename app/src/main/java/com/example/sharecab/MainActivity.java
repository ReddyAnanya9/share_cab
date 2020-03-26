package com.example.sharecab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button logoutbtn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // mAuth = FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();
        logoutbtn = findViewById(R.id.button);

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              mAuth.signOut();
              startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
    }
  //  public void signout(View view){
    //    mAuth.signOut();
    //}
}
