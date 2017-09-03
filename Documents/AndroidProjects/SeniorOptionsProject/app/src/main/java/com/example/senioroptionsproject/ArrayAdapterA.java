package com.example.senioroptionsproject;

import android.widget.ArrayAdapter;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ListView;
import android.graphics.Typeface;
import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by alexlu on 6/1/17.
 */

public class ArrayAdapterA extends ArrayAdapter {
    private ArrayList <FoodItem> foodsArray;
    private ArrayList <String> blockedConditions;
    private ListView mListView;
    //private ArrayList <String> blockedConditionsChanger;
    public ArrayAdapterA(Context c, int r,  ArrayList p, ArrayList b)
    {
        super(c,r,p);
        foodsArray = p;
        blockedConditions=b;
        //mListView = l;

    }
    public View getView(int position, View convertView, ViewGroup parent) {
        for (FoodItem f : foodsArray)
        {

        }

        View returnedView = super.getView(position, convertView, parent);

            if (foodsArray.get(position).getName().equals(foodsArray.get(position).getName().toUpperCase())) {

                ((TextView) returnedView).setTypeface(null, Typeface.BOLD);
               // Toast.makeText(getContext(), ((TextView) returnedView).getText(), Toast.LENGTH_SHORT).show();
            }
            else
            {
                ((TextView) returnedView).setTypeface(null, Typeface.NORMAL);
            }

        return returnedView;
    }


}
