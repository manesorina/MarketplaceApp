package Repository;

import Domain.User;

import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class UserFileRepository extends FileRepository<User> {
    public String productsListedByUserFilename;
    public String productsLikedByUserFilename;
    public UserFileRepository(String filename, String productsListedByUserFilename,
                              String productsLikedByUserFilename) {
        super(filename);
        this.productsListedByUserFilename = productsListedByUserFilename;
        this.productsLikedByUserFilename = productsLikedByUserFilename;
    }

    @Override
    protected String convertObjectToString(User user) {
        return user.getId() + "," +
                user.getUserName() + "," +
                user.getPassword() + "," +
                user.getEmail() + "," +
                user.getPhone() + "," +
                user.getScore() + "," +
                user.nrOfFlaggedActions;
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

        User user = new User(username, password, email, phone, score);
        user.setId(id);
        user.nrOfFlaggedActions = flaggedActions;
        return user;
    }

    @Override
    public void loadDataFromFile() {
        super.loadDataFromFile();
        loadLikedProducts();
        loadListedProducts();
    }

    private void loadLikedProducts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(productsLikedByUserFilename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int userId = Integer.parseInt(parts[0]);
                int productId = Integer.parseInt(parts[1]);

                User user = super.read(userId);
                if (user != null) {
                    user.getFavourites().add(productId);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading liked products: " + e.getMessage());
        }
    }

    private void loadListedProducts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(productsListedByUserFilename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int userId = Integer.parseInt(parts[0]);
                int productId = Integer.parseInt(parts[1]);

                User user = super.read(userId);
                if (user != null) {
                    user.getListedProducts().add(productId);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading listed products: " + e.getMessage());
        }
    }

    @Override
    public void create(User user) {
        super.create(user);
        saveLikedProducts(user);
        saveListedProducts(user);
    }

    @Override
    public void update(User user) {
        super.update(user);
        saveLikedProducts(user);
        saveListedProducts(user);
    }

    private void saveLikedProducts(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(productsLikedByUserFilename, true))) {
            for (Integer productId : user.getFavourites()) {
                writer.write(user.getId() + "," + productId);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving liked products: " + e.getMessage());
        }
    }

    private void saveListedProducts(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(productsListedByUserFilename, true))) {
            for (Integer productId : user.getListedProducts()) {
                writer.write(user.getId() + "," + productId);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving listed products: " + e.getMessage());
        }
    }

    @Override
    public List<User> findByCriteria(Predicate<User> predicate) {
        List<User> matchingUsers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(super.filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = createObjectFromString(line);
                if (predicate.test(user)) {
                    matchingUsers.add(user);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading users for criteria search: " + e.getMessage());
        }
        return matchingUsers;
    }
}
