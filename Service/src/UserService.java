public class UserService extends VisitorService{

    IMRepository<Order> orderRepo;

    public UserService(IMRepository<User> userRepo, IMRepository<Product> productRepo,
                       IMRepository<Review> reviewRepo, IMRepository<Order> orderRepo) {
        super(userRepo, productRepo, reviewRepo);
        this.orderRepo=orderRepo;

    }

    //public boolean authenticate(String userName, String password){

    //}






}

