import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserService extends VisitorService{

    IMRepository<Order> orderRepo;
    IMRepository<Offer> offerRepo;



    /**
     * Constructor for the UserService class. Initializes the service with the provided repositories for
     * users, products, reviews, orders, and offers.
     *
     * @param userRepo the repository to handle user-related operations
     * @param productRepo the repository to handle product-related operations
     * @param reviewRepo the repository to handle review-related operations
     * @param orderRepo the repository to handle order-related operations
     * @param offerRepo the repository to handle offer-related operations
     */
    public UserService(IMRepository<User> userRepo, IMRepository<Product> productRepo,
                       IMRepository<Review> reviewRepo, IMRepository<Order> orderRepo,IMRepository<Offer> offerRepo) {
        super(userRepo, productRepo, reviewRepo);
        this.orderRepo=orderRepo;
        this.offerRepo=offerRepo;

    }


    /**
     * Authenticates a user based on their username and password.
     *
     * @param userName the username of the user.
     * @param password the password of the user.
     * @return {@code true} if the user is authenticated; {@code false} otherwise.
     */
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
    /**
     * Sends an offer from a buyer to a seller for a specific product.
     *
     * @param senderUsername the username of the sender making the offer.
     * @param senderPassword the password of the sender for authentication.
     * @param message a message included with the offer.
     * @param selectedProduct the product for which the offer is being made.
     * @param offeredPrice the price offered by the sender.
     * @return {@code true} if the offer is created and sent successfully; {@code false} otherwise.
     */

    public boolean sendOffer( String senderUsername,String senderPassword, String message, Product selectedProduct, double offeredPrice) {
        if (authenticate(senderUsername, senderPassword)) {
            Product product = productRepo.read(selectedProduct.getId());
            if (product!=null) {
                User sender = findByCriteriaHelper(senderUsername, senderPassword);
                User offerReceiver = selectedProduct.getListedBy();
                if (offerReceiver  != null && !offerReceiver .getUserName().equals(senderUsername) && offeredPrice>=selectedProduct.getPrice()/2) {
                    Offer offer = new Offer(message, offeredPrice, selectedProduct,  sender, offerReceiver );
                    offerRepo.create(offer);
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Accepts an offer made to a seller.
     *
     * @param sellerUsername the username of the seller.
     * @param sellerPassword the password of the seller for authentication.
     * @param offerId the ID of the offer to be accepted.
     * @return {@code true} if the offer is accepted; {@code false} otherwise.
     */
    public boolean acceptOffer(String sellerUsername, String sellerPassword, int offerId) {
        if (authenticate(sellerUsername, sellerPassword)) {
            Offer offer=offerRepo.read(offerId);
            if (offer.getReceiver().equals(findByCriteriaHelper(sellerUsername, sellerPassword))) {
                offer.setStatus(true);
                offer.getTargetedProduct().setPrice(offer.getOfferedPrice());
                return true;
            }
        }
        return false;
    }


    /**
     * Declines an offer made to a seller.
     *
     * @param sellerUsername the username of the seller.
     * @param sellerPassword the password of the seller for authentication.
     * @param offerId the ID of the offer to be declined.
     * @return {@code true} if the offer is declined; {@code false} otherwise.
     */
    public boolean declineOffer(String sellerUsername, String sellerPassword, int offerId){
        if(authenticate(sellerUsername,sellerPassword)){
            Offer offer=offerRepo.read(offerId);
            if(offer.getReceiver().equals(findByCriteriaHelper(sellerUsername,sellerPassword))){
                offer.setStatus(false);
                return true;
            }
        }
        return false;
    }


    /**
     * Displays the offers made by the user.
     *
     * @param username the username of the user.
     * @param password the password of the user for authentication.
     * @return a list of offers made by the user.
     */
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


    /**
     * Displays the offers received by the user.
     *
     * @param username the username of the user.
     * @param password the password of the user for authentication.
     * @return a list of offers received by the user.
     */
    public List<Offer> displayReceivedOffers(String username, String password) {
        List<Offer> personalOffers = new ArrayList<>();
        User user = findByCriteriaHelper(username, password);
        if (user != null && authenticate(username,password)) {
            List<Offer> offers = offerRepo.getAll();
            for (Offer offer : offers) {
                if (offer.getReceiver().equals(user)) {
                    personalOffers.add(offer);
                }
            }
        }
        return personalOffers;
    }


    /**
     * Displays all offers involving the user (both sent and received).
     *
     * @param username the username of the user.
     * @param password the password of the user for authentication.
     * @return a list of all offers involving the user.
     */
    public List<Offer> displayAllUserOffers(String username, String password) {
        List<Offer> personalOffers = new ArrayList<>();
        User user = findByCriteriaHelper(username, password);
        if (user != null && authenticate(username,password)) {
            List<Offer> offers = offerRepo.getAll();
            for (Offer offer : offers) {
                if (offer.getReceiver().equals(user) || offer.getSender().equals(user)) {
                    personalOffers.add(offer);
                }
            }
        }
        return personalOffers;
    }




    //Order


    /**
     * Selects products for an order based on provided product IDs.
     *
     * @param productIds a list of product IDs to be selected.
     * @return a list of selected products.
     */
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



    /**
     * Places an order for a list of selected products.
     *
     * @param buyerUsername the username of the buyer.
     * @param buyerPassword the password of the buyer for authentication.
     * @param selectedProductsIds a list of product IDs for the order.
     * @param status the status of the order.
     * @param shippingAddress the shipping address for the order.
     * @param sellerId the ID of the seller.
     * @return {@code true} if the order is placed successfully; {@code false} otherwise.
     */

    public boolean placeOrder(String buyerUsername, String buyerPassword, List<Integer> selectedProductsIds, String status, String shippingAddress, int sellerId) {
        if(authenticate(buyerUsername,buyerPassword)){
            User buyer=findByCriteriaHelper(buyerUsername,buyerPassword);

            List<Product> orderedProducts = selectProductsForOrder(selectedProductsIds);
            Map<User, List<Product>> productsBySeller=new HashMap<>();
            for(Product product:orderedProducts){
                product.setAvailable(false);
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


    /**
     * Displays orders made by the user.
     *
     * @param username the username of the user.
     * @param password the password of the user for authentication.
     * @return a list of orders made by the user.
     */
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


    /**
     * Displays orders received by the user.
     *
     * @param username the username of the user.
     * @param password the password of the user for authentication.
     * @return a list of orders received by the user.
     */
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


    /**
     * Displays all orders involving the user (both made and received).
     *
     * @param username the username of the user.
     * @param password the password of the user for authentication.
     * @return a list of all orders involving the user.
     */

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


    /**
     * Writes a review from one user to another.
     *
     * @param reviewerUsername the username of the reviewer.
     * @param reviewerPassword the password of the reviewer for authentication.
     * @param grade the grade for the review.
     * @param message the message content of the review.
     * @param revieweeId the ID of the user being reviewed.
     * @return {@code true} if the review is written successfully; {@code false} otherwise.
     */
    public boolean writeReview(String reviewerUsername, String reviewerPassword, double grade, String message, int revieweeId ){
        if(authenticate(reviewerUsername,reviewerPassword)){
            User reviewer=findByCriteriaHelper(reviewerUsername,reviewerPassword);
            User reviewee=userRepo.read(revieweeId);
            if(reviewee!=null && !reviewee.getUserName().equals(reviewerUsername)){
                for(Order order:displayMadeOrders(reviewerUsername,reviewerPassword)){
                    if(order.getSeller().equals(reviewee)){
                        Review review=new Review(grade,message,reviewer,reviewee);
                        reviewRepo.create(review);
                        return true;
                    }
                }

            }
        }
        return false;

    }


    /**
     * Deletes a review made by the user.
     *
     * @param username the username of the user.
     * @param password the password of the user for authentication.
     * @param reviewId the ID of the review to be deleted.
     * @return {@code true} if the review is deleted successfully; {@code false} otherwise.
     */

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



    /**
     * Displays personal reviews made by the user.
     *
     * @param username the username of the user.
     * @param password the password of the user for authentication.
     * @return a list of reviews made by the user.
     */

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



    /**
     * Adds a product to the user's favorites.
     *
     * @param userName the username of the user.
     * @param password the password of the user for authentication.
     * @param productId the ID of the product to be added to favorites.
     * @return {@code true} if the product is added successfully; {@code false} otherwise.
     */
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


    /**
     * Removes a product from the user's favorites.
     *
     * @param userName the username of the user.
     * @param password the password of the user for authentication.
     * @param productId the ID of the product to be removed from favorites.
     * @return {@code true} if the product is removed successfully; {@code false} otherwise.
     */

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



    /**
     * Displays the user's favorite products.
     *
     * @param userName the username of the user.
     * @param password the password of the user for authentication.
     * @return a list of the user's favorite products.
     */
    public List<Product> displayFavourites(String userName, String password){
        User user=findByCriteriaHelper(userName,password);
        if(user!=null){
            return user.getFavourites();
        }
        return new ArrayList<>();
    }



    //Product


    /**
     * Lists a new product for sale by the user.
     *
     * @param userName the username of the seller.
     * @param password the password of the seller for authentication.
     * @param category the category of the product.
     * @param name the name of the product.
     * @param color the color of the product.
     * @param size the size of the product.
     * @param price the price of the product.
     * @param brand the brand of the product.
     * @param condition the condition of the product.
     * @param nrOfViews the number of views the product has.
     * @param nrOfLikes the number of likes the product has.
     * @return {@code true} if the product is listed successfully; {@code false} otherwise.
     */
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



    /**
     * Deletes a product listed by the user.
     *
     * @param username the username of the seller.
     * @param password the password of the seller for authentication.
     * @param productId the ID of the product to be deleted.
     * @return {@code true} if the product is deleted successfully; {@code false} otherwise.
     */
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


    /**
     * Finds a user by their username and password.
     *
     * @param username the username of the user.
     * @param password the password of the user for authentication.
     * @return the user if found and authenticated; {@code null} otherwise.
     */
    public User findByCriteriaHelper(String username,String password){
        if(authenticate(username,password)){
            List<User> users=userRepo.findByCriteria(user -> user.getUserName().equals(username));
            return users.getFirst();
        }
        return null;
    }

    /**
     * Calculates the user's average offer acceptance rate.
     *
     * @param userId the ID of the user.
     * @return the acceptance rate as a percentage. Returns 0 if no offers are received.
     */
    public double userAverageOfferAcceptanceRate(int userId){
        User user=userRepo.read(userId);
        List<Offer> receivedOffers=displayReceivedOffers(user.getUserName(),user.getPassword());
        //List<Offer> receivedOffers=new ArrayList<>();
        if(user!=null){
            List<Offer>offers=offerRepo.getAll();
            for(Offer offer:offers){
                if(offer.getReceiver().equals(user)){
                    receivedOffers.add(offer);
                }

            }
        }

        if (receivedOffers.isEmpty()) {
            return 0;
        }
        int nrOfAcceptedOffers=0;
        for(Offer offer:receivedOffers){

            if (offer.getStatus()){
                nrOfAcceptedOffers++;
            }
        }
        return ((double) nrOfAcceptedOffers/receivedOffers.size())*100;


    }

    /**
     * Retrieves the list of products listed by a user based on their username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return A list of products the user has listed, or an empty list if the user is not found or has no listed products.
     */
    public List<Product> getMyListedProducts(String username, String password) {
        User user=findByCriteriaHelper(username,password);
        if(user!=null){
            return user.getListedProducts();
        }
        return new ArrayList<>();
    }

    /**
     * Calculates the number of negative reviews received by a user.
     * A review is considered negative if its grade is 3.5 or below.
     *
     * @param userId The ID of the user for whom the count of negative reviews is being calculated.
     * @return The total number of negative reviews for the specified user.
     */
    public int getUserNegativeReviews(int userId){
        int nrOfNegativeReviews=0;
        User user=userRepo.read(userId);
        for(Review review:reviewRepo.getAll()){
            if (review.getReviewee().equals(user) && review.getGrade()<=3.5){
                nrOfNegativeReviews++;
            }
        }
        return nrOfNegativeReviews;

    }

    /**
     * Calculates the number of negative reviews received by a user.
     * A review is considered negative if its grade is 3.5 or below.
     *
     * @param userId The ID of the user for whom the count of negative reviews is being calculated.
     * @return The total number of negative reviews for the specified user.
     */
    public int getUserPositiveReviews(int userId){
        int nrOfPositiveReviews=0;
        User user=userRepo.read(userId);
        for(Review review:reviewRepo.getAll()){
            if (review.getReviewee().equals(user) && review.getGrade()>3.5){
                nrOfPositiveReviews++;
            }
        }
        return nrOfPositiveReviews;

    }

    /**
     * Displays the reviews for a user's profile based on their username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return A list of reviews for the user’s profile, or an empty list if the user is not found or has no reviews.
     */
    public List<Review> displayProfileReviews(String username, String password) {
        User user=findByCriteriaHelper(username,password);
        List<Review> profileReviews=new ArrayList<>();
        if(user!=null){
            List<Review> reviews=reviewRepo.getAll();
            for(Review review:reviews){
                if(review.getReviewee().equals(user)){
                    profileReviews.add(review);
                }
            }
            return profileReviews;
        }
        return new ArrayList<>();
    }



    /**
     * Calculates the number of individual sales made by a user.
     * Each product sold, even within the same order, counts as a separate sale.
     *
     * @param userId The user whose sales are being calculated.
     * @return The total number of individual sales made by the user.
     */
    public int calculateNumberOfSales(int userId) {
        int totalSales = 0;
        User user=userRepo.read(userId);

        for (Order order : orderRepo.getAll()) {
            for (Product product : order.getProducts()) {
                if (product.getListedBy().equals(user)) {
                    totalSales++;
                }
            }
        }

        return totalSales;
    }


    /**
     * Calculates the trust score for a user based on their activity and reputation.
     * The score is computed using the following factors:
     * <ul>
     *   <li>The number of sales made by the user (each sale contributes 10 points).</li>
     *   <li>The number of negative reviews received by the user (each negative review contributes 5 points).</li>
     *   <li>The number of flagged actions associated with the user (each flagged action subtracts from the score).</li>
     * </ul>
     */
    public int calculateUserTrustScore(int userId){
        int score=calculateNumberOfSales(userId)*10;

        score+=getUserPositiveReviews(userId)*5;
        score-=getUserNegativeReviews(userId)*15;
        score-=userRepo.read(userId).getFlaggedActions();
        return score;
    }

    public int getNrOfFlaggedActions(int userId){
        User user= userRepo.read(userId);
        return user.getFlaggedActions();
    }


    /**
     * Retrieves the engagement score for a user based on their username and password.
     * This method authenticates the user and returns their current score if valid.
     *
     * @param username The username of the user whose score is being retrieved.
     * @param password The password of the user for authentication.
     * @return The user's engagement score if the user is found and authenticated;
     *         otherwise, returns 0 if the user is not found or authentication fails.
     */
    public double getMyScore(String username, String password) {
        User user=findByCriteriaHelper(username,password);
        if (user != null)
            return user.getScore();
        else return 0;
    }
}


