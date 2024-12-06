package com.example.foodselector.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.foodselector.Food;
import com.example.foodselector.FoodPreferences;
import com.example.foodselector.FoodSelector;
import com.example.foodselector.R;

import java.util.ArrayList;

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
public class HomeFragment extends Fragment {

    private ArrayList<ArrayList<Food>> foods;
    private FoodPreferences foodPref;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // 初始化数据
        initializeData();

        // 设置按钮监听
        root.findViewById(R.id.random_food_button).setOnClickListener(v -> handleRandomFood());
        root.findViewById(R.id.search_food_button).setOnClickListener(v -> handleSearchFood());
        root.findViewById(R.id.add_food_button).setOnClickListener(v -> handleAddFood());
        root.findViewById(R.id.add_restaurant_button).setOnClickListener(v -> handleAddRestaurant());
        root.findViewById(R.id.remind_water_button).setOnClickListener(v -> handleRemindWater());
        root.findViewById(R.id.bmi_calculator_button).setOnClickListener(v -> handleBMICalculator());
        root.findViewById(R.id.reset_data_button).setOnClickListener(v -> handleResetData());

        return root;
    }

    // 初始化数据
    private void initializeData() {
        foods = new ArrayList<>();
        foodPref = new FoodPreferences();

        // 初始化用户偏好
        foodPref.meet = 1.0;
        foodPref.vegetable = 1.0;
        foodPref.spicy = 1.0;
        foodPref.pigMeet = false;
    }

    // 随机选择食物
    private void handleRandomFood() {
        Food selectedFood = FoodSelector.selectRandomFood(foods, foodPref);
        if (selectedFood != null) {
            Toast.makeText(getActivity(), "随机选中的食物是：" + selectedFood.name, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "没有符合偏好的食物可选。", Toast.LENGTH_SHORT).show();
        }
    }

    // 搜索食物功能
    private void handleSearchFood() {
        Toast.makeText(getActivity(), "搜索功能尚未实现！", Toast.LENGTH_SHORT).show();
    }

    // 添加食物功能
    private void handleAddFood() {
        Toast.makeText(getActivity(), "添加食物功能尚未实现！", Toast.LENGTH_SHORT).show();
    }

    // 添加食堂功能
    private void handleAddRestaurant() {
        Toast.makeText(getActivity(), "添加食堂功能尚未实现！", Toast.LENGTH_SHORT).show();
    }

    // 提醒喝水功能
    private void handleRemindWater() {
        Toast.makeText(getActivity(), "记得喝水哦！", Toast.LENGTH_SHORT).show();
    }

    // BMI 计算功能
    private void handleBMICalculator() {
        Toast.makeText(getActivity(), "BMI 计算功能尚未实现！", Toast.LENGTH_SHORT).show();
    }

    // 重置用户数据
    private void handleResetData() {
        foodPref = new FoodPreferences(); // 重置为默认偏好
        Toast.makeText(getActivity(), "用户数据已重置！", Toast.LENGTH_SHORT).show();
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

            // 打印foods的内容，确保数据被正确加载
            Log.d("HomeFragment", "Loaded foods: " + foods.toString());
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

}
