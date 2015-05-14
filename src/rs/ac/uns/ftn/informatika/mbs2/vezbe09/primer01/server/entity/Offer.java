package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries( { @NamedQuery(name = "findOfferByName", query = "SELECT k FROM Offer k WHERE k.name like :name")
,@NamedQuery(name = "findActiveOffers", query = "SELECT k FROM Offer k WHERE k.active = true")
,@NamedQuery(name = "findOffersByCategory", query = "SELECT k FROM Offer k WHERE k.category = :category")
,@NamedQuery(name = "findOffersByManager", query = "SELECT k FROM Offer k WHERE k.manager = :manager")})



@Entity
@Table(name = "offers")
public class Offer implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "offer_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "offer_name", unique = true, nullable = false)
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "offer_dateCreated", unique = false, nullable = false)
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "offer_expiration", unique = false, nullable = false)
	private Date expirationDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "offer_validto", unique = false, nullable = false)
	private Date validTo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "offer_validfrom", unique = false, nullable = false)
	private Date validFrom;

	@Column(name = "offer_regprc", unique = false, nullable = true)
	private double regPrice;

	@Column(name = "offer_saleprc", unique = false, nullable = true)
	private double salePrice;

	@Column(name = "offers_max", unique = false, nullable = true)
	private int maxOffers;

	@Column(name = "offer_desc", unique = false, nullable = true)
	private String description;

	@Column(name = "offers_purch", unique = false, nullable = true)
	private int purchasedOffers;

	@Column(name = "offer_active", unique = false, nullable = true)
	private boolean active;

	@OneToMany(cascade = { ALL }, fetch = EAGER, mappedBy = "offer")
	private Set<Payment> payments = new HashSet<Payment>();

	@OneToMany(cascade = { ALL }, fetch = EAGER, mappedBy = "offer")
	private Set<Coupon> coupons = new HashSet<Coupon>();

	@OneToMany(cascade = { ALL }, fetch = EAGER, mappedBy = "offer")
	private Set<Comment> comments = new HashSet<Comment>();

	@OneToMany(cascade = { ALL }, fetch = EAGER, mappedBy = "offer")
	private Set<Image> images = new HashSet<Image>();

	@ManyToOne
	@JoinColumn(name = "manager_id", referencedColumnName = "user_id", nullable = false)
	private Seller manager;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
	private Category category;

	
	public Offer(String name, Date dateCreated, Date expirationDate,
			Date validTo, Date validFrom, double regPrice, double salePrice,
			int maxOffers, String description, int purchasedOffers,
			boolean active, Seller manager, Category category) {
		super();
		this.name = name;
		this.dateCreated = dateCreated;
		this.expirationDate = expirationDate;
		this.validTo = validTo;
		this.validFrom = validFrom;
		this.regPrice = regPrice;
		this.salePrice = salePrice;
		this.maxOffers = maxOffers;
		this.description = description;
		this.purchasedOffers = purchasedOffers;
		this.active = active;
		this.manager = manager;
		this.category = category;
	}

	public void add(Image p) {
		if (p.getOffer() != null)
			p.getOffer().getImages().remove(p);
		p.setOffer(this);
		getImages().add(p);
	}

	public void remove(Image p) {
		p.setOffer(null);
		getImages().remove(p);
	}

	public void add(Comment p) {
		if (p.getOffer() != null)
			p.getOffer().getComments().remove(p);
		p.setOffer(this);
		getComments().add(p);
	}

	public void remove(Comment p) {
		p.setOffer(null);
		getComments().remove(p);
	}

	public void add(Payment p) {
		if (p.getOffer() != null)
			p.getOffer().getPayments().remove(p);
		p.setOffer(this);
		getPayments().add(p);
	}

	public void remove(Payment p) {
		p.setOffer(null);
		getPayments().remove(p);
	}

	public void add(Coupon p) {
		if (p.getOffer() != null)
			p.getOffer().getCoupons().remove(p);
		p.setOffer(this);
		getCoupons().add(p);
	}

	public void remove(Coupon p) {
		p.setOffer(null);
		getCoupons().remove(p);
	}

	public Offer() {
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public double getRegularPrice() {
		return regPrice;
	}

	public void setRegularPrice(double regPrice) {
		this.regPrice = regPrice;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
	}

	public int getMaxOffers() {
		return maxOffers;
	}

	public void setMaxOffers(int maxOffers) {
		this.maxOffers = maxOffers;
	}

	public int getPurchasedOffers() {
		return purchasedOffers;
	}

	public void setPurchasedOffers(int purchasedOffers) {
		this.purchasedOffers = purchasedOffers;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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

	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	public Seller getManager() {
		return manager;
	}

	public void setManager(Seller manager) {
		this.manager = manager;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		return "(Offer)[id=" + id + ",name=" + name + ",description="
				+ description + "]";
	}

	private static final long serialVersionUID = 5969496673973694792L;
}
