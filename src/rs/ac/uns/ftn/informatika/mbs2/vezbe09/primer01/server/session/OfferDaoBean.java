package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Category;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Offer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Seller;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Local(OfferDaoLocal.class)
public class OfferDaoBean extends GenericDaoBean<Offer, Integer> implements
		OfferDaoLocal {

	public Offer findOfferByName(
			String name) {
		Query q = em
				.createNamedQuery("findOfferByName");
		q.setParameter("name", name);
		Offer result = (Offer) q.getSingleResult();
		return result;
	}
	
	//@SuppressWarnings("unchecked")
	public List<Offer> findActiveOffers() {
		Query q = em
				.createNamedQuery("findActiveOffers");
		return q.getResultList();
	}
	
	//@SuppressWarnings("unchecked")
	public List<Offer> findByManager(Seller s) {
		Query q = em
			.createNamedQuery("findOffersByManager");
		q.setParameter("manager", s);
		return q.getResultList();
	}

	public List<Offer> findByCategory(Category cat) {
		Query q = em.createNamedQuery("findOffersByCategory");
		q.setParameter("category", cat);
		return q.getResultList();
	}
}
