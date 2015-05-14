package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Comment;

@Stateless
@Local(CommentDaoLocal.class)
public class CommentDaoBean extends GenericDaoBean<Comment, Integer> implements
		CommentDaoLocal {

}
