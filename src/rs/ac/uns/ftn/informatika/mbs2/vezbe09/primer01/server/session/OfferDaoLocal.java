package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.List;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Offer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Seller;

public interface OfferDaoLocal extends GenericDaoLocal<Offer, Integer> {

	public Offer findOfferByName(String name) ;
	public List<Offer> findActiveOffers();
	public List<Offer> findByManager(Seller s);
}
