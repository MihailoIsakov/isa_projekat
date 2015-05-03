package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Gorivo;

@Stateless
@Local(GorivoDaoLocal.class)
public class GorivoDaoBean extends GenericDaoBean<Gorivo, Integer> implements
		GorivoDaoLocal {

}
