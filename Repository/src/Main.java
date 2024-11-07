import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {


        IMRepository<Product> repo=new IMRepository<>();
        IMRepository<Admin> adminRepo=new IMRepository<>();
        IMRepository<User> userRepo=new IMRepository<>();
        IMRepository<Review> reviewRepo=new IMRepository<>();


        Product p1=new Product("blue",36,8.99,"Nike","Worn",0);
        Product p2=new Product("black",38,10.99,"Nike","Worn",0);
        repo.create(p1);
        repo.create(p2);
        //repo.delete(1);
        //System.out.print(repo.getAll());


        Admin a1=new Admin("JohnDoe","qwerty1234","johnedoe@email.com","0747896547");
        Admin a2=new Admin("JaneSmith","1234abc","janesmith@email.com","0748596321");
        adminRepo.create(a1);
        adminRepo.create(a2);

        //System.out.println(a1.authenticate("johnDoe","qwerty1234"));
        //System.out.print(a2.authenticate("johnDoe","1234"));


        User u1= new User("MikeHouse","1q2w3e4r","mikehouse@gmail.com","0748996332",0);
        User u2= new User("LisaTeak","xyz987","lisateak@gmail.com","0747558114",4.5);
        User u3= new User("TinaSilver","x1y2z3","tinasilver@gmail.com","0758669327",0);
        userRepo.create(u1);
        userRepo.create(u2);
        userRepo.create(u3);

        //System.out.println(userRepo.getAll());



        Review r1=new Review(4.5,"I liked the service",u1,u2);
        Review r2=new Review(1.5,"Very bad service",u2,u3);
        reviewRepo.create(r1);
        reviewRepo.create(r2);

        //AdminService adminService = new AdminService(userRepo, reviewRepo, adminRepo);

        //System.out.println(reviewRepo.getAll());
//        System.out.println(a1.deleteReview(r2));
//        System.out.println(reviewRepo.getAll());

//        System.out.println(userRepo.getAll());
//        adminService.deleteUser(a1.getId(), a1.userName, a1.password, u1.getId());
//        System.out.println(userRepo.getAll());












    }


}
