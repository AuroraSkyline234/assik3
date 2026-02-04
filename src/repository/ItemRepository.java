package repository;

import model.GameItem;
import model.Potion;
import model.Weapon;
import interfaces.IGenericRepository;
import utils.DatabaseConnection;
import exception.DatabaseOperationException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemRepository implements IGenericRepository<GameItem> {

    @Override
    public void create(GameItem item) {
        String sql = "INSERT INTO items (name, weight, gold_value, damage, heal_amount, type, backpack_id) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, item.getName());
            stmt.setDouble(2, item.getWeight());
            stmt.setInt(3, item.getGold_value());

            if (item instanceof Weapon) {
                stmt.setInt(4, ((Weapon) item).getDamage());
                stmt.setInt(5, 0);
                stmt.setString(6, "WEAPON");
            } else if (item instanceof Potion) {
                stmt.setInt(4, 0);
                stmt.setInt(5, ((Potion) item).getHealAmount());
                stmt.setString(6, "POTION");
            }
            stmt.setInt(7, item.getBackpackid());

            stmt.executeUpdate();
            System.out.println("Item " + item.getName() + " saved successfully.");

        } catch (SQLException e) {
            throw new DatabaseOperationException("DB create error: " + e.getMessage(), e);
        }

    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM items WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Item with id " + id + " deleted.");
            } else {
                System.out.println("Item with id " + id + " not found.");
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("DB delete error: " + e.getMessage(), e);
        }

    }

    @Override
    public GameItem getById(int id) {
        String sql = "SELECT * FROM items WHERE id = ?";
        GameItem loaded = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    loaded = mapRowToItem(rs);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("DB getById error: " + e.getMessage(), e);
        }

        return loaded;
    }

    @Override
    public void update(GameItem item) {
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
    @Override
    public List<GameItem> getAll() {
        List<GameItem> items = new ArrayList<>();
        String sql = "SELECT * FROM items";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                GameItem item = mapRowToItem(rs);
                if (item != null) {
                    items.add(item);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("DB getAll error: " + e.getMessage(), e);
        }

        return items;
    }

    private GameItem mapRowToItem(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        double weight = rs.getDouble("weight");
        int gold = rs.getInt("gold_value");
        String type = rs.getString("type");
        int backpackId = rs.getInt("backpack_id");

        GameItem item = null;
        if ("WEAPON".equals(type)) {
            int damage = rs.getInt("damage");
            item = new Weapon(id, name, weight, gold, damage);
        } else if ("POTION".equals(type)) {
            int heal = rs.getInt("heal_amount");
            item = new Potion(id, name, weight, gold, heal);
        }

        if (item != null) {
            item.setBackpackid(backpackId);
        }
        return item;
    }
}