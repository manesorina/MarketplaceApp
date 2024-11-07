import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VisitorService {
    private final IMRepository<User> userRepo;
    private final IMRepository<Product> productRepo;
    private final IMRepository<Review> reviewRepo;

    public VisitorService(IMRepository<User> userRepo, IMRepository<Product> productRepo, IMRepository<Review> reviewRepo) {
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.reviewRepo = reviewRepo;
    }

    public List<User> seeAllUsers() {
        return userRepo.getAll().stream().map(u -> u).collect(Collectors.toList());
    }

    public List<Product> seeAllProducts() {
        return productRepo.getAll().stream().map(u -> u).collect(Collectors.toList());
    }

    public List<Review> seeAllReviews() {
        return reviewRepo.getAll().stream().map(u -> u).collect(Collectors.toList());
    }

    public List<User> searchUsersByUsername(String username) {
        return userRepo.getAll().stream().filter(user -> user.getUserName().toLowerCase()
                        .contains(username.toLowerCase())).collect(Collectors.toList());
    }

    public List<User> searchUsersByScoreRange(double minScore, double maxScore) {
        return userRepo.getAll().stream()
                .filter(user -> user.getScore() >= minScore && user.getScore() <= maxScore)
                .collect(Collectors.toList());
    }

    public List<User> searchUsersByReviewCountRange(double reviewMin, double reviewMax) {
        Map<User, Long> reviewCounts = reviewRepo.getAll().stream()
                .collect(Collectors.groupingBy(Review::getReviewee, Collectors.counting()));
        return reviewCounts.entrySet().stream()
                .filter(entry -> entry.getValue() >= reviewMin && entry.getValue() <= reviewMax)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<Product> searchProductsByName(String name) {
        return productRepo.getAll().stream().filter(product -> product.getName()
                .toLowerCase().contains(name.toLowerCase())).collect(Collectors.toList());
    }

    public List<Product> searchProductsByUsername(String username) {
        return productRepo.getAll().stream().filter(product -> product.getListedBy().getUserName()
                .toLowerCase().contains(username.toLowerCase())).collect(Collectors.toList());
    }

    public List<Product> searchProductsByBrand(String brand) {
        return productRepo.getAll().stream().filter(product -> product.getBrand().toLowerCase()
                .contains(brand.toLowerCase())).collect(Collectors.toList());
    }

    public List<Product> searchProductsByCategory(String category) {
        return productRepo.getAll().stream().filter(product -> product.getCategory().getName().
                name().toLowerCase().contains(category.toLowerCase())).collect(Collectors.toList());
    }

    public List<Product> searchProductsByPriceRange(double minPrice, double maxPrice) {
        return productRepo.getAll().stream()
                .filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public List<Product> searchProductsByCondition(String condition) {
        return productRepo.getAll().stream().filter(product -> product.getCondition().toLowerCase()
                .contains(condition.toLowerCase())).collect(Collectors.toList());
    }

    public List<Product> searchProductsByColor(String color) {
        return productRepo.getAll().stream().filter(product -> product.getColor().toLowerCase()
                .contains(color.toLowerCase())).collect(Collectors.toList());
    }

    public List<Product> searchProductsBySizeRange(double minSize, double maxSize) {
        return productRepo.getAll().stream()
                .filter(product -> product.getSize() >= minSize && product.getSize() <= maxSize)
                .collect(Collectors.toList());
    }

}
