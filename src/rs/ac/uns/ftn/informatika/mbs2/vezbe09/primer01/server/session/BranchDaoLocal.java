package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.List;

import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Branch;

public interface BranchDaoLocal extends GenericDaoLocal<Branch, Integer> {

	public Branch findBranchByName(String name);
	public List<Branch> findByManager(int id);
}
