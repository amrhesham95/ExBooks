package com.example.exbooks.Screens.homeScreen;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.exbooks.R;
import com.example.exbooks.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    List<Category> categoryList ;

    public CategoryAdapter(List<Category> categoryList) {
        categoryList =new ArrayList<Category>();
        categoryList.add(new Category("History",R.drawable.ic_favorite_black_24dp));
        categoryList.add(new Category("Kids",R.drawable.ic_favorite_black_24dp));
        categoryList.add(new Category("Computer&Technology",R.drawable.ic_favorite_black_24dp));
        categoryList.add(new Category("Cooking",R.drawable.ic_favorite_black_24dp));
        categoryList.add(new Category("Education&Reference",R.drawable.ic_favorite_black_24dp));
        categoryList.add(new Category("Health&Fittness",R.drawable.ic_favorite_black_24dp));
        categoryList.add(new Category("Entertainment",R.drawable.ic_favorite_black_24dp));
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
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryIcon;
        TextView categoryName;
        public CategoryViewHolder( View itemView) {
            super(itemView);
            categoryIcon=(ImageView) itemView.findViewById(R.id.category_image);
            categoryName=(TextView)itemView.findViewById(R.id.categoryName);

        }
    }
}
