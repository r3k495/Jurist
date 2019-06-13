package com.example.jurist;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profileActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private TextView name;
    private TextView last;
    private TextView email;
    private TextView phone;
    private TextView reference;
    private DataSnapshot dataSnapshot;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    private DatabaseError databaseError;
    private String lawyername;
    private String lawyerlast;
    private String lawyeremail;
    private String lawyerphone;
    private String lawyerreference;
    private static boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        name=(TextView) findViewById(R.id.profile_name);
        last=(TextView) findViewById(R.id.profile_last);
        email=(TextView) findViewById(R.id.profile_email);
        phone=(TextView) findViewById(R.id.profile_phone);
        reference=(TextView) findViewById(R.id.profile_reference);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().getRoot();
        databaseReference.child("Lawyers").child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lawyername=(String) dataSnapshot.child("first_name").getValue();
                lawyerlast=(String) dataSnapshot.child("last_name").getValue();
                lawyeremail=(String) dataSnapshot.child("email").getValue();
                lawyerphone=(String) dataSnapshot.child("phone_no").getValue();
                lawyerreference=(String) dataSnapshot.child("reference_no").getValue();



                name.setText("First Name:  "+lawyername);
                last.setText("Last Name:  "+lawyerlast);
                email.setText("Email Address:  "+lawyeremail);
                phone.setText("Phone Number:  "+lawyerphone);
                reference.setText("Reference Number:  "+lawyerreference);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
