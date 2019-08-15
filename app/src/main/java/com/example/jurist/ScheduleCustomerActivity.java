package com.example.jurist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ScheduleCustomerActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    //private EditText mSearchField;
    private Button mSearchBtn;


    private RecyclerView mResultList;

    private DatabaseReference mUserDatabase;

    private String citySelected;
    public static String caseType;
    public static String cashType;


    public static CustomOnSelectedListener x = new CustomOnSelectedListener();

    public static CustomListener y = new CustomListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_customer);

        mUserDatabase = FirebaseDatabase.getInstance().getReference("Lawyers");

        Spinner spinner = (Spinner) findViewById(R.id.city_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ScheduleCustomerActivity.this,
                R.array.city, R.layout.color_spinner_layout);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource( R.layout.spinner_dropdown_layout);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);



        Spinner spinner1 = (Spinner) findViewById(R.id.case_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(ScheduleCustomerActivity.this,
                R.array.case_type, R.layout.color_spinner_layout);
// Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource( R.layout.spinner_dropdown_layout);
// Apply the adapter to the spinner
        spinner1.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(x);



        Spinner spinner2 = (Spinner) findViewById(R.id.cash_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(ScheduleCustomerActivity.this,
                R.array.cash_type, R.layout.color_spinner_layout);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource( R.layout.spinner_dropdown_layout);
// Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);

        spinner2.setOnItemSelectedListener(y);


       // mSearchField = (EditText) findViewById(R.id.search_field);
        mSearchBtn = (Button) findViewById(R.id.search_btn);

        mResultList = (RecyclerView) findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //String searchText = mSearchField.getText().toString();

                firebaseUserSearch(citySelected,x.caseSelected1,y.cashSelected);

            }
        });



    }

    private void firebaseUserSearch(String city, final String casetype, String cash) {

        Toast.makeText(ScheduleCustomerActivity.this, "Started Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = mUserDatabase.orderByChild("district").startAt(city).endAt(city + "\uf8ff");
        FirebaseRecyclerAdapter<Lawyers, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Lawyers, UsersViewHolder>(

                Lawyers.class,
                R.layout.list_layout,
                UsersViewHolder.class,
                firebaseSearchQuery


        ) {
            @Override
            protected void populateViewHolder(UsersViewHolder viewHolder, Lawyers model, int position) {
                //caseType = model.getCase_type();
                //cashType = model.getCash_type();
                viewHolder.setDetails(getApplicationContext(), model.getName(), model.getLastName(),model.getLocation(),model.getCase_type(),model.getCash_type());



            }
        };

        mResultList.setAdapter(firebaseRecyclerAdapter);

    }

    @Override
    public void onClick(View v) {



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        citySelected=parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    // View Holder Class

    public static class UsersViewHolder extends RecyclerView.ViewHolder {


        View mView;

        public UsersViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

        }

        public void setDetails(Context ctx, String userName, String userLastName,String userLocation,String userCaseType,String userCashType){

            TextView user_name = (TextView) mView.findViewById(R.id.name_text);
            TextView user_last_name = (TextView) mView.findViewById(R.id.status_text);
            TextView user_location = (TextView) mView.findViewById(R.id.location_text);
            TextView user_case_type = (TextView) mView.findViewById(R.id.case_type);
            TextView user_cash_type = (TextView) mView.findViewById(R.id.cash_type);




            user_name.setText(userName);
            user_last_name.setText(userLastName);
            user_location.setText(userLocation);
            user_case_type.setText(userCaseType);
            user_cash_type.setText(userCashType);




        }






    }//holder class
    /*public void sendMessage(View view)
    {
        Intent intent = new Intent(ScheduleCustomerActivity.this, PaymentStart.class);
        startActivity(intent);
    }*/


}
