package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Buyer;

@Stateless
@Local(BuyerDaoLocal.class)
public class BuyerDaoBean extends GenericDaoBean<Buyer, Integer> implements
		BuyerDaoLocal {

}
