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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQuery(name = "findSellerByUsername", query = "SELECT k FROM Seller k WHERE k.username like :username")

@Entity
@Table(name = "sellers")
public class Seller extends User {

	@Column(name = "seller_phone", unique = false, nullable = false)
	private String phoneNumber;

	@Column(name = "seller_email", unique = false, nullable = false)
	private String email;

	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "manager")
	private Set<Offer> offers = new HashSet<Offer>();

	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "manager")
	private Set<Branch> branches = new HashSet<Branch>();

	public Seller() {
	}

	public Seller(String fname, String lname, String username, String password, String phoneNumber, String email) {
		super(fname, lname, username, password);
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	public void add(Offer p) {
		if (p.getManager() != null)
			p.getManager().getOffers().remove(p);
		p.setManager(this);
		getOffers().add(p);
	}

	public void remove(Offer p) {
		p.setManager(null);
		getOffers().remove(p);
	}

	public void add(Branch p) {
		if (p.getManager() != null)
			p.getManager().getBranches().remove(p);
		p.setManager(this);
		getBranches().add(p);
	}

	public void remove(Branch p) {
		p.setManager(null);
		getBranches().remove(p);
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

	public Set<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}

	public Set<Branch> getBranches() {
		return branches;
	}

	public void setBranches(Set<Branch> branches) {
		this.branches = branches;
	}

}
