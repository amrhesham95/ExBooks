package ex.devs.exbooks.Screens.homeScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import ex.devs.exbooks.R;
import ex.devs.exbooks.Screens.booksOfCategoryScreen.BooksOfCategoryActivity;
import ex.devs.exbooks.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    List<Category> categoryList ;
    Context context;

    public CategoryAdapter(Context mContext) {
        context=mContext;
        categoryList =new ArrayList<Category>();
        categoryList.add(new Category("Arts&Music",R.drawable.ic_arts));
        categoryList.add(new Category("Biographies",R.drawable.ic_biography));
        categoryList.add(new Category("Business",R.drawable.ic_business));
        categoryList.add(new Category("Comics",R.drawable.ic_comic));
        categoryList.add(new Category("Computer&Technology",R.drawable.ic_computer));
        categoryList.add(new Category("Cooking",R.drawable.ic_cooking));
        categoryList.add(new Category("Education&Reference",R.drawable.ic_education));
        categoryList.add(new Category("Entertainment",R.drawable.ic_entertainment));
        categoryList.add(new Category("Health&Fitness",R.drawable.ic_healthh));
        categoryList.add(new Category("History",R.drawable.ic_history));
        categoryList.add(new Category("Hobbies&Crafts",R.drawable.ic_craft));
        categoryList.add(new Category("Horror",R.drawable.ic_horror));
        categoryList.add(new Category("Kids",R.drawable.ic_kids));
        categoryList.add(new Category("Literature&Fiction",R.drawable.ic_litrature));
        categoryList.add(new Category("Medical",R.drawable.ic_medicine));
        categoryList.add(new Category("Mysteries",R.drawable.ic_mystery));
        categoryList.add(new Category("Romance",R.drawable.ic_romance));
        categoryList.add(new Category("Sci-Fi&Fantasy",R.drawable.ic_sci_fi));
        categoryList.add(new Category("Science&Math",R.drawable.ic_science));
        categoryList.add(new Category("Sports",R.drawable.ic_sports));
        categoryList.add(new Category("Travel",R.drawable.ic_travel));


    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.category_card_layout, viewGroup   , false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);
        return  categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int i) {
    categoryViewHolder.categoryName.setText(categoryList.get(i).getCategoryName());
    categoryViewHolder.categoryIcon.setImageResource(categoryList.get(i).getCategoryIcon());


    categoryViewHolder.cardView.setOnClickListener((event)->{
        Activity activity=(Activity)context;
        Intent intent = new Intent(activity, BooksOfCategoryActivity.class);
        intent.putExtra("categoryName",categoryList.get(i).getCategoryName());
        activity.startActivity(intent);

    });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryIcon;
        TextView categoryName;

        ImageButton goToCategoryBtn;

        CardView cardView;

        public CategoryViewHolder( View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.categoryCardView);
            categoryIcon=(ImageView) itemView.findViewById(R.id.category_image);
            categoryName=(TextView)itemView.findViewById(R.id.categoryName);
            goToCategoryBtn=itemView.findViewById(R.id.goToCategoryBtn);
        }
    }
}
