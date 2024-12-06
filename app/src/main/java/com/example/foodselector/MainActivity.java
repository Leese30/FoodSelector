package com.example.foodselector;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import android.widget.Button;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.example.foodselector.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private ArrayList<ArrayList<Food>> foods;
    private FoodPreferences foodPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foods = new ArrayList<>();
        readRecipesFromFile();

        foodPref = new FoodPreferences();
        initializeUserPreferences();

        Button randomFoodButton = findViewById(R.id.random_food_button);
        randomFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Food selectedFood = FoodSelector.selectRandomFood(foods, foodPref);
                if (selectedFood != null) {
                    Toast.makeText(MainActivity.this, "随机选中的食物是：" + selectedFood.name, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "没有符合偏好的食物可选。", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void readRecipesFromFile() {
        InputStream is = getResources().openRawResource(R.raw.recipes);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String restaurantName = parts[0];
                String foodName = parts[1];
                String preferences = parts[2];

                int restaurantIndex = findRestaurantIndex(restaurantName);
                if (restaurantIndex == -1) {
                    ArrayList<Food> newRestaurant = new ArrayList<>();
                    foods.add(newRestaurant);
                    restaurantIndex = foods.size() - 1;
                }

                Food food = new Food();
                food.id = foods.get(restaurantIndex).size();
                food.name = foodName;
                food.preferences = preferences;
                foods.get(restaurantIndex).add(food);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int findRestaurantIndex(String restaurantName) {
        for (int i = 0; i < foods.size(); i++) {
            if (foods.get(i).get(0).name.equals(restaurantName)) {
                return i;
            }
        }
        return -1;
    }

    private void initializeUserPreferences() {
        // 在这里初始化用户偏好
        // 示例：
        foodPref.meet = 1.0;
        foodPref.vegetable = 1.0;
        foodPref.eastern = 1.0;
        foodPref.western = 1.0;
        foodPref.chinese = 1.0;
        foodPref.spicy = 1.0;
        foodPref.seafood = 1.0;
        foodPref.pigMeet = false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}