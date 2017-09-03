package com.example.senioroptionsproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by alexlu on 5/22/17.
 */

public class ViewDatabase extends AppCompatActivity {
    private static final String TAG = "ViewDatabase";
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private String userID;
    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_database_layout); //We need to make this file
        mListView = (ListView)(findViewById(R.id.listview));
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        mAuthListener = new FirebaseAuth.AuthStateListener()
        {
            @Override
            public void onAuthStateChanged (FirebaseAuth firebaseAuth){
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully Signed In with " + user.getUid());
                }
                else {
                    Log.d(TAG,"onAuthStateChanged:signed_out");
                    //toastMessage("Successfully Signed Out");
                }

            }

        };
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showdata(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void showdata(DataSnapshot dataSnapshot) {

        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            AllergyInformation aInfo = new AllergyInformation();
            aInfo.setDairy(ds.child("Chopt").getValue(AllergyInformation.class).getDairy());
            aInfo.setEggs(ds.child("Chopt").getValue(AllergyInformation.class).getEggs());
            aInfo.setGluten(ds.child("Chopt").getValue(AllergyInformation.class).getGluten());
            aInfo.setSesameSeeds(ds.child("Chopt").getValue(AllergyInformation.class).getSesameSeeds());
            aInfo.setShellfish(ds.child("Chopt").getValue(AllergyInformation.class).getShellfish());
            aInfo.setSoy(ds.child("Chopt").getValue(AllergyInformation.class).getSoy());
            aInfo.setTreeNuts(ds.child("Chopt").getValue(AllergyInformation.class).getTreeNuts());
            //Must change back to userID
            Log.d(TAG, "showdata:dairy" + aInfo.getDairy());
            ArrayList<String> array = new ArrayList<String>();
            //array.add(aInfo.getDairy());

        }
    }


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
}
