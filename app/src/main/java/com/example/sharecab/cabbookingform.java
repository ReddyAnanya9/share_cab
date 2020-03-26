package com.example.sharecab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class cabbookingform extends AppCompatActivity {
    Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabbookingform);
        confirm=findViewById(R.id.button3);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(cabbookingform.this,"Details noted",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
