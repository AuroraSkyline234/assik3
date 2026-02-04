import controller.InventoryController;
import interfaces.IInventoryService;
import model.Potion;
import model.Weapon;
import repository.ItemRepository;
import service.InventoryService;
import utils.ReflectionUtil;

public class Main {
    public static void main(String[] args) {
        ItemRepository repo = new ItemRepository();
        IInventoryService service = new InventoryService(repo); // интерфейс слева
        InventoryController controller = new InventoryController(service);

        System.out.println("Demo");
        Weapon sword = new Weapon(0, "Iron Sword", 5.0, 120, 25);
        sword.setBackpackid(1);

        Potion potion = new Potion(0, "Healing Potion", 1.0, 60, 50);
        potion.setBackpackid(2);

        ReflectionUtil.inspectClass(sword);

        try {
            controller.createItem(sword);
            controller.createItem(potion);
        } catch (Exception e) {
            System.out.println("Create error: " + e.getMessage());
        }

        System.out.println(" ITEMS SORTED BY PRICE (lambda)");
        controller.listItemsSortedByPrice()
                .forEach(item -> item.printInfo());

        System.out.println(" VALIDATION FAIL DEMO");
        try {
            Weapon badItem = new Weapon(0, "Bug Sword", -2.0, -10, 999);
            badItem.setBackpackid(1);
            controller.createItem(badItem);
        } catch (Exception e) {
            System.out.println("Expected validation exception: " + e.getMessage());
        }

        System.out.println("NOT FOUND DEMO");
        try {
            controller.getItem(999999);
        } catch (Exception e) {
            System.out.println("Expected not found exception: " + e.getMessage());
        }


        System.out.println("DELETE DEMO");
        try {
            controller.deleteItem(8);
            System.out.println("Delete attempted for id=5");
        } catch (Exception e) {
            System.out.println("Delete exception: " + e.getMessage());
        }
    }
}
