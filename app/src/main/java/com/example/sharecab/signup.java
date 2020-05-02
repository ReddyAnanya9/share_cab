package com.example.sharecab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.multidex.MultiDexApplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;



import static android.content.ContentValues.TAG;

public class signup extends AppCompatActivity{
    EditText mFullName,mEmail,mPassword,checkPass,mphoneno;
    Button mRegisterBtn;
    TextView mLoginBtn;
 //   String userID;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mFullName = findViewById(R.id.editText4);
        mEmail = findViewById(R.id.editText6);
        mPassword = findViewById(R.id.editText7);
        mRegisterBtn = findViewById(R.id.registerbtn);
        mLoginBtn = findViewById(R.id.textView6);
        checkPass=findViewById(R.id.editText8);
        mphoneno=findViewById(R.id.editText5);
        mAuth = FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();

        if(mAuth.getCurrentUser()!= null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                final String fullname = mFullName.getText().toString();
                final String phoneno = mphoneno.getText().toString();
                String checkpass = checkPass.getText().toString();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("email required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password required");
                    return;
                }
                if(phoneno.length()!=10){
                    mphoneno.setError("Invalid phone no");
                }
                if(password.length()<6){
                    mPassword.setError("Password grater than 6");
                    return;
                }
                if(!password.equals(checkpass)){
                    checkPass.setError("password doesn't match");
                }

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(signup.this, "User created", Toast.LENGTH_SHORT).show();
                            CollectionReference users=db.collection("Users") ;
                            User u= new User(fullname,email,phoneno);
                            /*users.add(u).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(signup.this,"User added to database",Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(signup.this,e.getMessage(),Toast.LENGTH_LONG).show();
                                }
                            });*/
                            users.document(mAuth.getCurrentUser().getUid()).set(u).
                                    addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(signup.this,"User added to database",Toast.LENGTH_LONG).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(signup.this,e.getMessage(),Toast.LENGTH_LONG).show();
                                }
                            });

                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(signup.this, "Error occurred" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });

            }
        });
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

    }
}
