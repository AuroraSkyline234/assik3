package service;

import model.GameItem;
import repository.ItemRepository;
import exception.InvalidItemException;

public class InventoryService {
    private ItemRepository repository = new ItemRepository();

    public void createNewItem(GameItem item) throws InvalidItemException {
        if (item.getWeight() <= 0) {
            throw new InvalidItemException("ERROR: " + item.getName() + " cannot be 0 or less weight!");
        }
        if (item.getGold_value() < 0) {
            throw new InvalidItemException("ERROR: Price cannot be negative!");
        }
        repository.saveitem(item);
    }

    public GameItem findItem(int id) {
        return repository.getItembyId(id);
    }

    public void deleteItem(int id) {
        repository.Deleteitem(id);
    }

    public void updateItem(GameItem item) throws InvalidItemException {
        if (item.getGold_value() < 0) {
            throw new InvalidItemException("ERROR: Cannot update price to negative!");
        }
        repository.updateItem(item);
    }
}