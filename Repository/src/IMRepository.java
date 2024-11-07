import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IMRepository<T extends Identifiable> implements IRepository<T> {
    private final Map<Integer, T> storage = new HashMap<>();
    private int currentId = 1;

    @Override
    public void create(T object) {
        object.setId(currentId);
        storage.put(currentId++, object);
    }

    @Override
    public T read(int id) {
        return storage.get(id);
    }

    @Override
    public void update(T object) {
        if (object instanceof Identifiable identifiableEntity) {
            storage.put(identifiableEntity.getId(), object);
        } else {
            throw new IllegalArgumentException("Entity must implement Identifiable interface.");
        }
    }

    @Override
    public T delete(int id) {
        return storage.remove(id);
    }



    public List<T> getAll() {
        return new ArrayList<>(storage.values());
    }


}


