package com.example.jurist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScheduleActivity extends AppCompatActivity implements View.OnClickListener {

    EditText date;
    Button send_button;
    Button view_button;
    event_list event;
    FirebaseDatabase database; // = FirebaseDatabase.getInstance();
    DatabaseReference myRef; // = database.getReference();
    private FirebaseAuth firebaseAuth;
    private String firebaseUserid;
    private String dates;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        date = (EditText) findViewById(R.id.date);

        send_button = (Button) findViewById(R.id.button);
        view_button = (Button) findViewById(R.id.view_button);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Events");
        event = new event_list();
        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();

        view_button.setOnClickListener(this);


    }
    //date validation
    public static boolean isValidDate(final String dates) {

        Pattern pattern;
        Matcher matcher;
        final String DATE_PATTERN = "[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9]";
        pattern = Pattern.compile(DATE_PATTERN);
        matcher = pattern.matcher(dates);


        return matcher.matches();

    }


    public void setValues(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        firebaseUserid= user.getUid();
        dates = date.getText().toString();



        //date validation

        if(!isValidDate(dates)){
            Toast.makeText(this,"Please enter valid date & time", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            event = new event_list(dates,firebaseUserid);
            String id=myRef.push().getKey();
            myRef.child(id).setValue(event);
            Toast.makeText(ScheduleActivity.this,"Created Successfully",Toast.LENGTH_SHORT).show();
        }

    }




    public void Send(View view) {



        send_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setValues();


            }
        });

        /* fordebugging the eventlister


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

        /*String UID = Array.getUID();
        myRef.child(UID).setValue(user);
                FirebaseUser user = firebaseAuth.getCurrentUser();
                firebaseUserid= user.getUid();
                dates = date.getText().toString();
                event = new event_list(dates,firebaseUserid);
                String id=myRef.push().getKey();
                myRef.child(id).setValue(event);
                Toast.makeText(ScheduleActivity.this,"Created Successfully",Toast.LENGTH_SHORT).show();
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
