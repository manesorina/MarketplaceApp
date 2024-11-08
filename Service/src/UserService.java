import java.util.ArrayList;
import java.util.List;

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

    public boolean placeOrder(User seller, Order order) {
        User buyer = order.getBuyer();

        if (buyer == null || !authenticate(buyer.getId(), buyer.getUserName(), buyer.getPassword())) {
            return false;
        }

        for (Product product : order.getProducts()) {
            Product fetchedProduct = productRepo.read(product.getId());
            if (fetchedProduct == null || !fetchedProduct.getListedBy().equals(seller)) {
                return false;
            }
        }

        order.setTotalPrice(order.getTotalPrice());
        orderRepo.create(order);
        return true;
    }


    public boolean writeReview(Review review){
        User reviewer=review.getReviewer();
        User reviewee=review.getReviewee();

        if(reviewer!= null && authenticate(reviewer.getId(),reviewer.getUserName(),reviewer.getPassword())){

            List<Order> orders=orderRepo.getAll();
            for(Order order:orders){
                if(order.getProducts().stream().anyMatch(product -> product.getListedBy().equals(reviewee))
                && order.getId()==reviewer.getId()){

                    reviewRepo.create(review);
                    return true;
                }
            }
        }
        return false;
    }


    public boolean deleteReview( int userId){
        User user=userRepo.read(userId);
        List<Review> reviews=reviewRepo.getAll();
        if( user != null && authenticate(userId, user.getUserName(), user.getPassword())){
            for(Review review:reviews){
                if(review.getReviewer().equals(user)){
                    reviewRepo.delete(review.getId());
                    return true;
                }
            }
        }
        return false;
    }




    public List<Order> getOrders(int userID){
        User user= userRepo.read(userID);
       List<Order> personalOrders=new ArrayList<>();
        if(user!= null && authenticate(user.getId(),user.getUserName(),user.getPassword())){
           List<Order>orders=orderRepo.getAll();
           for(Order order:orders){
                if(order.getBuyer().equals(user)){
                    personalOrders.add(order);
                }
           }
       }
        return personalOrders;
    }










}

