package com.example.exbooks.Screens.BookAddingScreen;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exbooks.R;
import com.example.exbooks.model.Book;
import com.example.exbooks.model.ImageStorageService;

import java.io.File;
import java.io.IOException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.api.Places;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookAddingActivity extends AppCompatActivity implements BookAddingContract.BookAddingView {

    static final int REQUEST_IMAGE_CAPTURE   = 1;
    static final int REQUEST_GET_SINGLE_FILE = 2 ;

    static final int IMAGE_BITMAP = 1 ;
    static final int IMAGE_URI    = 2 ;
    int imgType = 0 ;

    ImageView imageView = null;
    ImageStorageService imageStorageService ;
    Bitmap image_Bitmap ;
    Uri image_Uri ;

    BookAddingContract.BookAddingPresenter bookAddingPresenter ;
    Place returnedPlace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_adding);
        bookAddingPresenter = new BookAddingPresenterImpl(this);
        imageView = findViewById(R.id.imageView);
        imageStorageService = new ImageStorageService();
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

        Button uploadCam = findViewById(R.id.captureBtn);
        uploadCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturePhoto();

            }
        });

        Button uploadGall = findViewById(R.id.uploadImgBtn);
        uploadGall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPhoto();
            }
        });


        Button addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText title = findViewById(R.id.titleET);
                EditText desc  = findViewById(R.id.descET);
                String categ = spinner.getSelectedItem().toString();
                System.out.println("Category : "+categ);

                Book book = new Book();
                book.setTitle(title.getText().toString());
                book.setDescription(desc.getText().toString());
                book.setCategory(categ);
                book.setLocation("Maadi");

                if(imgType == IMAGE_BITMAP){
                    String url  = imageStorageService.storeImageBitmap(image_Bitmap,book.getTitle(),book.getTitle());
                    book.setImgUrl(url);


                }else if(imgType == IMAGE_URI){

                }

                bookAddingPresenter.addBook(book);
                Toast.makeText(BookAddingActivity.this, "Book is added Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


    private void capturePhoto(){

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void uploadPhoto(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),REQUEST_GET_SINGLE_FILE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
            image_Bitmap = imageBitmap ;
            imgType = IMAGE_BITMAP ;

        }
        else if(requestCode == REQUEST_GET_SINGLE_FILE && resultCode == RESULT_OK){
            Uri selectedImageUri = data.getData();

            if (selectedImageUri!=null)
            {
                imageView.setImageURI(selectedImageUri);
                image_Uri = image_Uri ;
                imgType = IMAGE_URI ;
            }
        }

       Places.initialize (getApplicationContext(),new String("AIzaSyAsN6Y4KcSYrJEYZKwo9dxNEIdMDVEvnZ8"));
        // Initialize the AutocompleteSupportFragment.
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
        // Specify the types of place data to return.
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));
            autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                returnedPlace=place;
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("autoCompleteError", "An error occurred: " + status);
            }
        });

    }


}
