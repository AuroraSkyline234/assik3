package interfaces;

import model.GameItem;
import java.util.List;

public interface IInventoryService {
    void createNewItem(GameItem item);
    GameItem findItem(int id);
    void deleteItem(int id);
    void updateItem(GameItem item);
    List<GameItem> getAllItemsSortedByPrice();
}

