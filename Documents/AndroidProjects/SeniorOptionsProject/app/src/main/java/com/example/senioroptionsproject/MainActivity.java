package com.example.senioroptionsproject;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;
import android.widget.*;
import java.util.*;
import java.lang.*;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.*;
import com.google.firebase.database.*;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.android.gms.tasks.Task;

/*import com.google.firebase.database.DataSnapshot;
//import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
//import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.*;*/

public class MainActivity extends AppCompatActivity
{
    private String allergyInput;
    private String userId;
    private FoodItemArray ita;
    private String restaurantNameInput;
    private String initialAllergyInput;
    private boolean initial=false;
    private ArrayList<String> allergyValues;
    private static final String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText email, password;
    private Button login;
    private ArrayList<FoodItem> itemsAllergicTo;
    private ArrayList<FoodItem> savedItems;
    ArrayList<String> allAllergies;
    private ArrayList<String> blockedConditions;
    private Button signUp;
    private ArrayList <FoodItem> itemsAT;
    private ArrayList <String> allergiesOfUserGlobal;
    private DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference mConditionRef = mRootRef; //mRootRef.child("Chopt");
    private DatabaseReference mAllergiesRef;
    //private DatabaseReference myRef;
    //GoogleApiClient mGoogleApiClient;
    private ListView mListView;
    private static final String TAG2 = "ViewDatabase";
    private FirebaseDatabase mFireBaseDatabase;

    //String ref = firebase.database().ref("Panera");
    //private Button logout;

    private void toastMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //mRootRef.child("allergies").setValue("banana");
        email=(EditText) findViewById(R.id.eMail);
        password=(EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.loginButton);

        allergyValues = new ArrayList<String>();
        allergyValues.add("dairy");
        allergyValues.add("eggs");
        allergyValues.add("gluten");
        allergyValues.add("peanuts");
        allergyValues.add("sesame_seeds");
        allergyValues.add("shellfish");
        allergyValues.add("soy");
        allergyValues.add("tree_nuts");
        allergyInput = "";
        restaurantNameInput = "";
        initialAllergyInput = "";
        allergiesOfUserGlobal = new ArrayList<String>();
        blockedConditions=new ArrayList<String>();
        itemsAllergicTo = new ArrayList<FoodItem>();
        savedItems = new ArrayList<FoodItem>();
         ita=new FoodItemArray();
        ita.setFoods(itemsAllergicTo);
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        //GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)

                //.addConnectionCallbacks(this)
                //.addOnConnectionFailedListener(this)
                //.addApi(LocationServices.API)
                //.build();




