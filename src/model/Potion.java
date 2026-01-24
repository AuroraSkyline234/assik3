package model;

public class Potion extends GameItem {
    private int healAmount;
    public Potion (int id, String name, double weight, int gold_value, int healAmount){
        super(id, name, weight, gold_value);
        this.healAmount = healAmount;
    }

    @Override
    public void use() {
        System.out.println("You used " + getName() + " and hp + " + healAmount);
    }
    public int getHealAmount() {
        return healAmount;
    }
}
