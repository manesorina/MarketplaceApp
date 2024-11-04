public class Offer implements Identifiable{
    int id;
    String message;
    double offeredPrice;
    Product targetedProduct;
    String status;

    public Offer(String message, double offeredPrice, Product targetedProduct, String status) {
        this.message = message;
        this.offeredPrice = offeredPrice;
        this.targetedProduct = targetedProduct;
        this.status = "Pending...";
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
