public class Offer implements Identifiable{
    private int id;
    private String message;
    private double offeredPrice;
    private Product targetedProduct;
    private Boolean accepted;
    private User sender;
    private User receiver;

    public Offer(String message, double offeredPrice, Product targetedProduct, User sender,User reciever) {
        this.message = message;
        this.offeredPrice = offeredPrice;
        this.targetedProduct = targetedProduct;
        this.accepted=false;
        this.sender=sender;
        this.receiver=reciever;
        this.accepted=false;
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

    public User getReceiver() {
        return receiver;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
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
