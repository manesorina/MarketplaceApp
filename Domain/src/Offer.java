public class Offer {
    int offerID;
    String message;
    double offeredPrice;
    Object targetedObject;

    public Offer(int offerID, String message, double offeredPrice, Object targetedObject) {
        this.offerID = offerID;
        this.message = message;
        this.offeredPrice = offeredPrice;
        this.targetedObject = targetedObject;
    }

}
