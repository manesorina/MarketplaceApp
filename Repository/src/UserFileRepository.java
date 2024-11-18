import java.io.*;
import java.util.ArrayList;
import java.util.List;

//public class UserFileRepository extends FMRepository<User> {
//
//    public UserFileRepository(String filename) {
//        super(filename);
//    }
//
//
//    @Override
//    protected String convertObjectToString(User user) {
//
//        return user.getId() + "," +
//                user.getUserName() + "," +
//                user.getPassword() + "," +
//                user.getEmail() + "," +
//                user.getPhone() + "," +
//                user.getScore() + "," +
//                user.getFlaggedActions() +
//                "," + convertProductListToString(user.getFavourites()) +
//                "," + convertProductListToString(user.getListedProducts());
//    }
//
//
//    private String convertProductListToString(List<Product> products) {
//        StringBuilder sb=new StringBuilder();
//        for (Product product : products) {
//            sb.append(product.getId()).append(";");
//        }
//        return sb.toString();
//    }
//
//    @Override
//    protected User createObjectFromString(String line) {
//        String[] parts = line.split(",");
//        User user = getUser(parts);
//
//
//        List<Product> favourites = parseProductList(parts[7]);
//        List<Product> listedProducts = parseProductList(parts[8]);
//        addProductsToUser(user, favourites, listedProducts);
//
//        return user;
//    }
//
//
//    private static User getUser(String[] parts) {
//        int id = Integer.parseInt(parts[0]);
//        String userName = parts[1];
//        String password = parts[2];
//        String email = parts[3];
//        String phone = parts[4];
//        double score = Double.parseDouble(parts[5]);
//        //int flaggedActions = Integer.parseInt(parts[6]);
//
//        User user = new User(userName, password, email, phone, score);
//        user.setId(id);
//        return user;
//    }
//
//
//    private List<Product> parseProductList(String productIdsString) {
//        List<Product> products = new ArrayList<>();
//        String[] productIds = productIdsString.split(";");
//        for (String id : productIds) {
//            int productId = Integer.parseInt(id);
//            //Product product = productRepository.read(productId);
//            //products.add(product);
//        }
//        return products;
//    }
//
//
//    private void addProductsToUser(User user, List<Product> favourites, List<Product> listedProducts) {
//
//        for (Product product : favourites) {
//            user.getFavourites().add(product);
//        }
//
//        for (Product product : listedProducts) {
//            user.getListedProducts().add(product);
//        }
//    }
//
//
//}
