package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "buyers")
public class Buyer extends User {

	@Column(name = "buyer_phone", unique = false, nullable = false)
	private String phoneNumber;

	@Column(name = "buyer_email", unique = false, nullable = false)
	private String email;

	@Column(name = "buyer_address", unique = false, nullable = false)
	private String address;

	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "buyer")
	private Set<Payment> payments = new HashSet<Payment>();

	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "buyer")
	private Set<Coupon> coupons = new HashSet<Coupon>();

	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "buyer")
	private Set<Comment> comments = new HashSet<Comment>();

	public Buyer() {
	}
	
	public Buyer(String firstName, String lastName, String username,
			String password, String phoneNumber, String email, String address) {
		super(firstName, lastName, username, password);
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
	}


	public void add(Coupon p) {
		if (p.getBuyer() != null)
			p.getBuyer().getCoupons().remove(p);
		p.setBuyer(this);
		getCoupons().add(p);
	}

	public void remove(Coupon p) {
		p.setBuyer(null);
		getCoupons().remove(p);
	}

	public void add(Payment p) {
		if (p.getBuyer() != null)
			p.getBuyer().getPayments().remove(p);
		p.setBuyer(this);
		getPayments().add(p);
	}

	public void remove(Payment p) {
		p.setBuyer(null);
		getPayments().remove(p);
	}

	public void add(Comment p) {
		if (p.getBuyer() != null)
			p.getBuyer().getComments().remove(p);
		p.setBuyer(this);
		getComments().add(p);
	}

	public void remove(Comment p) {
		p.setBuyer(null);
		getComments().remove(p);
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	public Set<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(Set<Coupon> coupons) {
		this.coupons = coupons;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

}
