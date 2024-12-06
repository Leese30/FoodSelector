package com.example.foodselector;

import java.util.ArrayList;
import java.util.Random;

public class FoodSelector {

    public static Food selectRandomFood(ArrayList<ArrayList<Food>> foods, FoodPreferences foodPref) {
        ArrayList<Food> allFoods = new ArrayList<>();
        ArrayList<Double> weights = new ArrayList<>();
        double totalWeight = 0.0;

        for (ArrayList<Food> restaurantFoods : foods) {
            for (Food food : restaurantFoods) {
                if (foodPref.pigMeet && food.preferences.charAt(food.preferences.length() - 1) == '1') {
                    continue;
                }
                allFoods.add(food);
                double weight = calculateWeight(food, foodPref);
                weights.add(weight);
                totalWeight += weight;
            }
        }

        if (totalWeight == 0.0) {
            return null;
        }

        Random rand = new Random();
        double randomValue = rand.nextDouble() * totalWeight;
        double cumulativeWeight = 0.0;

        for (int i = 0; i < allFoods.size(); i++) {
            cumulativeWeight += weights.get(i);
            if (randomValue <= cumulativeWeight) {
                return allFoods.get(i);
            }
        }
        return null;
    }

    private static double calculateWeight(Food food, FoodPreferences foodPref) {
        double totalWeight = 0.0;
        for (int i = 0; i < 8; i++) {
            if (food.preferences.charAt(i) == '1') {
                switch (i) {
                    case 0:
                        totalWeight += foodPref.meet;
                        break;
                    case 1:
                        totalWeight += foodPref.vegetable;
                        break;
                    case 2:
                        totalWeight += foodPref.eastern;
                        break;
                    case 3:
                        totalWeight += foodPref.western;
                        break;
                    case 4:
                        totalWeight += foodPref.chinese;
                        break;
                    case 5:
                        totalWeight += foodPref.spicy;
                        break;
                    case 6:
                        totalWeight += foodPref.seafood;
                        break;
                    case 7:
                        totalWeight = 0;
                        break;
                }
            }
        }
        return totalWeight;
    }
}