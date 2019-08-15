package com.example.jurist;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;



public class CustomListener implements  OnItemSelectedListener{
    public String cashSelected;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        cashSelected=parent.getItemAtPosition(position).toString();

        //debugging purpose
        //Toast.makeText(this,caseSelected,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}