public class UserService extends VisitorService{

    IMRepository<Order> orderRepo;
    IMRepository<Offer> offerRepo;


    public UserService(IMRepository<User> userRepo, IMRepository<Product> productRepo,
                       IMRepository<Review> reviewRepo, IMRepository<Order> orderRepo,IMRepository<Offer> offerRepo) {
        super(userRepo, productRepo, reviewRepo);
        this.orderRepo=orderRepo;
        this.offerRepo=offerRepo;

    }

    public boolean authenticate(int userId,String userName, String password){
        User user= userRepo.read(userId);
        return user != null && user.getUserName().equals(userName) && user.getPassword().equals(password);
    }

    public boolean sendOffer(User seller, int buyerId, Offer offer, int productId){
        User buyer=userRepo.read(buyerId);
        if( buyer != null && authenticate(buyerId, buyer.getUserName(), buyer.getPassword())){
            Product product = productRepo.read(productId);
            if (product != null && product.getListedBy().equals(seller)) {
                offerRepo.create(offer);
                return true;
            }
        }
        return false;
    }

    public boolean acceptOffer(int sellerId, Offer offer){
        User seller=userRepo.read(sellerId);
        if(seller != null && authenticate(sellerId,seller.getUserName(), seller.getPassword())){
            offer.setStatus(true);
        }
        return offer.getStatus();
    }

    public boolean placeOrder(int buyerId, User seller, Order order) {

        User buyer = userRepo.read(buyerId);
        if (buyer == null || !authenticate(buyerId, buyer.getUserName(), buyer.getPassword())) {
            return false;

        }
        for (Product product : order.getProducts()) {
            Product fetchedProduct = productRepo.read(product.getId());
            if (fetchedProduct == null || !fetchedProduct.getListedBy().equals(seller)) {
                return false;
            }
        }
        double totalPrice = 0;
        for (Product product : order.getProducts()) {
            totalPrice += product.getPrice();

        }
        order.setTotalPrice(totalPrice);
        orderRepo.create(order);
        return true;
    }







}

