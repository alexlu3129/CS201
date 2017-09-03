package com.example.senioroptionsproject;

/**
 * Created by alexlu on 5/24/17.
 */
import java.util.Collection;
import java.util.*;
public class FoodItemArray extends ArrayList {
    ArrayList<FoodItem> foods;

    public FoodItemArray(ArrayList<FoodItem> i) {
        foods = i;
    }

    public FoodItemArray() {
        foods = new ArrayList<>();
    }

    public void add(FoodItem i) {
        foods.add(i);
    }

    public ArrayList<FoodItem> compareTo() {

        Collections.sort(foods);
        FoodItem current;
//        if (foods.size()>0) {
//            String previous = foods.get(0).getCategory();
//            for (int i = 0; i < foods.size(); i++) {
//                if (!(foods.get(i).getCategory().equals(previous))) {
//                    if (!(foods.get(i).getName().toUpperCase().equals(foods.get(i).getName()))) {
//                        current = foods.get(i);
//                        foods.remove(i);
//                        foods.add(i + 1, current);
//                    }
//                    previous = foods.get(i).getCategory();
//                }
//
//            }
//        }
        return foods;
    }

    public ArrayList<FoodItem> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<FoodItem> i) {
        foods = i;
    }

    public void delete() {
        foods = new ArrayList();
    }

    public ArrayList<FoodItem> find(String category) {
        ArrayList<FoodItem> current = new ArrayList<>();
        for (FoodItem i : foods) {
            if (i.getCategory().equals(category))
                current.add(i);

        }
        return current;
    }

    public ArrayList<FoodItem> remove(String category) {
        ArrayList<FoodItem> changer = foods;
        for (int i = 0; i < changer.size(); i++) {
            if (changer.get(i).getCategory().equals(category)) {
                changer.remove(i);
                i--;
            }

        }
        return changer;
    }
}
