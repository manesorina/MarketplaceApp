package Domain;

public class Category implements Identifiable {
    private int id;
    private CategoryName name;

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

    public CategoryName getName() {
        return name;
    }

    public void setName(CategoryName name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Domain.Category{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
