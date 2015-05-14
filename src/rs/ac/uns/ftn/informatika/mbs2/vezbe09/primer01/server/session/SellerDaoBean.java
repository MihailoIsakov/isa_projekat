package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Seller;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.User;

@Stateless
@Local(SellerDaoLocal.class)
public class SellerDaoBean extends GenericDaoBean<Seller, Integer> implements
		SellerDaoLocal {

	public Seller findSellerByUsername(
			String username) {
		Query q = em
				.createNamedQuery("findSellerByUsername");
		System.out.println(2);
		q.setParameter("username", username);
		Seller result = (Seller) q.getSingleResult();
		return result;
	}
}
