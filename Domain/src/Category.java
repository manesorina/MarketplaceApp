import java.util.Map;

public class Category {
    Map<String,Integer> categories;

    public Category(Map<String, Integer> categories) {
        this.categories = categories;
    }

    public boolean addToCategory(Object product){};
    public boolean removeFromCategory(Object product){};
}
