package com.example.springserver.dir;

import java.util.ArrayList;

public class Fields {
    public String tempTitle;
    public ArrayList<ListItem> list;

    public Fields(){
        tempTitle="";
        list = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Fields{" +
                "tempTitle='" + tempTitle + '\'' +
                ", list=" + list +
                '}';
    }
}
