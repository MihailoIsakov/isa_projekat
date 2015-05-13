package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@NamedQuery(name = "findCategoryByName", query = "SELECT k FROM Category k WHERE k.name like :name")

@Entity
@Table(name = "categories")
public class Category implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "category_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "cat_name", unique = true, nullable = false)
	private String name;

	@Column(name = "cat_desc", unique = false, nullable = true)
	private String description;

	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "category")
	private Set<Offer> offers = new HashSet<Offer>();

	public void add(Offer p) {
		if (p.getCategory() != null)
			p.getCategory().getOffers().remove(p);
		p.setCategory(this);
		getOffers().add(p);
	}

	public void remove(Offer p) {
		p.setCategory(null);
		getOffers().remove(p);
	}

	public Category() {
	}
	
	

	public Category(String name, String description) {
		super();
		this.name = name;
		this.description = description;
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

	public Set<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}

	public String toString() {
		return "(Category)[id=" + id + ",name=" + name + ",description="
				+ description + "]";
	}

	private static final long serialVersionUID = 5969496673973694792L;
}
