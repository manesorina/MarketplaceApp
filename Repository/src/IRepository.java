import java.util.List;

public interface IRepository<T> {
    public void create(T object);
    public T read(int id);
    public void update(T object);
    public T delete(int id);
    List<T> getAll();
}
