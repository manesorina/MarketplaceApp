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

    public boolean authenticate(String userName, String password){
        List<User> users = userRepo.findByCriteria(user -> user.getUserName().equals(userName) && user.getPassword().equals(password));
        User user = users.getFirst();
        return user != null;
    }



    public boolean sendOffer(User seller, String buyerUsername, String buyerPassword, Offer offer) {
        if (authenticate(buyerUsername, buyerPassword)) {
            List<Product> products = productRepo.findByCriteria(product -> product.getListedBy().equals(seller));
            if (!products.isEmpty()) {

                offer.setSender(findByCriteriaHelper(buyerUsername, buyerPassword));
                offer.setReciever(seller);

                offerRepo.create(offer);
                return true;
            }
        }
        return false;
    }


    public boolean acceptOffer(String sellerUsername, String sellerPassword, Offer offer) {
        if (authenticate(sellerUsername, sellerPassword)) {
            if (offer.getReciever().equals(findByCriteriaHelper(sellerUsername, sellerPassword))) {
                offer.setStatus(true);
                return true;
            }
        }
        return false;
    }

    public boolean declineOffer(String sellerUsername, String sellerPassword, Offer offer){
        if(authenticate(sellerUsername,sellerPassword)){
            if(offer.getReciever().equals(findByCriteriaHelper(sellerUsername,sellerPassword))){
                offer.setStatus(false);
                return true;
            }
        }
        return false;
    }

    public List<Offer> displayOffers(String username, String password) {
        List<Offer> personalOffers = new ArrayList<>();
        User user = findByCriteriaHelper(username, password);
        if (user != null) {
            List<Offer> offers = offerRepo.getAll();
            for (Offer offer : offers) {
                if (offer.getSender().equals(user) || offer.getReciever().equals(user)) {
                    personalOffers.add(offer);
                }
            }
        }
        return personalOffers;
    }



    public boolean placeOrder(User seller, Order order) {
        User buyer = order.getBuyer();

        if (buyer == null || !authenticate(buyer.getUserName(), buyer.getPassword())) {
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

        if(reviewer!= null && authenticate(reviewer.getUserName(),reviewer.getPassword())){

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


    public boolean deleteReview(String username, String password) {
        User user = findByCriteriaHelper(username, password);
        if (user != null) {
            List<Review> reviews = reviewRepo.getAll();
            for (Review review : reviews) {
                if (review.getReviewer().equals(user)) {
                    reviewRepo.delete(review.getId());
                    return true;
                }
            }
        }
        return false;
    }




    public List<Order> displayOrders(String userName, String password){
       List<Order> personalOrders=new ArrayList<>();
       User user=findByCriteriaHelper(userName,password);
        if(user!=null){
            List<Order>orders=orderRepo.getAll();
            for(Order order:orders){
                if(order.getBuyer().equals(user)){
                    personalOrders.add(order);
                }
           }
       }
        return personalOrders;
    }



    public boolean addToFavorites(String userName, String password,Product product){
        User user=findByCriteriaHelper(userName,password);
        if(user!=null){
            if(product!=null && !user.favourites.contains(product)){
                user.favourites.add(product);
                int newNrOfLikes= product.getNrLikes()+1;
                product.setNrLikes(newNrOfLikes);
                return true;
            }
        }
        return false;

    }

    public boolean removeFromFavourites(String userName,String password, Product product){
        User user=findByCriteriaHelper(userName,password);
        if(user!=null){
            if(product!=null && user.favourites.contains(product)){
                user.favourites.remove(product);
                return true;
            }
        }
        return false;
    }

    public List<Product> displayFavourites(String userName, String password){
        User user=findByCriteriaHelper(userName,password);
        if(user!=null){
            return user.getFavourites();
        }
        return new ArrayList<>();
    }

    public boolean listProduct(String userName,String password, Product product){
        User user=findByCriteriaHelper(userName,password);
        if(user!=null){
            if(product!=null && !user.listedProducts.contains(product)){
                user.listedProducts.add(product);
                return true;
            }
        }
        return false;
    }

    public boolean deleteListedProduct(int productId,String userName,String password){
        User user=findByCriteriaHelper(userName,password);
        if(user!=null){
            if(productRepo.read(productId)){
                user.listedProducts.remove(product);
                return true;
            }
        }
        return false;
    }

    public List<Product> displayListedProducts(String userName, String password){
        User user=findByCriteriaHelper(userName,password);
        if(user!=null){
            return user.getListedProducts();
        }
        return new ArrayList<>();
    }


    public User findByCriteriaHelper(String username,String password){
        if(authenticate(username,password)){
            List<User> users=userRepo.findByCriteria(user -> user.getUserName().equals(username));
            return users.getFirst();
        }
        return null;
    }









}

