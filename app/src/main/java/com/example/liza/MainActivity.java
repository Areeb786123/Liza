package com.example.liza;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();


        Button bt = (Button) findViewById(R.id.lg);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mAuth.signOut();
                    Intent i = new Intent(MainActivity.this, Login.class);
                    startActivity(i);

                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Authentication failed."+e,
                            Toast.LENGTH_SHORT).show();
                }

            }
        });


    }



    public void  updateUI(FirebaseUser user){

        if(user == null){
            Intent i = new Intent(MainActivity.this, Login.class);
            startActivity(i);
        }



    }




    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }







    }

