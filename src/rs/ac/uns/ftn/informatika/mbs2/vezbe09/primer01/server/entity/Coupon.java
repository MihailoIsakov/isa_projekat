package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//import org.apache.openjpa.persistence.criteria.Expressions.Product;

@Entity
@Table(name = "coupons")
public class Coupon implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "coupon_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "coupon_identifier", unique = true, nullable = true)
	private String couponIdentifier;

	@Column(name = "coupon_used", unique = false, nullable = false)
	private boolean used;

	@Column(name = "coupon_active", unique = false, nullable = false)
	private boolean active;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "coupon_validto", unique = false, nullable = false)
	private Date validTo;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "offer_id", referencedColumnName = "offer_id", nullable = false)
	private Offer offer;

	@ManyToOne
	@JoinColumn(name = "buyer_id", referencedColumnName = "user_id", nullable = false)
	private Buyer buyer;

	public Coupon() {
	}

	public String getCouponIdentifier() {
		return couponIdentifier;
	}

	public void setCouponIdentifier(String couponIdentifier) {
		this.couponIdentifier = couponIdentifier;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
