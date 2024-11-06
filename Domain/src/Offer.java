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
        this.status = status;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getOfferedPrice() {
        return offeredPrice;
    }

    public void setOfferedPrice(double offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

    public Product getTargetedProduct() {
        return targetedProduct;
    }

    public void setTargetedProduct(Product targetedProduct) {
        this.targetedProduct = targetedProduct;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
