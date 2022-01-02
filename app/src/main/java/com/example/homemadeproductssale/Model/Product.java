package com.example.homemadeproductssale.Model;

public class Product {

    public static final String tableName = "Product";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String IMAGE = "image";

    public int id, image, price;
    public String name;

    public static final String CREATE_TABLE = "CREATE TABLE " + tableName + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT, " +
            PRICE + " TEXT, " +
            IMAGE + " BLOB )";

    public Product(int id, int image, String name, int price) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;

    }

    public Product(){}

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
