package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Branch;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Offer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Seller;

@Stateless
@Local(BranchDaoLocal.class)
public class BranchDaoBean extends GenericDaoBean<Branch, Integer> implements
		BranchDaoLocal {

	public Branch findBranchByName(
			String name) {
		Query q = em
				.createNamedQuery("findBranchByName");
		q.setParameter("name", name);
		Branch result = (Branch) q.getSingleResult();
		return result;
	}
	//@SuppressWarnings("unchecked")
	public List<Branch> findByManager(int id) {
		Query q = em
			.createNamedQuery("findBranchesByManager");
		q.setParameter("manager", id);
		return q.getResultList();
	}
}
