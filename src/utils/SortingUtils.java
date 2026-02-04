package utils;

import model.GameItem;
import java.util.Comparator;
import java.util.List;

public class SortingUtils {

    public static List<GameItem> sortByPriceThenName(List<GameItem> items) {
        items.sort(
                Comparator.comparingInt(GameItem::getGold_value)
                        .thenComparing(GameItem::getName, String.CASE_INSENSITIVE_ORDER)
        );
        return items;
    }

    public static List<GameItem> sortByWeightDesc(List<GameItem> items) {
        items.sort((a, b) -> Double.compare(b.getWeight(), a.getWeight()));
        return items;
    }
}
