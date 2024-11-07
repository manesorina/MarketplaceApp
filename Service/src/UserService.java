public class UserService extends VisitorService{
    public UserService(IMRepository<User> userRepo, IMRepository<Product> productRepo,
                       IMRepository<Review> reviewRepo) {
        super(userRepo, productRepo, reviewRepo);
    }
}

