import java.util.*;

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
        try{
            User user = users.getFirst();
        }catch (NoSuchElementException e){
            return false;
        }
        return true;
    }


    //Offer Methods

    public boolean sendOffer( String senderUsername,String senderPassword, String message, Product selectedProduct, double offeredPrice) {
        if (authenticate(senderUsername, senderPassword)) {
            Product product = productRepo.read(selectedProduct.getId());
            if (product!=null) {
                User sender = findByCriteriaHelper(senderUsername, senderPassword);
                User seller = selectedProduct.getListedBy();
                if (seller != null && !seller.getUserName().equals(senderUsername) && offeredPrice>=selectedProduct.getPrice()/2) {
                    Offer offer = new Offer(message, offeredPrice, selectedProduct, false, sender, seller);
                    offerRepo.create(offer);

                    return true;
                }
            }
        }
        return false;
    }



    public boolean acceptOffer(String sellerUsername, String sellerPassword, int offerId) {
        if (authenticate(sellerUsername, sellerPassword)) {
            Offer offer=offerRepo.read(offerId);
            if (offer.getReciever().equals(findByCriteriaHelper(sellerUsername, sellerPassword))) {
                offer.setStatus(true);
                offer.getTargetedProduct().setPrice(offer.getOfferedPrice());
                return true;
            }
        }
        return false;
    }

    public boolean declineOffer(String sellerUsername, String sellerPassword, int offerId){
        if(authenticate(sellerUsername,sellerPassword)){
            Offer offer=offerRepo.read(offerId);
            if(offer.getReciever().equals(findByCriteriaHelper(sellerUsername,sellerPassword))){
                offer.setStatus(false);
                return true;
            }
        }
        return false;
    }


    public List<Offer> displayMadeOffers(String username, String password) {
        List<Offer> personalOffers = new ArrayList<>();
        User user = findByCriteriaHelper(username, password);
        if (user != null && authenticate(username,password)) {
            List<Offer> offers = offerRepo.getAll();
            for (Offer offer : offers) {
                if (offer.getSender().equals(user)){
                    personalOffers.add(offer);
                }
            }
        }
        return personalOffers;
    }
    public List<Offer> displayReceivedOffers(String username, String password) {
        List<Offer> personalOffers = new ArrayList<>();
        User user = findByCriteriaHelper(username, password);
        if (user != null && authenticate(username,password)) {
            List<Offer> offers = offerRepo.getAll();
            for (Offer offer : offers) {
                if (offer.getReciever().equals(user)) {
                    personalOffers.add(offer);
                }
            }
        }
        return personalOffers;
    }

    public List<Offer> displayAllUserOffers(String username, String password) {
        List<Offer> personalOffers = new ArrayList<>();
        User user = findByCriteriaHelper(username, password);
        if (user != null && authenticate(username,password)) {
            List<Offer> offers = offerRepo.getAll();
            for (Offer offer : offers) {
                if (offer.getReciever().equals(user) || offer.getSender().equals(user)) {
                    personalOffers.add(offer);
                }
            }
        }
        return personalOffers;
    }




    //Order
    //adauga atributele de la order


    public List<Product> selectProductsForOrder(List<Integer> productIds) {
        List<Product> selectedProducts = new ArrayList<>();
        for (int productId : productIds) {
            Product product = productRepo.read(productId);
            if (product != null) {
                selectedProducts.add(product);
            }
        }
        return selectedProducts;
    }


    public boolean placeOrder(String buyerUsername, String buyerPassword, List<Integer> selectedProductsIds, String status, String shippingAddress, int sellerId) {
        if(authenticate(buyerUsername,buyerPassword)){
            User buyer=findByCriteriaHelper(buyerUsername,buyerPassword);

            List<Product> orderedProducts = selectProductsForOrder(selectedProductsIds);
            Map<User, List<Product>> productsBySeller=new HashMap<>();
            for(Product product:orderedProducts){
                productsBySeller.computeIfAbsent(product.getListedBy(),k -> new ArrayList<>()).add(product);
            }

            for (Map.Entry<User, List<Product>> entry : productsBySeller.entrySet()) {
                User seller = entry.getKey();
                List<Product> productsForSeller = entry.getValue();

                Order order = new Order(productsForSeller, status, shippingAddress, buyer, seller);
                orderRepo.create(order);
            }
            return true;

        }
        return false;

    }

    public List<Order> displayMadeOrders(String username, String password){
        List<Order> personalOrders=new ArrayList<>();
        User user=findByCriteriaHelper(username,password);
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

    public List<Order> displayReceivedOrders(String username, String password){
        List<Order> personalOrders=new ArrayList<>();
        User user=findByCriteriaHelper(username,password);
        if(user!=null){
            List<Order>orders=orderRepo.getAll();
            for(Order order:orders){
                if(order.getSeller().equals(user)){
                    personalOrders.add(order);
                }
            }
        }
        return personalOrders;
    }

    public List<Order> displayAllUsersOrders(String username, String password){
        List<Order> personalOrders=new ArrayList<>();
        User user=findByCriteriaHelper(username,password);
        if(user!=null){
            List<Order>orders=orderRepo.getAll();
            for(Order order:orders){
                if(order.getSeller().equals(user) || order.getBuyer().equals(user)){
                    personalOrders.add(order);
                }
            }
        }
        return personalOrders;
    }


    //Review
    public boolean writeReview(String reviewerUsername, String reviewerPassword, double grade, String message, int revieweeId ){
        if(authenticate(reviewerUsername,reviewerPassword)){
            User reviewer=findByCriteriaHelper(reviewerUsername,reviewerPassword);
            User reviewee=userRepo.read(revieweeId);
            if(reviewee!=null && !reviewee.getUserName().equals(reviewerUsername)){
                Review review=new Review(grade,message,reviewer,reviewee);
                reviewRepo.create(review);
                return true;
            }
        }
        return false;

    }

    //pt ca avem metoda de display personal reviews unde o sa fie vizibil si id ul review urilor am pus review id
    public boolean deleteReview(String username, String password,int reviewId) {
        if (authenticate(username,password)){
            List<Review> reviews=reviewRepo.getAll();
            for(Review review:reviews){
                if(review.getId()==reviewId){
                    reviewRepo.delete(reviewId);
                    return true;
                }
            }

        }
        return false;
    }

    public List<Review> displayMadePersonalReviews(String username, String password){
        List<Review> personalReviews=new ArrayList<>();
        User user=findByCriteriaHelper(username,password);
        if(user!=null){
            List<Review>reviews=reviewRepo.getAll();
            for(Review review:reviews){
                if(review.getReviewer().equals(user)){
                    personalReviews.add(review);
                }
            }
        }
        return personalReviews;
    }


    //Favorites
    public boolean addToFavorites(String userName, String password,int productId){
        if(authenticate(userName,password)){
            User user=findByCriteriaHelper(userName,password);
            Product product=productRepo.read(productId);
            if(product!=null && !user.favourites.contains(product)){
                user.favourites.add(product);
                int newNrOfLikes= product.getNrLikes()+1;
                product.setNrLikes(newNrOfLikes);
                return true;
            }
        }
        return false;

    }

    public boolean removeFromFavourites(String userName,String password, int productId){
        if(authenticate(userName,password)){
            User user=findByCriteriaHelper(userName,password);
            Product product=productRepo.read(productId);
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



    //Product
    public boolean listProduct(String userName,String password, Category category,String name,String color, int size, double price, String brand, String condition, int nrOfViews, int nrOfLikes){
        if(authenticate(userName,password)){
            User seller=findByCriteriaHelper(userName,password);
            Product product=new Product(name,color,size,price,brand,condition,nrOfViews,nrOfLikes,seller);
            product.setCategory(category);
            productRepo.create(product);
            seller.listedProducts.add(product);
            return true;
        }
        return false;
    }


    public boolean deleteListedProduct(String username,String password,int productId){
        if(authenticate(username,password)){
            User user=findByCriteriaHelper(username,password);
            for(Product product:user.listedProducts){
                if(product.getId()==productId){
                    user.listedProducts.remove(productRepo.read(productId));
                    productRepo.delete(productId);
                    return true;
                }
            }

        }
        return false;

    }


    public User findByCriteriaHelper(String username,String password){
        if(authenticate(username,password)){
            List<User> users=userRepo.findByCriteria(user -> user.getUserName().equals(username));
            return users.getFirst();
        }
        return null;
    }

    public List<Review> displayProfileReviews(String username, String password) {
        User user=findByCriteriaHelper(username,password);
        List<Review> res = new ArrayList<>();
        if(user!=null){
            List<Review> reviews=reviewRepo.getAll();
            for(Review review:reviews){
                if(review.getReviewee().equals(user)){
                    res.add(review);
                }
            }
        }
        return res;
    }

    public double userAverageOfferAcceptanceRate(int userId){
        User user=userRepo.read(userId);
        List<Offer> receivedOffers=displayReceivedOffers(user.getUserName(),user.getPassword());
        if (receivedOffers.isEmpty()) {
            return 0;
        }
        int nrOfAcceptedOffers=0;
        for(Offer offer:receivedOffers){

            if (offer.getStatus()){
                nrOfAcceptedOffers++;
            }
        }
        return (double) (nrOfAcceptedOffers/receivedOffers.size())*100;


    }










}

