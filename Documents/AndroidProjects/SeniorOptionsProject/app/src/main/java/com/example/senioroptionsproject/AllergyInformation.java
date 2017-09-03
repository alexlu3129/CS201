package com.example.senioroptionsproject;

import java.util.ArrayList;

/**
 * Created by alexlu on 5/22/17.
 */

public class AllergyInformation {
    private ArrayList<String> dairy;
    private ArrayList<String> eggs;
    private ArrayList<String> gluten;
    private ArrayList<String> sesame_seeds;
    private ArrayList<String> shellfish;
    private ArrayList<String> soy;
    private ArrayList<String> tree_nuts;

    public AllergyInformation()
    {

    }

    public ArrayList<String> getDairy()
    {
        return dairy;
    }

    public ArrayList<String> getEggs()
    {
        return eggs;
    }

    public ArrayList<String> getGluten()
    {
        return gluten;
    }

    public ArrayList<String> getSesameSeeds()
    {
        return sesame_seeds;
    }

    public ArrayList<String> getShellfish()
    {
        return shellfish;
    }

    public ArrayList<String> getSoy()
    {
        return soy;
    }

    public ArrayList<String> getTreeNuts()
    {
        return tree_nuts;
    }


    public void setDairy(ArrayList<String> val)
    {
        dairy=val;
    }

    public void setEggs(ArrayList<String> val)
    {
        eggs=val;
    }

    public void setGluten(ArrayList<String> val)
    {
        gluten=val;
    }

    public void setSesameSeeds(ArrayList<String> val)
    {
        sesame_seeds=val;
    }

    public void setShellfish(ArrayList<String> val)
    {
        shellfish=val;
    }

    public void setSoy(ArrayList<String> val)
    {
        soy=val;
    }

    public void setTreeNuts(ArrayList<String> val)
    {
        tree_nuts=val;
    }

}
