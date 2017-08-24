package com.example.abhishek.movies.model;

import java.util.ArrayList;

/**
 * Created by abhishek on 24/08/17.
 */

public class CrewModels {
    private ArrayList<CrewModel> list;

    public CrewModels(){
        list = new ArrayList<>();
    }

    public void addCrew(CrewModel crewModel){
        list.add(crewModel);
    }

    public CrewModel getCrewModel(int position){
        return list.get(position);
    }


}
