package Repository;

import Domain.Offer;

public class OfferFileRepository extends FileRepository<Offer> {

    public OfferFileRepository(String filename){
        super(filename);
    }

    protected String convertObjectToString(Offer offer){
        return offer.getId() + "," +
                offer.getMessage() + ","+
                offer.getOfferedPrice() + ","+
                offer.getTargetedProduct() + ","+
                offer.getSender() + ","+
                offer.getReceiver() + ","+
                offer.getStatus();
    }

    protected Offer createObjectFromString (String line){
        String[] parts=line.split(",");
        int id=Integer.parseInt(parts[0]);
        String message=parts[1];
        double offeredPrice=Double.parseDouble(parts[2]);
        int targetedProduct=Integer.parseInt(parts[3]);
        int sender=Integer.parseInt(parts[4]);
        int receiver=Integer.parseInt(parts[5]);

        Offer offer=new Offer(message,offeredPrice,targetedProduct,sender,receiver);
        offer.setId(id);
        return offer;





    }
}
