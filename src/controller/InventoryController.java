package controller;

import interfaces.IInventoryService;
import model.GameItem;

import java.util.List;

public class InventoryController {

    private final IInventoryService service;

    public InventoryController(IInventoryService service) {
        this.service = service;
    }

    public void createItem(GameItem item) {
        service.createNewItem(item);
    }

    public GameItem getItem(int id) {
        return service.findItem(id);
    }

    public void updateItem(GameItem item) {
        service.updateItem(item);
    }

    public void deleteItem(int id) {
        service.deleteItem(id);
    }

    public List<GameItem> listItemsSortedByPrice() {
        return service.getAllItemsSortedByPrice();
    }
}
