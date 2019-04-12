package com.example.exbooks.Screens.BookAddingScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.exbooks.R;

import java.util.ArrayList;
import java.util.List;

public class BookAddingActivity extends AppCompatActivity implements BookAddingContract.BookAddingView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_adding);
        Spinner spinner = findViewById(R.id.categSpinner);

        List<String> categories = new ArrayList<String>();
        categories.add("History");
        categories.add("Kids");
        categories.add("Computers & Tech");
        categories.add("Cooking");
        categories.add("Edu & Refernces");
        categories.add("Health & Fitness");
        categories.add("Entertainment");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();

                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}
