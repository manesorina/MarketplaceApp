public class Main {
    public static void main(String[] args) {
        IMRepository<User> userRepo = new IMRepository<User>();
        IMRepository<Admin> adminRepo = new IMRepository<Admin>();
        IMRepository<Product> productRepo = new IMRepository<Product>();
        IMRepository<Review> reviewRepo = new IMRepository<Review>();
        IMRepository<Offer> offerRepo = new IMRepository<Offer>();
        IMRepository<Order> orderRepo = new IMRepository<Order>();
        IMRepository<Category> categoryRepo = new IMRepository<>();
        VisitorService visitorService = new VisitorService(userRepo, productRepo, reviewRepo);
        UserService userService = new UserService(userRepo, productRepo, reviewRepo, orderRepo, offerRepo);
        AdminService adminService = new AdminService(userRepo, productRepo, reviewRepo, adminRepo, categoryRepo);
        Controller controller = new Controller(adminService, userService, visitorService);
        ConsoleApp console = new ConsoleApp(controller);
        console.start();
    }
}
