package Repository;

import Domain.*;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String userFilename = "Repository/users.json";
        String productFilename = "Repository/products.json";
        String categoriesFilename= "Repository/categories.json";
        String offersFilename= "Repository/offers.json";
        String orderFilename= "Repository/orders.json";
        String reviewsFilename= "Repository/reviews.json";
        String adminsFilename= "Repository/admins.json";
        String visitorsFilename= "Repository/visitors.json";



        UserFileRepository fileUserRepo = new UserFileRepository(userFilename);
        AdminFileRepository fileAdminRepo=new AdminFileRepository(adminsFilename);
        VisitorFileRepository fileVisitorRepo=new VisitorFileRepository(visitorsFilename);

        CategoryFileRepository fileCategoryRepo=new CategoryFileRepository(categoriesFilename);
        ProductFileRepository fileProductRepo = new ProductFileRepository(productFilename);

        OrderFileRepository fileOrderRepo=new OrderFileRepository(orderFilename);
        OfferFileRepository fileOfferRepo=new OfferFileRepository(offersFilename);
        ReviewFileRepository fileReviewRepo=new ReviewFileRepository(reviewsFilename);



        Visitor visitor1=new Visitor();
        Visitor visitor2=new Visitor();
        fileVisitorRepo.create(visitor1);
        fileVisitorRepo.create(visitor2);




        Admin admin1=new Admin("Jack Black","qwert","jackblack@email.com","0789663221");
        Admin admin2=new Admin("Emma Steel","azsxdc","emmastee@email.com","0784557441");

        fileAdminRepo.create(admin1);
        fileAdminRepo.create(admin2);





        User user1 = new User("John Doe", "1234", "johndoe@gmail.com", "0724578967", 3.5);
        user1.nrOfFlaggedActions = 2;


        User user2 = new User("Jane Smith", "abc", "janesmith@email.com", "0724567789", 2.5);
        user2.nrOfFlaggedActions = 3;



        User user3 = new User("Mike jones", "a1b2c3", "mikejones@gmail.com", "0724567789", 0.0);
        user3.nrOfFlaggedActions = 0;

        User user4 = new User("Sarah Stone", "123456", "sarahstone@email.com", "0724567789", 0.0);
        user4.nrOfFlaggedActions = 0;





        Product product1 = new Product( "T-shirt", "Red", 42, 19.99, "BrandA", "New", 100, 10, user1.getId());
        Product product2 = new Product( "Jeans", "Blue", 32, 39.99, "BrandB", "Used", 150, 25, user1.getId());
        Product product3 = new Product( "Users 2 thsirt", "Blue", 32, 8.99, "BrandB", "Used", 150, 25, user2.getId());
        Product product4 = new Product( "Users 2 jean", "Blue", 32, 11.99, "BrandB", "Used", 150, 25, user2.getId());
        Product product5 = new Product( "Summer top", "Blue", 32, 10.99, "BrandB", "Used", 150, 25, user2.getId());



        Category categoryTops = new Category(CategoryName.TOPS);
        Category categoryBottoms=new Category(CategoryName.BOTTOMS);

        fileCategoryRepo.create(categoryTops);
        fileCategoryRepo.create(categoryBottoms);


        product1.setCategory(categoryTops.getId());
        product3.setCategory(categoryTops.getId());
        product2.setCategory(categoryBottoms.getId());
        product4.setCategory(categoryBottoms.getId());
        product5.setCategory(categoryTops.getId());




        user1.getFavourites().add(product3.getId());
        user1.getListedProducts().addAll(Arrays.asList(product1.getId(),product2.getId()));

        user2.getFavourites().add(product1.getId());
        user2.getListedProducts().addAll(Arrays.asList(product3.getId(),product4.getId()));

        fileProductRepo.create(product1);
        fileProductRepo.create(product2);
        fileProductRepo.create(product3);
        fileProductRepo.create(product4);
        fileProductRepo.create(product5);
        fileUserRepo.create(user1);

        fileUserRepo.create(user2);
        fileUserRepo.create(user3);
        fileUserRepo.create(user4);

        System.out.println("User  1 ID: " + user1.getId());

        System.out.println("User  3 ID: " + user3.getId());

        System.out.println("Product  3 ID: " + product3.getId());





        Offer offer1=new Offer("Would you consider 18.00 for this?",18.00,product1.getId(),user4.getId(),user1.getId());
        Offer offer2=new Offer("Would you take 7.50 for this?",7.50,product3.getId(),user3.getId(),user2.getId());

        fileOfferRepo.create(offer1);
        fileOfferRepo.create(offer2);


        List<Integer> orderedProducts1 = List.of(product3.getId());
        List<Integer> orderedProducts2 = List.of(product3.getId());


        Order order1=new Order(orderedProducts1,"shipped","Strada xyz",user3.getId(),user1.getId());
        Order order2=new Order(orderedProducts2,"shipped","Strada xyz",user4.getId(),user2.getId());


        fileOrderRepo.create(order1);
        fileOrderRepo.create(order2);


        Review review1=new Review(3.6,"Acceptable service",user3.getId(),user2.getId());
        Review review2=new Review(4.0,"Good service",user4.getId(),user2.getId());

        fileReviewRepo.create(review1);
        fileReviewRepo.create(review2);


        fileProductRepo.delete(product4.getId());

        //fileProductRepo.update(user1.getListedProducts().getFirst());
       // user1.getFavourites().add(product3.getId());
        //fileUserRepo.update(user1.getListedProducts().addAll(Arrays.asList(product1.getId(),product2.getId()));

        //Product product6 = new Product( "Winter Top", "Blue", 32, 10.99, "BrandB", "Used", 150, 25, user2.getId());
        //product6.setCategory(categoryTops.getId());
        //fileProductRepo.create(product6);
        //System.out.println(product6.getListedBy());




    }
}
