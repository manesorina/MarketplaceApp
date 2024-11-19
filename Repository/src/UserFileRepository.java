import java.util.ArrayList;
import java.util.List;

public class UserFileRepository extends FMRepository<User> {

    public UserFileRepository(String filename) {
        super(filename);
    }

    @Override
    protected String convertObjectToString(User user) {
        return user.getId() + "," +   //product.getCategory() + "," +
                user.getUserName() + "," +
                user.getPassword() + "," +
                user.getEmail() + "," +
                user.getPhone() + "," +
                user.getScore() + "," +
                user.getFlaggedActions() + "," +
                (user.getFavourites().isEmpty() ? "" : convertIdListToString(user.getFavourites())) + "," +
                (user.getListedProducts().isEmpty() ? "" : convertIdListToString(user.getListedProducts()));
    }

    private String convertIdListToString(List<Integer> ids) {
        StringBuilder sb = new StringBuilder();
        for (Integer id : ids) {
            sb.append(id).append(";");
        }

        if (!sb.isEmpty()) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    @Override
    protected User createObjectFromString(String line) {
        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0]);

        // CategoryName categoryName = CategoryName.valueOf(parts[1].toUpperCase());
        // Category category = new Category(categoryName);
        //

        String username = parts[1];
        String password = parts[2];
        String email = parts[3];
        String phone = parts[4];
        double score = Double.parseDouble(parts[5]);
        int flaggedActions = Integer.parseInt(parts[6]);

        List<Integer> favourites = (parts.length > 7 && !parts[7].isEmpty()) ? parseIdList(parts[7]) : new ArrayList<>();
        List<Integer> listedProducts = (parts.length > 8 && !parts[8].isEmpty()) ? parseIdList(parts[8]) : new ArrayList<>();

        User user = new User(username, password, email, phone, score);
        user.getFavourites().addAll(favourites);
        user.getListedProducts().addAll(listedProducts);
        user.nrOfFlaggedActions = flaggedActions;
        return user;
    }

    private List<Integer> parseIdList(String idsString) {
        List<Integer> ids = new ArrayList<>();
        if (idsString == null || idsString.isEmpty()) {
            return ids;
        }
        String[] idArray = idsString.split(";");
        for (String id : idArray) {
            ids.add(Integer.parseInt(id));
        }
        return ids;
    }
}
