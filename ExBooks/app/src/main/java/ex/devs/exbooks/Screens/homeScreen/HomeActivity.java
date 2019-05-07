package ex.devs.exbooks.Screens.homeScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import ex.devs.exbooks.R;
import ex.devs.exbooks.Screens.BookAddingScreen.BookAddingActivity;
import ex.devs.exbooks.Screens.booksOfCategoryScreen.BooksOfCategoryActivity;
import ex.devs.exbooks.Screens.chatHistoryScreen.ChatsActivity;
import ex.devs.exbooks.Screens.myBooksScreen.MyBooksActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,HomeContract.HomeViewInterface  {
    HomeContract.HomePresenterInterface homePresenterInterface;
    private RecyclerView categoriesRecyleView;
    private RecyclerView.Adapter categoryAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolbar;
    TextView navigationViewHeader;
    public static final String TOPIC_TAG="topic";
    //test



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FirebaseMessaging.getInstance().subscribeToTopic("firstTopic")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = getString(R.string.topic);
                        if (!task.isSuccessful()) {
                            msg = getString(R.string.topicFailed);
                        }
                        Log.d(TOPIC_TAG, msg);
                       // Toast.makeText(HomeActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        mCollapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing);
        mToolbar=(Toolbar)findViewById(R.id.toolbar);
        mCollapsingToolbarLayout.setTitleEnabled(true);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        mToolbar.setTitle("ExBooks");
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorHighlith));
        mCollapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorHighlith));

        homePresenterInterface = new HomePresenterImp(this);
        categoriesRecyleView = (RecyclerView) findViewById(R.id.categoriesRecyleView);
        categoriesRecyleView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        categoriesRecyleView.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this);
        categoriesRecyleView.setAdapter(categoryAdapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        categoriesRecyleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BooksOfCategoryActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton addBookFab = (FloatingActionButton) findViewById(R.id.fab);
        addBookFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, BookAddingActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);
        navigationViewHeader=view.findViewById(R.id.navigationViewHeader);
        navigationViewHeader.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        navigationView.setItemIconTintList(null);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
           this.finishAffinity();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_chats) {
            // Handle the camera action
            Intent intent=new Intent(this, ChatsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_myBooks) {
            Intent intent=new Intent(this, MyBooksActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_logOut) {
            homePresenterInterface.signOut();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
