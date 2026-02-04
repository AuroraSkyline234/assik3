package model;

import java.util.ArrayList;
import java.util.List;
public class Backpack {
    private int id;
    private String owner;
    private List<GameItem> items = new ArrayList<>();
    public Backpack(int id,String owner){
        this.id = id;
        this.owner = owner;
    }
    public void addItem(GameItem item){
        items.add(item);
        System.out.println(item.getName() + " put in backpack " + owner);
    }
    public void showContents(){
        System.out.println("Player backpack: " + owner);
        if (items.isEmpty()){
            System.out.println("model.Backpack empty");
        } else {
            for (GameItem item : items){
                System.out.println("--" + item.getName() + "| weight: " + item.getWeight());
            }
        }
    }
    public List<GameItem> getItems(){
        return items;
    }

}