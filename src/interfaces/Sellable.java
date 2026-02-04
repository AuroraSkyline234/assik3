package interfaces;

public interface Sellable {
    int getGold_value();

    default String getPriceTag() {
        return "Price: " + getGold_value() + " gold coins.";
    }

    static boolean isValidPrice(int price) {
        return price >= 0;
    }
}
