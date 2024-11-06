public class VisitorService {
    private final IMRepository<User> userRepo;
    private final IMRepository<Product> productRepo;

    public VisitorService(IMRepository<User> userRepo, IMRepository<Product> productRepo) {
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }


}
