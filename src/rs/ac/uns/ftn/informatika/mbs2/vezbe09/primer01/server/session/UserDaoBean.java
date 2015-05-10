package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.User;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;


@Stateless
@Local(UserDaoLocal.class)
public class UserDaoBean extends GenericDaoBean<User, Integer>
		implements UserDaoLocal {

	public User findUserWithCredentials (String username, String password) {
		Query q = em.createNamedQuery("user.findUserWithCredentials");
		q.setParameter("user", username);
		q.setParameter("pass", password);
		User result = (User) q.getSingleResult();
		return result;
	}

}
