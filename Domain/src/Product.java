public class Product implements Identifiable{

    int id;
    private Category category;
    String color;
    int size;
    double price;
    String brand;
    String condition;
    int nrViews;

    public Product(String color, int size, double price, String brand, String condition, int nrViews) {
        this.color = color;
        this.size = size;
        this.price = price;
        this.brand = brand;
        this.condition = condition;
        this.nrViews = nrViews;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

