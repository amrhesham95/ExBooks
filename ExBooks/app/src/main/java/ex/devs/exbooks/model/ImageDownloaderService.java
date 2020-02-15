package ex.devs.exbooks.model;

import android.content.Context;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ImageDownloaderService {

    public void loadWithGlide(String url , Context context , ImageView imageView) {
        // [START storage_load_with_glide]
        // Reference to an image file in CloudStorage
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(url);


        // ImageView in your Activity
        //imageView = findViewById(R.id.imageViewDownload);

        // Download directly from StorageReference using Glide
        // (See MyAppGlideModule for Loader registration)
        System.out.println(storageReference);
//        GlideApp.with(context /* context */)
//                .load(storageReference).override(300,200)
//                .into(imageView);

            GlideApp.with(context).load(storageReference).into(imageView);


        // [END storage_load_with_glide]
    }


}
