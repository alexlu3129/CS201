/*package com.example.senioroptionsproject;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.firebase.auth.FirebaseUser;


import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.*;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;

/**
 * Created by alexlu on 5/18/17.
 */

/*public class AddToDatabase extends AppCompatActivity{
    /*private static final String TAG = "AddToDatabase";
    private Button submit;
    private EditText mAllergies;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mAllergies = (EditText) findViewById(R.id.allergies);
            submit = (Button) findViewById(R.id.submitButton);
            mAuth = FirebaseAuth.getInstance();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myRef = mFirebaseDatabase.getReference();
        /*mAuthListener = (AuthStateListener) (FirebaseAuth)
        {
            @Override
                FirebaseUser user = FirebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully Signed In");
                    setContentView(R.layout.activity_main);
                    Intent intent = new Intent(MainActivity.this, AddToDatabase.class);
                    startActivity(intent);
                }
                else{
                    Log.d(TAG,"onAuthStateChanged:signed_out");
                    //toastMessage("Successfully Signed Out");
                }

            }

        };


    @Override
    public void onStart()
    {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    };

    @Override
    public void onStop()
    {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    };

    private void toastMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}*/
