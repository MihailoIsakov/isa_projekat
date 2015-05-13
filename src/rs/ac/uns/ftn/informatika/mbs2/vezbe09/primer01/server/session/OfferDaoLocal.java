package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Category;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Offer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Seller;

import java.util.List;

public interface OfferDaoLocal extends GenericDaoLocal<Offer, Integer> {

	public Offer findOfferByName(String name) ;
	public List<Offer> findActiveOffers();
	public List<Offer> findByManager(Seller s);
	public List<Offer> findByCategory(Category cat);
}
