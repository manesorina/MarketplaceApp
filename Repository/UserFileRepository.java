package Repository;

import Domain.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserFileRepository extends FMRepository<User> {

    public UserFileRepository(String filename) {
        super(filename);
    }

    @Override
    protected String convertObjectToString(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append(user.getId()).append(",")
                .append(user.getUserName()).append(",")
                .append(user.getPassword()).append(",")
                .append(user.getEmail()).append(",")
                .append(user.getPhone()).append(",")
                .append(user.getScore()).append(",")
                .append(user.nrOfFlaggedActions).append("\n");

        for (Integer favourite : user.getFavourites()) {
            sb.append(user.getId()).append(",FAV,").append(favourite).append("\n");
        }

        for (Integer listedProduct : user.getListedProducts()) {
            sb.append(user.getId()).append(",LISTED,").append(listedProduct).append("\n");
        }

        return sb.toString().trim();
    }

    @Override
    protected User createObjectFromString(String line) {
        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0]);

        if (parts.length == 3 && "FAV".equals(parts[1])) {
            int productId = Integer.parseInt(parts[2]);
            User user = new User("", "", "", "", 0.0);
            user.setId(id);
            user.getFavourites().add(productId);
            return user;
        } else if (parts.length == 3 && "LISTED".equals(parts[1])) {
            int productId = Integer.parseInt(parts[2]);
            User user = new User("", "", "", "", 0.0);
            user.setId(id);
            user.getListedProducts().add(productId);
            return user;
        } else {
            String username = parts[1];
            String password = parts[2];
            String email = parts[3];
            String phone = parts[4];
            double score = Double.parseDouble(parts[5]);
            int flaggedActions = Integer.parseInt(parts[6]);

            User user = new User(username, password, email, phone, score);
            user.setId(id);
            user.nrOfFlaggedActions = flaggedActions;
            return user;
        }
    }

    @Override
    public void loadDataFromFile() {
        List<String> lines = readAllLines();
        List<User> users = new ArrayList<>();

        for (String line : lines) {
            User partialUser = createObjectFromString(line);

            User existingUser = findUserById(users, partialUser.getId());
            if (existingUser == null) {
                users.add(partialUser);
            } else {
                existingUser.getFavourites().addAll(partialUser.getFavourites());
                existingUser.getListedProducts().addAll(partialUser.getListedProducts());
            }
        }
        for (User user : users) {
            if (user.getId() >= currentId) {
                currentId = user.getId() + 1;
            }
            super.create(user);
        }
    }

    private List<String> readAllLines() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return lines;
    }

    private User findUserById(List<User> users, int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }
}
