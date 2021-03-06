package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@NamedQuery(name = "findPaymentsByBuyer", query = "SELECT k from Payment k WHERE k.buyer = :buyer")

@Entity
@Table(name = "payments")
public class Payment implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "payment_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "payment_made", unique = false, nullable = false)
	private boolean paymentMade;

	@Column(name = "payment_canceled", unique = false, nullable = false)
	private boolean canceled;

	@Column(name = "payment_price", unique = false, nullable = false)
	private double price;

	@ManyToOne
	@JoinColumn(name = "offer_id", referencedColumnName = "offer_id", nullable = false)
	private Offer offer;

	@ManyToOne
	@JoinColumn(name = "buyer_id", referencedColumnName = "user_id", nullable = false)
	private Buyer buyer;

	public Payment(double price, Offer offer, Buyer buyer) {
		this.price = price;
		this.offer = offer;
		this.buyer = buyer;
	}

	public Payment() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isPaymentMade() {
		return paymentMade;
	}

	public void setPaymentMade(boolean paymentMade) {
		this.paymentMade = paymentMade;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	private static final long serialVersionUID = -1583467827576674112L;
}
