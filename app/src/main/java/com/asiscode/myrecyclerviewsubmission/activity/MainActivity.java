package com.asiscode.myrecyclerviewsubmission.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.asiscode.myrecyclerviewsubmission.R;
import com.asiscode.myrecyclerviewsubmission.adapter.CardViewFoodAdapter;
import com.asiscode.myrecyclerviewsubmission.custom.ItemClickSupport;
import com.asiscode.myrecyclerviewsubmission.data.FoodData;
import com.asiscode.myrecyclerviewsubmission.domain.Food;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Food> foods;
    private RecyclerView rvCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Daftar Makanan");
        rvCategory = findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        foods = new ArrayList<>();
        foods.addAll(FoodData.getListData());

        showRecyclerCardView();
    }

    private void showRecyclerCardView() {
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        CardViewFoodAdapter cardViewFoodAdapter = new CardViewFoodAdapter(this);
        cardViewFoodAdapter.setListFoods(foods);
        rvCategory.setAdapter(cardViewFoodAdapter);

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View view) {
                showSelectedFood(foods.get(position));
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("food", foods.get(position));
                startActivity(intent);
            }
        });
    }

    private void showSelectedFood(Food food) {
        Toast.makeText(this, "Kamu memilih " + food.getName(), Toast.LENGTH_SHORT).show();
    }
}
