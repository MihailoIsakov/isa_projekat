package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Category;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless
@Local(CategoryDaoLocal.class)
public class CategoryDaoBean extends GenericDaoBean<Category, Integer>
		implements CategoryDaoLocal {

	public Category findCategoryByName(
			String name) {
		Query q = em
				.createNamedQuery("findCategoryByName");
		q.setParameter("name", name);
		Category result = (Category) q.getSingleResult();
		return result;
	}

}
