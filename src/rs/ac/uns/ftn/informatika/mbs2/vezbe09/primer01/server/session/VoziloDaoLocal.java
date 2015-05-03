package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import java.util.List;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Vozilo;

public interface VoziloDaoLocal extends GenericDaoLocal<Vozilo, Integer> {

	public List<Vozilo> findVozilaSaKlimom();

}
