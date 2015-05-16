package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Branch;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Seller;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

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
	public List<Branch> findByManager(Seller s) {
		Query q = em
			.createNamedQuery("findBranchesByManager");
		q.setParameter("manager", s);
		return q.getResultList();
	}
}
