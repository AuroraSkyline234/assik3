package service;

import interfaces.IInventoryService;

import exception.InvalidInputException;
import exception.ResourceNotFoundException;
import interfaces.IGenericRepository;
import model.GameItem;
import utils.SortingUtils;
import java.util.List;

public class InventoryService implements IInventoryService {

    private final IGenericRepository<GameItem> repository;

    public InventoryService(IGenericRepository<GameItem> repository) {
        this.repository = repository;
    }

    public void createNewItem(GameItem item) {
        if (item.getWeight() <= 0) {
            throw new InvalidInputException("ERROR: " + item.getName() + " cannot have weight <= 0");
        }
        if (item.getGold_value() < 0) {
            throw new InvalidInputException("ERROR: Price cannot be negative!");
        }
        repository.create(item);
    }

    public GameItem findItem(int id) {
        GameItem item = repository.getById(id);
        if (item == null) {
            throw new ResourceNotFoundException("Item with id " + id + " not found");
        }
        return item;
    }

    public void deleteItem(int id) {
        GameItem item = repository.getById(id);
        if (item == null) {
            throw new ResourceNotFoundException("Item with id " + id + " not found");
        }
        repository.delete(id);
    }

    public void updateItem(GameItem item) {
        if (item.getGold_value() < 0) {
            throw new InvalidInputException("ERROR: Cannot update price to negative!");
        }
        if (repository.getById(item.getId()) == null) {
            throw new ResourceNotFoundException("Item with id " + item.getId() + " not found");
        }
        repository.update(item);
    }

    @Override
    public List<GameItem> getAllItemsSortedByPrice() {
        List<GameItem> items = repository.getAll();
        return SortingUtils.sortByPriceThenName(items);
    }
}
