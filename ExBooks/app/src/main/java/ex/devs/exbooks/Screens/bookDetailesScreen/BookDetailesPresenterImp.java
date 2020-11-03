package ex.devs.exbooks.Screens.bookDetailesScreen;

import android.content.Context;
import androidx.annotation.NonNull;
import android.widget.ImageView;

import ex.devs.exbooks.model.ImageDownloaderService;
import ex.devs.exbooks.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BookDetailesPresenterImp implements BookDetailesContract.BookDetailesPresenterInterface {
    BookDetailesContract.BookDetailesViewInterface bookDetailesViewInterface;
    ImageDownloaderService imageDownloaderService ;
    public BookDetailesPresenterImp(BookDetailesContract.BookDetailesViewInterface bookDetailesViewInterface) {
        this.bookDetailesViewInterface=bookDetailesViewInterface;
        imageDownloaderService = new ImageDownloaderService();
    }

    public void getBookImg(String imgUrl, ImageView bookImgView){
        imageDownloaderService.loadWithGlide(imgUrl,getContext(),bookImgView);
    }

    @Override
    public Context getContext() {
        return (Context) bookDetailesViewInterface;
    }


    @Override
    public void getBookOwnerPhone(String bookOwnerUID) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users").child(bookOwnerUID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User bookOwner = dataSnapshot.getValue(User.class);
                bookDetailesViewInterface.setOwnerPhone(bookOwner.getPhone());
                System.out.println("ownerphone= "+bookOwner.getPhone());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
