package com.example.foodselector.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.foodselector.Food;
import com.example.foodselector.FoodPreferences;
import com.example.foodselector.FoodSelector;
import com.example.foodselector.R;

import com.example.foodselector.databinding.FragmentHomeBinding;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ArrayList<ArrayList<Food>> foods;
    private FoodPreferences foodPref;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // 初始化 foodPref 和 foods
        foods = new ArrayList<>();
        readRecipesFromFile();
        foodPref = new FoodPreferences();
        initializeUserPreferences();

        Button randomFoodButton = root.findViewById(R.id.random_food_button);
        randomFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Food selectedFood = FoodSelector.selectRandomFood(foods, foodPref);
                if (selectedFood != null) {
                    Toast.makeText(getActivity(), "随机选中的食物是：" + selectedFood.name, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "没有符合偏好的食物可选。", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }

    private void readRecipesFromFile() {
        // 实现从文件读取食谱的方法
    }

    private void initializeUserPreferences() {
        // 实现初始化用户偏好的方法
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