package com.example.restaurant;

public class Restaurant {
   private   String name;
   private   String city;
   private   String category;
    private String photo;
    private String price;


    public Restaurant() {
    }

    public Restaurant(String name, String city, String category, String photo,String price) {
        this.name = name;
        this.city = city;
        this.category = category;
        this.photo = photo;
        this.price=price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


}
