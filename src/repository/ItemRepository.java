package repository;

import model.GameItem;
import model.Potion;
import model.Weapon;
import utils.DatabaseConnection;

import java.sql.*;
public class ItemRepository {
    public void saveitem(GameItem item){
        String sql = "INSERT INTO items (name, weight, gold_value, damage, heal_amount, type, backpack_id) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, item.getName());
            stmt.setDouble(2,item.getWeight());
            stmt.setInt(3,item.getGold_value());
            if (item instanceof Weapon){
                stmt.setInt(4,((Weapon) item).getDamage());
                stmt.setInt(5,0);
                stmt.setString(6,"WEAPON");
            } else if(item instanceof Potion){
                stmt.setInt(4,0);
                stmt.setInt(5,((Potion) item).getHealAmount());
                stmt.setString(6,"POTION");
            }
            stmt.setInt(7, item.getBackpackid());
            stmt.executeUpdate();
            System.out.println("Item " + item.getName() + " saved");

        } catch (Exception e) {
            System.out.println("Error sql: " + e.getMessage());
        }
    }
    public void Deleteitem(int id){
        String SQL = "DELETE FROM items WHERE id = ?";
        try(Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setInt(1,id);
            int rowAffected = stmt.executeUpdate();
            if (rowAffected > 0){
                System.out.println("Item with id " + id + " successfully deleted");
            } else {
                System.out.println("Item with id " + id + " not found");
            }
        } catch (SQLException e) {
            System.out.println("Deletion error: " + e.getMessage());
        }
    }
    public GameItem getItembyId(int id) {
        String sql = "SELECT * FROM items WHERE id = ?";
        GameItem loaded = null;
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    double weight = rs.getDouble("weight");
                    int gold = rs.getInt("gold_value");
                    String type = rs.getString("type");
                    if ("WEAPON".equals(type)) {
                        int damage = rs.getInt("damage");
                        loaded = new Weapon(id, name, weight, gold, damage);
                    }
                    if ("POTION".equals(type)) {
                        int heal = rs.getInt("heal_amount");
                        loaded = new Potion(id, name, weight, gold, heal);
                    }
                    if (loaded != null) {
                        loaded.setBackpackid(rs.getInt("backpack_id"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("SEARCH ERROR: " + e.getMessage());
        }
        return loaded;
    }
    public void updateItem(GameItem item) {
        String sql = "UPDATE items SET name = ?, weight = ?, gold_value = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getName());
            stmt.setDouble(2, item.getWeight());
            stmt.setInt(3, item.getGold_value());
            stmt.setInt(4, item.getId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Item updated successfully!");
            } else {
                System.out.println("Item not found for update.");
            }
        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
        }
    }
}
