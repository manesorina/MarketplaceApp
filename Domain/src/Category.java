import java.util.Map;

public class Category implements Identifiable{
    public int id;
    public CategoryName name;

    public Category(CategoryName name) {
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
