package vn.edu.csc.furnitureapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Categories implements Serializable {
    String name;
    ArrayList<Furniture> arrayList;
    String image;
    int id;


    public Categories() {
    }

    public Categories(String name, ArrayList<Furniture> arrayList, String image, int id) {
        this.name = name;
        this.arrayList = arrayList;
        this.image = image;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categories(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Categories(String name, String image, int id) {
        this.name = name;
        this.image = image;
        this.id = id;
    }

    public Categories(String name, ArrayList<Furniture> arrayList) {
        this.name = name;
        this.arrayList = arrayList;
    }

    public Categories(String name, ArrayList<Furniture> arrayList, String image) {
        this.name = name;
        this.arrayList = arrayList;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Furniture> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Furniture> arrayList) {
        this.arrayList = arrayList;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
