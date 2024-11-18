import java.util.Arrays;

public class Main {

    public static void main(String args[]){

        String filename = "Repository/src/users.txt";
        UserFileRepository fileUserRepo = new UserFileRepository(filename);

        User user1=new User("John Doe","1234","johndoe@gmail.com","0724578967",3.5);
        user1.getFavourites().addAll(Arrays.asList(101, 102, 103));
        user1.getListedProducts().addAll(Arrays.asList(201, 202));
        user1.nrOfFlaggedActions = 2;

        fileUserRepo.create(user1);

        User loadedUser = fileUserRepo.read(user1.getId());

        if (loadedUser != null) {
            System.out.println("User loaded successfully!");
            System.out.println("User ID: " + loadedUser.getId());
            System.out.println("User Name: " + loadedUser.getUserName());
            System.out.println("User Email: " + loadedUser.getEmail());
            System.out.println("User Phone: " + loadedUser.getPhone());
            System.out.println("User Score: " + loadedUser.getScore());
            System.out.println("User Flagged Actions: " + loadedUser.nrOfFlaggedActions);
            System.out.println("User Favourites: " + loadedUser.getFavourites());
            System.out.println("User Listed Products: " + loadedUser.getListedProducts());
        } else {
            System.out.println("Failed to load user.");
        }


    }
}
