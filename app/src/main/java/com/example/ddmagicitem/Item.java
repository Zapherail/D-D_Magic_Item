package com.example.ddmagicitem;

public class Item {

    private String name;
    private String type;
    private String rarity;
    int image;

    public Item(String name, String type, String rarity, int image) {
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getRarity() {
        return rarity;
    }

    public int getImage() {
        return image;
    }
}
