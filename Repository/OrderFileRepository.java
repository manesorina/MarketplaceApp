package Repository;

import Domain.Order;

import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class OrderFileRepository extends FileRepository<Order> {
    public String orderedProductsFilename;
    public OrderFileRepository(String filename, String orderedProducts){
        super(filename);
        this.orderedProductsFilename = orderedProducts;
    }

    @Override
    protected String convertObjectToString(Order order) {
        return order.getId() + "," +
                order.getStatus() + "," +
                order.getTotalPrice() + "," +
                order.getShippingAddress() + "," +
                order.getBuyer() + "," +
                order.getSeller();
    }

    @Override
    protected Order createObjectFromString(String line) {
        String[] parts = line.split(",");
        int orderId = Integer.parseInt(parts[0]);
        String status = parts[1];
        double totalPrice = Double.parseDouble(parts[2]);
        String shippingAddress = parts[3];
        int buyer = Integer.parseInt(parts[4]);
        int seller = Integer.parseInt(parts[5]);

        return new Order(new ArrayList<>(), status, shippingAddress, buyer, seller);
    }

    @Override
    public void loadDataFromFile() {
        List<Order> orders = super.getAll();
        loadOrderedProducts(orders);
    }

    private void loadOrderedProducts(List<Order> orders) {
        try (BufferedReader reader = new BufferedReader(new FileReader(orderedProductsFilename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int orderId = Integer.parseInt(parts[0]);
                int productId = Integer.parseInt(parts[1]);

                Order order = findOrderById(orders, orderId);
                if (order != null) {
                    order.getProducts().add(productId);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading ordered products: " + e.getMessage());
        }
    }

    @Override
    public void create(Order order) {
        super.create(order);
        saveOrderedProducts(order);
    }

    @Override
    public void update(Order order) {
        super.update(order);
        saveOrderedProducts(order);
    }

    private void saveOrderedProducts(Order order) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(orderedProductsFilename, true))) {
            for (Integer productId : order.getProducts()) {
                writer.write(order.getId() + "," + productId);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving ordered products: " + e.getMessage());
        }
    }

    private Order findOrderById(List<Order> orders, int orderId) {
        for (Order order : orders) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        return null;
    }
}