            mAuthListener = new FirebaseAuth.AuthStateListener()
            {
                @Override
                public void onAuthStateChanged (FirebaseAuth firebaseAuth){
                FirebaseUser user = firebaseAuth.getCurrentUser();

                    if (user != null) {
                        // User is signed in
                        Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                        toastMessage("Successfully Signed In ");

                        userId = user.getUid();

                         allAllergies = getAllergies(); //getAllergies(initialAllergyInput);
                        //toastMessage(allAllergies.toString())

                        if (allAllergies.size()!=0) {

                        for (String singleAllergy : allAllergies) {
                            mRootRef.child(userId).child(singleAllergy).setValue(true);
                        }
                        for (String singleAllergy : allergyValues) {
                            if (allAllergies.indexOf(singleAllergy) == -1)
                                mRootRef.child(userId).child(singleAllergy).setValue(false);
                        } }
                        setContentView(R.layout.activity_main);
                    }

                else {
                    Log.d(TAG,"onAuthStateChanged:signed_out");

                    //toastMessage("Successfully Signed Out");
                }

            }

        };


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email1 = email.getText().toString();
                String pass1 = password.getText().toString();
                if (!email1.equals("") && !pass1.equals("") ) {
                    Task<AuthResult> tasks = mAuth.signInWithEmailAndPassword(email1, pass1);
                    tasks.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if (e.toString().equals("com.google.firebase.auth.FirebaseAuthInvalidCredentialsException: " +
                                    "The password is invalid or the user does not have a password."))
                            {
                                toastMessage("Invalid Password");
                            }
                            else if (e.toString().equals("com.google.firebase.auth.FirebaseAuthInvalidCredentialsException: " +
                                    "The email address is badly formatted."))
                            {
                                toastMessage("Invalid Username");
                            }

                        }
                    });
                }
                else
                {
                    toastMessage("You didn't fill in the fields");
                }
            }
        });


    }

    public void logoutOnClick (View V)
    {
        mAuth.signOut();
        toastMessage("signed out");
        setContentView(R.layout.activity_main2);
    }

    public void loginOnClick(View V)
    {
        //toastMessage("loginOnClick");
        email=(EditText) findViewById(R.id.eMail);
        password=(EditText) findViewById(R.id.password);
        String email1 = email.getText().toString();
        String pass1 = password.getText().toString();
        if (!email1.equals("") && !pass1.equals(""))
        {
            Task<AuthResult> tasks = mAuth.signInWithEmailAndPassword(email1, pass1);
            tasks.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (e.toString().equals("com.google.firebase.auth.FirebaseAuthInvalidCredentialsException: " +
                            "The password is invalid or the user does not have a password."))
                    {
                        toastMessage("Invalid Password");
                    }
                    else if (e.toString().equals("com.google.firebase.auth.FirebaseAuthInvalidCredentialsException: " +
                            "The email address is badly formatted."))
                    {
                        toastMessage("Invalid Username");
                    }

                }
            });
        }
        else
        {
            toastMessage("You didn't fill in the fields");
        }
    }


    public void buttonOnClick(View v) {


        Button myButton = (Button) (findViewById(R.id.submitButton));
        Spinner spinnerObj = (Spinner)(findViewById(R.id.spinner1));
        restaurantNameInput = String.valueOf(spinnerObj.getSelectedItem());
        //myButton.setText(restaurantNameInput);
        mAllergiesRef= (mRootRef.child(userId));
        //toastMessage(mConditionRef.toString());
//        logout.setOnClickListener(new View.OnClickListener(){
//                                      @Override
//                                      public void onClick (View v) {
//                                          mAuth.signOut();
//                                          toastMessage("Signed out");
//                                      }
//                                  }
//        );
        mConditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              //  toastMessage("addValueEventListener has been called");
                ArrayList<String> allergiesOfUser = new ArrayList<String>();
                ArrayList <String> keys = new ArrayList<String>();
                itemsAllergicTo = new ArrayList<FoodItem>();
                ita.setFoods(itemsAllergicTo);
                Iterable <DataSnapshot> snapshotAllergy = dataSnapshot.child(userId).getChildren();
                for (DataSnapshot d:snapshotAllergy) {
                    if ((boolean)d.getValue())
                    allergiesOfUser.add(d.getKey());
                }
                for(String a:allergiesOfUser)
                {
                    Iterable<DataSnapshot> snapshots = dataSnapshot.child(restaurantNameInput).child(a).getChildren();
                    for (DataSnapshot d : snapshots)
                    {

                         if(keys.indexOf(d.getKey()) == -1 && !d.getKey().toLowerCase().equals("false"))
                        {
                            itemsAllergicTo.add(new FoodItem(d.getKey(), d.getValue(String.class)));

                            keys.add(d.getKey());
                        }
                    }



                }

                ita.setFoods(itemsAllergicTo);
                itemsAllergicTo=ita.compareTo();
                String previous_category="";
                for (int i=0;i<itemsAllergicTo.size();i++) {
                    String str = itemsAllergicTo.get(i).getCategory();
                    if (!(itemsAllergicTo.get(i).getCategory().equals(previous_category))) {
                        previous_category = itemsAllergicTo.get(i).getCategory();
                        blockedConditions.add(itemsAllergicTo.get(i).getCategory());
                    }
                }
                for (FoodItem i : itemsAllergicTo)
                {
                    savedItems.add(new FoodItem(i.getName(), i.getCategory()));
                }
                outputData();

            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//

    }

    public void outputData()
    {
        setContentView(R.layout.view_database_layout);

        mListView = (ListView)(findViewById(R.id.listview));
        //itemsAllergicTo=ita.compareTo();
       itemsAllergicTo=new ArrayList<FoodItem>();
        for (FoodItem i : savedItems)
        {
            itemsAllergicTo.add(new FoodItem(i.getName(), i.getCategory()));
        }
        String previous_category="";


        for (int i=0;i<itemsAllergicTo.size();i++)
        {
            String str=itemsAllergicTo.get(i).getCategory();
            if(!(itemsAllergicTo.get(i).getCategory().equals(previous_category)))
            {

                while (str.indexOf("_")!=-1)
                {

                    str=str.substring(0,str.indexOf("_"))+" " + str.substring(str.indexOf("_")+1);

                }
                if ((!(str.toLowerCase().equals("false"))) && (!(str.toLowerCase().equals("null")))){
                    itemsAllergicTo.add(i, new FoodItem(str.toUpperCase(), itemsAllergicTo.get(i).getCategory()));
                    previous_category = itemsAllergicTo.get(i).getCategory();
                }

                i++;
            }
        }

        for (int i=0; i<itemsAllergicTo.size(); i++)
        {
            for (int j=0; j<blockedConditions.size(); j++)
            {
                if (itemsAllergicTo.get(i).getCategory().equals(blockedConditions.get(j))
                        && !itemsAllergicTo.get(i).getName().equals(itemsAllergicTo.get(i).getName().toUpperCase()))
                {
                    j=blockedConditions.size();
                    itemsAllergicTo.remove(i);
                    i--;
                }
            }
        }

        if (itemsAllergicTo.size()==0) {
            ((TextView)(findViewById(R.id.foodItems))).setText("This restaurant does not use your allergens in its dishes");
        }
//        for(String a: blockedConditions)
//        for (FoodItem i: itemsAllergicTo)
//        {
//            if(i.getName().toUpperCase().equals(a))
//            {
//                    ((TextView)(convertView.findViewById(R.id.listview)).setTypeface(null, Typeface.BOLD));
//            }
//        }
        ita.setFoods(itemsAllergicTo);

        ArrayAdapterA adapter = new ArrayAdapterA(this, R.layout.display_layout, ita.compareTo(), blockedConditions); //, mListView);
        //ArrayAdapter adapter = new ArrayAdapter(this, R.layout.display_layout, itemsAllergicTo);
        mListView.setAdapter(adapter);

        //((TextView)mListView.getChildAt(0)).setTypeface(null, Typeface.BOLD);
        //((TextView)mListView.getAdapter().getView(0, null, mListView)).setTypeface(null, Typeface.BOLD);

        //FoodItemArray x=ita;
//        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.display_layout, item.compareTo());
//        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             /*ArrayList<FoodItem> items = ita.remove(itemsAllergicTo.get(position).getCategory());

                for(FoodItem i : items)
                {
                    toastMessage(i.toString());
                };
                  FoodItemArray x=new FoodItemArray(items);

//                ArrayAdapter a = new ArrayAdapter(this, R.layout.display_layout, ita.compareTo());
//                mListView.setAdapter(a);*/
                boolean contains=false;
                for (int k = 0; k<blockedConditions.size(); k++)
                {
                    if (itemsAllergicTo.get(position).getCategory().equals(blockedConditions.get(k)))
                    {
                        blockedConditions.remove(k);
                        k=blockedConditions.size();
                        contains=true;
                    }
                }
                if (!contains) {
                    blockedConditions.add(itemsAllergicTo.get(position).getCategory());
                }
                outputData();

            }
        });


    }

    public void organizeByCategory()
    {
//        ArrayList <FoodItem> foods=new ArrayList();
//        for(int i=0; i< itemsAllergicTo.size()-1;i++)
//        {
//             if(itemsAllergicTo.get(i))
//        }

    }

    public void backToRestaurantChoice(View v)
    {
        setContentView(R.layout.activity_main);
        savedItems = new ArrayList<FoodItem>();
        blockedConditions = new ArrayList<String>();
    }

    public void backToLogin(View v) { setContentView(R.layout.activity_main2);}

    public void signupUsernameOnClick(View v)
    {
        EditText usernameText = (EditText)(findViewById(R.id.eMail2));
        if(usernameText.getText().toString().equals("email:          ")) {
            usernameText.setText("");
        }
    }

    public void updateAllergiesOnClick(View v)
    {
        setContentView(R.layout.edit_allergies);
        ((CheckBox) findViewById(R.id.edit_dairy)).setActivated(true);
    }

    public void updateAllergies(View v)
    {
        allAllergies=new ArrayList<>();
        if (((CheckBox) findViewById(R.id.edit_dairy)).isChecked())
            allAllergies.add("dairy");
        if (((CheckBox) findViewById(R.id.edit_eggs)).isChecked())
            allAllergies.add("eggs");
        if (((CheckBox) findViewById(R.id.edit_gluten)).isChecked())
            allAllergies.add("gluten");
        if (((CheckBox) findViewById(R.id.edit_peanuts)).isChecked())
            allAllergies.add("peanuts");
        if (((CheckBox) findViewById(R.id.edit_sesame_seeds)).isChecked())
            allAllergies.add("sesame_seeds");
        if (((CheckBox) findViewById(R.id.edit_shellfish)).isChecked())
            allAllergies.add("shellfish");
        if (((CheckBox) findViewById(R.id.edit_soy)).isChecked())
            allAllergies.add("soy");
        if (((CheckBox) findViewById(R.id.edit_tree_nuts)).isChecked())
            allAllergies.add("tree_nuts");

        for (String singleAllergy : allAllergies) {
            mRootRef.child(userId).child(singleAllergy).setValue(true);
        }

        for (String singleAllergy : allergyValues) {
            if (allAllergies.indexOf(singleAllergy) == -1)
                mRootRef.child(userId).child(singleAllergy).setValue(false);
        }

        toastMessage("Updated");
        setContentView(R.layout.activity_main);
    }

    public void backFromAllergyEditingOnClick(View v)
    {
        setContentView(R.layout.activity_main);
    }

    public void signupOnClick(View v) {
        setContentView(R.layout.signup);
    }
    public void createAccountOnClick(View v)  {

        String newEmail= ((EditText)(findViewById(R.id.eMail2))).getText().toString();
        String newPassword = ((EditText)(findViewById(R.id.password2))).getText().toString();
        initial=true;

        // [START create_user_with_email]
            if (!(newEmail.equals("") || newPassword.equals(""))) {
                Task<AuthResult> tasks = mAuth.createUserWithEmailAndPassword(newEmail, newPassword);
                tasks.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        if (e.toString().equals("com.google.firebase.auth.FirebaseAuthUserCollisionException: " +
                                "The email address is already in use by another account."))
                        {
                            toastMessage("Email is Already in Use");
                        }
                        else if (e.toString().equals("com.google.firebase.auth.FirebaseAuthInvalidCredentialsException: " +
                                "The email address is badly formatted.")) {
                            toastMessage("Invalid Email");
                        }
                        else
                        {
                            toastMessage("Invalid Password");
                        }
                        //toastMessage(e.toString());
                    }
                });
                tasks.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        toastMessage("Registered with email: " + mAuth.getCurrentUser().getEmail());
                    }
                });
            }
            else
            {
                toastMessage("You didn't fill in the fields");
            }
                //toastMessage("Try again, it failed");
                    /*.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                toastMessage("Authentication Failed");
                                //Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                        //Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                     //       }

                            // [START_EXCLUDE]
                            //hideProgressDialog();
                            // [END_EXCLUDE]
                        }
                    });*/
            // [END create_user_with_email]


    }
    public ArrayList<String> getAllergies()
    {
        final ArrayList<String> allergies_separated= new ArrayList();
        if(initial) {
            if (((CheckBox) findViewById(R.id.dairy)).isChecked())
                allergies_separated.add("dairy");
            if (((CheckBox) findViewById(R.id.eggs)).isChecked())
                allergies_separated.add("eggs");
            if (((CheckBox) findViewById(R.id.gluten)).isChecked())
                allergies_separated.add("gluten");
            if (((CheckBox) findViewById(R.id.peanuts)).isChecked())
                allergies_separated.add("peanuts");
            if (((CheckBox) findViewById(R.id.sesame_seeds)).isChecked())
                allergies_separated.add("sesame_seeds");
            if (((CheckBox) findViewById(R.id.shellfish)).isChecked())
                allergies_separated.add("shellfish");
            if (((CheckBox) findViewById(R.id.soy)).isChecked())
                allergies_separated.add("soy");
            if (((CheckBox) findViewById(R.id.tree_nuts)).isChecked())
                allergies_separated.add("tree_nuts");
        }
//
        return allergies_separated;

        }



    @Override
    public void onStart()
    {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
        //mGoogleApiClient.connect();

    };

    @Override
    public void onStop()
    {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
        //mGoogleApiClient.disconnect();

    };

}
