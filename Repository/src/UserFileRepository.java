import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserFileRepository extends FMRepository<User> {

    public UserFileRepository(String filename) {
        super(filename);
    }

    
    @Override
    protected String convertObjectToString(User user) {

        return user.getId() + "," +
                user.getUserName() + "," +
                user.getPassword() + "," +  
                user.getEmail() + "," +
                user.getPhone() + "," +
                user.getScore() + "," +
                user.getFlaggedActions() +
                "," + convertProductListToString(user.getFavourites()) +
                "," + convertProductListToString(user.getListedProducts());
    }

    
    private String convertProductListToString(List<Product> products) {
        StringBuilder sb=new StringBuilder();
        for (Product product : products) {
            sb.append(product.getId()).append(";");  
        }
        return sb.toString();
    }

    

}
