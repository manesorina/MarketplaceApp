import java.util.ArrayList;
import java.util.List;

public class OrderFileRepository extends FMRepository<Order> {

    public OrderFileRepository(String filename){
        super(filename);
    }

    public String convertObjectToString(Order order){
        return order.getId()+ "," +
                order.getProducts()+ "," +
                order.getStatus()+ "," +
                order.getShippingAddress()+ "," +
                order.getBuyer()+ "," +
                order.getSeller();

    }

    public Order createObjectFromString(String line){
        String[] parts=line.split(",");
        int id=Integer.parseInt(parts[0]);
        List<Integer> orderedProducts = parseIdList(parts[1]);
        String status=parts[2];
        String shippingAddress=parts[3];
        int buyer=Integer.parseInt(parts[4]);
        int seller=Integer.parseInt(parts[5]);

        Order order= new Order(orderedProducts,status,shippingAddress,buyer,seller);
        order.getProducts().addAll(orderedProducts);
        return order;
    }


    private List<Integer> parseIdList(String idsString) {
        String[] idArray = idsString.split(",");
        List<Integer> ids = new ArrayList<>();
        for (String id : idArray) {
            ids.add(Integer.parseInt(id));
        }
        return ids;
    }

    private String convertIdListToString(List<Integer> ids) {
        StringBuilder sb = new StringBuilder();
        for (Integer id : ids) {
            sb.append(id).append(",");
        }
        if (!sb.isEmpty()) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
