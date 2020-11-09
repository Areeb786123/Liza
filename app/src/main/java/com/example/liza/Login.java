package com.example.liza;

import android.content.Intent;
import android.os.Bundle;
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

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    public static final String TAG="Myactivity";

    String e;
    EditText mail;
    EditText pass;
    String p;
    Button btn;
    TextView neww;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();
         mail=(EditText)findViewById(R.id.fullName);
         pass=(EditText)findViewById(R.id.Password);
         btn =  (Button)findViewById(R.id.login_btn);

         e = mail.getText().toString();
         p = pass.getText().toString();
         neww=(TextView) findViewById(R.id.New) ;
         neww.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i = new Intent(Login.this, Register.class);
                 startActivity(i);
             }
         });


         neww.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i = new Intent(Login.this,Register.class);
                 startActivity(i);
             }
         });




         btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {


                EditText mail=(EditText)findViewById(R.id.fullName);
                EditText pass=(EditText)findViewById(R.id.Password);
                e = mail.getText().toString();
                p = pass.getText().toString();
                 try {
                     signing(e,p);
                 }
                 catch (Exception e){
                     Toast.makeText(Login.this, "Authentication failed."+e,
                             Toast.LENGTH_SHORT).show();
                 }


             }
         });




    }


    public void updateUI(FirebaseUser user){

        if(user != null){

            Intent i = new Intent(Login.this, MainActivity.class);
            startActivity(i);
        }



    }

    public void signing(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed."+task.getException(),
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });






    }



}
