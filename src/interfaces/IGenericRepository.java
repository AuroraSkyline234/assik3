package interfaces;
import java.util.List;

public interface IGenericRepository<T> {
    void create(T entity);
    T getById(int id);
    List<T> getAll();
    void update(T entity);
    void delete(int id);
}
