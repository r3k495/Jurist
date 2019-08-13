package com.example.jurist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.data.model.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity implements View.OnClickListener {

    EditText date;
    Button send_button;
    Button view_button;
    event_list event;
    FirebaseDatabase database; // = FirebaseDatabase.getInstance();
    DatabaseReference myRef; // = database.getReference();
    private FirebaseAuth firebaseAuth;
    private String firebaseUserid;

    List<String> events = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        date = (EditText) findViewById(R.id.date);

        send_button = (Button) findViewById(R.id.button);
        view_button = (Button) findViewById(R.id.view_button);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Lawyers");
        event = new event_list();
        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();
        view_button.setOnClickListener(ScheduleActivity.this);


    }


    private void getValues() {

       event.setDate(date.getText().toString());
       //event.setUID("1");

    }

    public void Send(View view) {


        send_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                getValues();

                FirebaseUser user = firebaseAuth.getCurrentUser();
                firebaseUserid= user.getUid();


                events.add(date.getText().toString());
                for(String friend : events) {
                    myRef.child(firebaseUserid).child("Events").child(friend).setValue(true);
                    //rootRef.child("friends").child(friend).setValue(true);
                }

                Toast.makeText(ScheduleActivity.this, "Date Inserted", Toast.LENGTH_LONG);

            }
        });



        /*


        myRef.addValueEventListener(new ValueEventListener() {


            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {

                getValues();
        /*String UID = Array.getUID();
        myRef.child(UID).setValue(user);
                FirebaseUser user = firebaseAuth.getCurrentUser();
                firebaseUserid= user.getUid();


                events.add(event);

                myRef.child(firebaseUserid).child("Events").setValue(events);
                Toast.makeText(ScheduleActivity.this, "Date Inserted", Toast.LENGTH_LONG);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }); */

    }


    @Override
    public void onClick(View v) {
        if(v==view_button){

        }
        switch (v.getId()) {
            case R.id.view_button:
                Intent intent = new Intent(ScheduleActivity.this, eventHistory.class);
                startActivity(intent);

        }

    }
}
