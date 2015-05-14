package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Buyer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Payment;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Local(PaymentDaoLocal.class)
public class PaymentDaoBean extends GenericDaoBean<Payment, Integer> implements
		PaymentDaoLocal {

	public List<Payment> findPaymentsByBuyer(Buyer buyer) {
		Query q = em.createNamedQuery("findPaymentsByBuyer");
		q.setParameter("buyer", buyer);

		return q.getResultList();
	}

}
