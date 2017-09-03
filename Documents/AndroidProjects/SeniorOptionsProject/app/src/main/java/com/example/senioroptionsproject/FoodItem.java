package com.example.senioroptionsproject;

/**
 * Created by alexlu on 5/22/17.
 */
import java.util.ArrayList;
import java.util.Collections;
public class FoodItem implements Comparable <FoodItem>  {
    private String name;
    private String category;
    public FoodItem(String n, String c)
    {
        name=n;
        category=c;
    }

    public String getName()
    {
        return name;
    }

    public String getCategory()
    {
        return category;
    }
    public int compareTo(FoodItem i)
    {
        return this.category.compareTo(i.getCategory());
    }
    public String toString()
    {
        return name;
    }


}
