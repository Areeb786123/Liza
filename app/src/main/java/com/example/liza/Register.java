package com.example.liza;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    private static final String TAG="myactivity";

    EditText mFullName,mEmail,mPassword,mPhone;
    Button mRegistrationBtn;
    TextView mLoginBtn;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.Password);
        mPhone = findViewById(R.id.Phone);
        mRegistrationBtn = findViewById(R.id.register_btn);
        mLoginBtn = findViewById(R.id.already);

        firebaseAuth = FirebaseAuth.getInstance();


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent i = new Intent(Register.this,Login.class);
                    startActivity(i);
            }
        });


        mRegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

               if (TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required ");


                }

                else if (TextUtils.isEmpty(password)){
                    mPassword.setError("Password is must required");

                }


                 else {
                   //
                   // mPassword.setError("Password Must be of 6 characters");
                    createAcc(email, password);
                }



            }
        });
    }



    public void updateUI( FirebaseUser user){

        if(user!=null){
            Intent i = new Intent(Register.this,MainActivity.class);
            startActivity(i);


        }


    }


   public void createAcc(String email, String password){



       firebaseAuth.createUserWithEmailAndPassword(email, password)
               .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()) {
                           // Sign in success, update UI with the signed-in user's information
                           Log.d(TAG, "createUserWithEmail:success");
                           FirebaseUser user = firebaseAuth.getCurrentUser();
                           updateUI(user);
                       } else {
                           // If sign in fails, display a message to the user.
                           Log.w(TAG, "createUserWithEmail:failure", task.getException());
                           Toast.makeText(Register.this, "Authentication failed."+task.getException(),
                                   Toast.LENGTH_SHORT).show();
                           updateUI(null);
                       }

                       // ...
                   }
               });





   }




}
