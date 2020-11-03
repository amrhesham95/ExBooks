package ex.devs.exbooks.Screens.myBooksScreen;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import ex.devs.exbooks.R;
import ex.devs.exbooks.Screens.booksOfCategoryScreen.BooksOfCategoryAdapter;
import ex.devs.exbooks.model.Book;

import java.util.ArrayList;

public class MyBooksActivity extends AppCompatActivity implements MyBooksContract.MyBooksViewInterface{
    MyBooksContract.MyBooksPresenterInerface myBooksPresenterInerface;
    private RecyclerView myBooksRecyleView;
    private RecyclerView.Adapter categoryBooksAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Book>myBooks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_books);
        Toolbar toolbar = (Toolbar) findViewById(R.id.mybooktoolbar);
        toolbar.setTitle("My books");
        setSupportActionBar(toolbar);
        myBooksPresenterInerface= new MyBooksPresenterImp(this);
        myBooksRecyleView=(RecyclerView)findViewById(R.id.category_books_recycleView);
        myBooksRecyleView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        myBooksRecyleView.setLayoutManager(layoutManager);
        myBooks=new ArrayList<>();
        myBooksPresenterInerface.getMyBooks();



    }

    @Override
    public void setUserBooks(ArrayList<Book> myBooks) {
        categoryBooksAdapter=new BooksOfCategoryAdapter(this,myBooks,1);
        myBooksRecyleView.setAdapter(categoryBooksAdapter);
    }
}
