package ex.devs.exbooks.model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import ex.devs.exbooks.Screens.BookAddingScreen.BookAddingContract;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class ImageStorageService extends AsyncTask<Bitmap,Void, String> {

    FirebaseStorage storage ;
    StorageReference storageReference ;
    Bitmap imageBitmap ;
    Uri imageUri ;
    String imgUrl;
    String bookTitle , imgName;
    BookAddingContract.BookAddingPresenter presenter;
    int x=0;

    public ImageStorageService(BookAddingContract.BookAddingPresenter presenter, String bookTitle, String imgName){
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("books");
        this.bookTitle = bookTitle ;
        this.imgName = imgName ;
        this.presenter = presenter ;

    }

    @Override
    protected String doInBackground(Bitmap... bitmaps) {
        x++;
        return storeImageBitmap(bitmaps[0],bookTitle,imgName);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        x++;

        System.out.println("onpostExecute:\n"+s);
        System.out.println(x);
        presenter.setBook(s);
    }

    public String storeImageBitmap(Bitmap imageBitmap , String bookTitle , String imgName){
        imgUrl = null ;
        this.imageBitmap = imageBitmap ;
        StorageReference ref = storageReference.child(bookTitle);
        StorageReference  imgRef = ref.child(imgName);
        ref = imgRef.getParent();


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] data = byteArrayOutputStream.toByteArray();
        UploadTask uploadTask = imgRef.putBytes(data);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("testing uploading img","failed");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.i("testing uploading img","success");
                imgUrl = imgRef.getPath();
                System.out.println("-----------------");
                System.out.println(imgUrl);

            }
        });
        while(imgUrl==null){}
        return imgUrl ;
    }
/*
    public String storeImageUri(Uri imageUri , String bookTitle){
        imgUrl = null ;
        this.imageUri = imageUri ;
        StorageReference ref = storageReference.child("ayktab2/"+this.imageUri.getLastPathSegment());
//        StorageReference ref = storageReference.child("images");
        //      StorageReference riversRef = ref.child("images/"+uri.getLastPathSegment());
        UploadTask uploadTask = ref.putFile(uri);

        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.i("testing","Uploading failed");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.i("testing","uploaded successfullly");

            }
        });
    }
}
*/

}
