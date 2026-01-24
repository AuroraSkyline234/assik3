package model;

import interfaces.Sellable;
import interfaces.Usable;

public abstract class GameItem implements Sellable, Usable {
    private int id;
    private String name;
    private double weight;
    private int gold_value;
    private  int backpackid;

    public GameItem(int id, String name, double weight, int gold_value) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.gold_value = gold_value;
    }

    public abstract void use();
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getWeight() {
        return weight;
    }
    public int getGold_value() {
        return gold_value;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public void setGold_value(int gold_value) {
        this.gold_value = gold_value;
    }
    public void setBackpackid(int bacpackid) {
        this.backpackid = bacpackid;
    }
    public int getBackpackid() {
        return backpackid;
    }
    @Override
    public String getPriceTag() {
        return "Price: " + this.gold_value + " gold coins.";
    }
}
