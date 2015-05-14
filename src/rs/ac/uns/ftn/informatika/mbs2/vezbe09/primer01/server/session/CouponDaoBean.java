package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import javax.ejb.Local;
import javax.ejb.Stateless;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Coupon;

@Stateless
@Local(CouponDaoLocal.class)
public class CouponDaoBean extends GenericDaoBean<Coupon, Integer> implements
		CouponDaoLocal {

}
