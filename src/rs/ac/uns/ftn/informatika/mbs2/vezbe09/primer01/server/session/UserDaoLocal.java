package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.User;

public interface UserDaoLocal extends GenericDaoLocal<User, Integer> {

	User findUserWithCredentials(String username, String password);

}
