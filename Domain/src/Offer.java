public class Offer implements Identifiable{
    private int id;
    private String message;
    private double offeredPrice;
    private Product targetedProduct;
    private Boolean accepted;
    private User sender;
    private User reciever;

    public Offer(String message, double offeredPrice, Product targetedProduct, Boolean accepted, User sender,User reciever) {
        this.message = message;
        this.offeredPrice = offeredPrice;
        this.targetedProduct = targetedProduct;
        this.accepted=accepted;
        this.sender=sender;
        this.reciever=reciever;
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

    public Boolean getStatus() {
        return accepted;
    }

    public void setStatus(Boolean accepted) {
        this.accepted = accepted;
    }

    public User getSender() {
        return sender;
    }

    public User getReciever() {
        return reciever;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setReciever(User reciever) {
        this.reciever = reciever;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", offeredPrice=" + offeredPrice +
                ", targetedProduct=" + targetedProduct +
                ", accepted=" + accepted +
                '}';
    }
}
