package com.example.exbooks.Screens.homeScreen;

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

import com.example.exbooks.R;
import com.example.exbooks.Screens.booksOfCategoryScreen.BooksOfCategoryActivity;
import com.example.exbooks.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    List<Category> categoryList ;
    Context context;

    public CategoryAdapter(Context mContext) {
        context=mContext;
        categoryList =new ArrayList<Category>();
        categoryList.add(new Category("History",R.drawable.ic_pyramids));
        categoryList.add(new Category("Kids",R.drawable.ic_ball));
        categoryList.add(new Category("Computer&Technology",R.drawable.ic_video));
        categoryList.add(new Category("Cooking",R.drawable.ic_cookingcolor));
        categoryList.add(new Category("Education&Reference",R.drawable.ic_classroom_color));
        categoryList.add(new Category("Health&Fittness",R.drawable.ic_health));
        categoryList.add(new Category("Entertainment",R.drawable.ic_entertinment));
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
