package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Branch;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Seller;

import java.util.List;

public interface BranchDaoLocal extends GenericDaoLocal<Branch, Integer> {

	public Branch findBranchByName(String name);
	public List<Branch> findByManager(Seller s);
}
