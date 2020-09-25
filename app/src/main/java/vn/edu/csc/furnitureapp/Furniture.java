package vn.edu.csc.furnitureapp;

import java.io.Serializable;

public class Furniture implements Serializable {
    String name;
    String description;
    String image;
    Categories categories;
    int id;
    int categotiesID;

    public Furniture() {
    }

    public Furniture(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Furniture(String name, String description, String image, Categories categories, int id) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.categories = categories;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public int getCategotiesID() {
        return categotiesID;
    }

    public void setCategotiesID(int categotiesID) {
        this.categotiesID = categotiesID;
    }
}
