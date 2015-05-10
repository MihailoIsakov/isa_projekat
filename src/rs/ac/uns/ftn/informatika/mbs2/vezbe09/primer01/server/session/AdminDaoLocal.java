package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Admin;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.User;

public interface AdminDaoLocal extends GenericDaoLocal<Admin, Integer> {
	public User findUserWithCredentials(String username, String password);
}
