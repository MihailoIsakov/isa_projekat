package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Seller;

public interface SellerDaoLocal extends GenericDaoLocal<Seller, Integer> {

	public Seller findSellerByUsername(
			String username);
}
