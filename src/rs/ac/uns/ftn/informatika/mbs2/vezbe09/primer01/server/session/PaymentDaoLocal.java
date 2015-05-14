package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.session;

import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Buyer;
import rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity.Payment;

import java.util.List;

public interface PaymentDaoLocal extends GenericDaoLocal<Payment , Integer> {
    public List<Payment> findPaymentsByBuyer(Buyer buyer);
}
