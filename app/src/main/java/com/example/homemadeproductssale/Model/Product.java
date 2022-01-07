package com.example.homemadeproductssale.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Product{

    public static final String tableName = "Product";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String IMAGE = "image";

    public int id, image, price;
    public String name;

    public Product(){}


    protected Product(Parcel in) {
        id = in.readInt();
        image = in.readInt();
        price = in.readInt();
        name = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
