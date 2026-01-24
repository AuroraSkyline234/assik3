
## 1. Project Overview
A Java-based application to manage RPG game inventories using **JDBC** and **PostgreSQL**. It demonstrates **CRUD operations**, **OOP principles**, and **Exception Handling**.


---

## 2. OOP Design
* **Inheritance:** `GameItem` (Abstract Base Class) is extended by `Weapon` and `Potion`.
* **Interfaces:** Implemented `Sellable` (for price) and `Usable` (for item action).
* **Polymorphism:** The system handles different item types uniformly in the Service layer.
* **Encapsulation:** All fields are `private` with public Getters/Setters.
* **Composition:** `Backpack` entity contains a collection of Items (One-to-Many relation).

---

## 3. Database Schema
The database consists of two tables linked by a **Foreign Key**:
1.  **`backpacks`**: Stores owners (`id`, `owner_name`, `max_weight`).
2.  **`items`**: Stores game items (`id`, `name`, `type`, `backpack_id` FK).

sql
-- Sample Insert
INSERT INTO backpacks (owner_name, max_weight) VALUES ('Dragonborn', 150);
INSERT INTO items (name, type, backpack_id) VALUES ('Steel Sword', 'WEAPON', 1);
## 4. Features & Architecture   

* **CRUD:** Create, Read (Find by ID), Update (Modify stats), Delete.
* **Validation:** Business logic prevents negative weight/price.
* **Exception Handling:** Custom `InvalidItemException` is thrown and caught for invalid inputs.

---

## 5. How to Run
1.  **Database:** Create a PostgreSQL database named `gameinventory`.
2.  **Config:** Update `src/utils/DatabaseConnection.java` with your credentials (`user`, `password`).
3.  **Run:** Execute the `Main.java` file.
4.  **Test:** The console will display the results of CRUD operations and Validation tests.

---

## 6. Reflection
* **Learned:** How to implement JDBC `PreparedStatement`, design custom Exceptions, and structure a multi-layer Java application.
* **Challenges:** Handling Foreign Key constraints (ensuring a Backpack exists before saving an Item) and implementing the Update logic correctly.