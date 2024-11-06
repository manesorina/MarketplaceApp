public class Product implements Identifiable{

    int id;
    private Category category;
    String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getNrViews() {
        return nrViews;
    }

    public void setNrViews(int nrViews) {
        this.nrViews = nrViews;
    }
}

