public interface IRepository<T> {

    public void create(T object);
    public void read(int id);
    public void update(T object);
    public void delete(T object);
}
