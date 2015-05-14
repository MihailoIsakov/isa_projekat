package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Image;

@Stateless
@Local(ImageDaoLocal.class)
public class ImageDaoBean extends GenericDaoBean<Image, Integer> implements
ImageDaoLocal {

}
