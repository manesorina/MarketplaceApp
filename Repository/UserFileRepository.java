package Repository;

import Domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserFileRepository extends FMRepository<User> {

    public UserFileRepository(String filename) {
        super(filename);
    }

    @Override
    protected String convertObjectToString(User user) {
        String serialized= user.getId() + "," +
                user.getUserName() + "," +
                user.getPassword() + "," +
                user.getEmail() + "," +
                user.getPhone() + "," +
                user.getScore() + "," +
                user.getFlaggedActions() + "," +
                convertIdListToString(user.getFavourites()) + "," +
                convertIdListToString(user.getListedProducts());
        System.out.println("Serialized user: " + serialized);
        return serialized;
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
        user.setId(id);
        return user;
    }

    private List<Integer> parseIdList(String idsString) {
        String[] idArray = idsString.split(";");
        List<Integer> ids = new ArrayList<>();
        for (String id : idArray) {
            if (!id.trim().isEmpty()) {
                ids.add(Integer.parseInt(id));
            }
        }
        System.out.println("Parsed IDs: " + ids);
        return ids;
    }
}
