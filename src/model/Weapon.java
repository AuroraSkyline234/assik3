package model;

public class Weapon extends GameItem {
    private int damage;
    public Weapon(int id, String name, double weight, int gold_value, int damage) {
        super(id, name, weight, gold_value);
        this.damage = damage;
    }
    public void use(){
        System.out.println("You used " + getName() + " and dealt " + damage + " damage!!");
    }

    public int getDamage() {
        return damage;
    }
    @Override
    public String getType() {
        return "WEAPON";
    }

}
