import exception.InvalidItemException;
import model.Backpack;
import model.GameItem;
import model.Potion;
import model.Weapon;
import java.util.ArrayList;
import repository.ItemRepository;
import service.InventoryService;

public class Main {
    public static void main(String[] args) {
        InventoryService service = new InventoryService();

        try {
            System.out.println("--- Create item ---");
            Weapon sword = new Weapon(0, "Excalibur", 10.5, 1000, 50);
            sword.setBackpackid(1);
            service.createNewItem(sword);

            System.out.println("--- Baditem (exception test) ---");
            Weapon badSword = new Weapon(0, "Broken Sword", -5, 100, 10);
            service.createNewItem(badSword);

        } catch (InvalidItemException e) {
            System.out.println("Cath error: " + e.getMessage());
        }

        System.out.println("--- Update  ---");
        GameItem item = service.findItem(1);
        if (item != null) {
            System.out.println("Name was: " + item.getName());
            item.setName("LEGENDARY Excalibur");

            try {
                service.updateItem(item);
                GameItem updated = service.findItem(1);
                System.out.println("It became a name" + updated.getName());
            } catch (InvalidItemException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}