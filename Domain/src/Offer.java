public class Offer implements Identifiable{
    private int id;
    private String message;
    private double offeredPrice;
    private int targetedProduct;
    private Boolean accepted;
    private int sender;
    private int receiver;

    public Offer(String message, double offeredPrice, int targetedProduct, int sender, int reciever) {
        this.message = message;
        this.offeredPrice = offeredPrice;
        this.targetedProduct = targetedProduct;
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

    public int getTargetedProduct() {
        return targetedProduct;
    }

    public void setTargetedProduct(int targetedProduct) {
        this.targetedProduct = targetedProduct;
    }

    public Boolean getStatus() {
        return accepted;
    }

    public void setStatus(Boolean accepted) {
        this.accepted = accepted;
    }

    public int getSender() {
        return sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public void setReceiver(int receiver) {
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
