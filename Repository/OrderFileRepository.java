package Repository;

import Domain.Order;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class OrderFileRepository extends FileRepository<Order> {

    public OrderFileRepository(String filename){
        super(filename);
    }

    public String convertObjectToString(Order order){
        StringBuilder sb = new StringBuilder();
        for (Integer productId : order.getProducts()) {
            sb.append(order.getId()).append(",")
                    .append(productId).append(",")
                    .append(order.getStatus()).append(",")
                    .append(order.getTotalPrice()).append(",")
                    .append(order.getShippingAddress()).append(",")
                    .append(order.getBuyer()).append(",")
                    .append(order.getSeller()).append("\n");
        }
        return sb.toString().trim();

    }

    public Order createObjectFromString(String line){
        String[] parts = line.split(",");
        int orderId = Integer.parseInt(parts[0]);
        int productId = Integer.parseInt(parts[1]);
        String status = parts[2];
        double totalPrice = Double.parseDouble(parts[3]);
        String shippingAddress = parts[4];
        int buyer = Integer.parseInt(parts[5]);
        int seller = Integer.parseInt(parts[6]);

        Order order = new Order(new ArrayList<>(), status, shippingAddress, buyer, seller);
        order.setId(orderId);
        order.setTotalPrice(totalPrice);
        order.getProducts().add(productId);
        return order;
    }

    @Override
    public void loadDataFromFile() {
        List<String> lines = readAllLines();
        List<Order> orders = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(",");
            int orderId = Integer.parseInt(parts[0]);
            int productId = Integer.parseInt(parts[1]);
            String status = parts[2];
            double totalPrice = Double.parseDouble(parts[3]);
            String shippingAddress = parts[4];
            int buyer = Integer.parseInt(parts[5]);
            int seller = Integer.parseInt(parts[6]);

            Order existingOrder = findOrderById(orders, orderId);
            if (existingOrder == null) {
                existingOrder = new Order(new ArrayList<>(), status, shippingAddress, buyer, seller);
                existingOrder.setId(orderId);
                existingOrder.setTotalPrice(totalPrice);
                orders.add(existingOrder);
            }
            existingOrder.getProducts().add(productId);
        }

        for (Order order : orders) {
            if (order.getId() >= currentId) {
                currentId = order.getId() + 1;
            }
            super.create(order);
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

    private Order findOrderById(List<Order> orders, int orderId) {
        for (Order order : orders) {
            if (order.getId() == orderId) {
                return order;
            }
        }
        return null;
    }
}
