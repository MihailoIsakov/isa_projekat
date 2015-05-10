package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

//import org.apache.openjpa.persistence.criteria.Expressions.Product;
@NamedQueries({@NamedQuery(name = "findBranchByName", query = "SELECT k FROM Branch k WHERE k.name like :name")
,@NamedQuery(name = "findBranchesByManager", query = "SELECT k FROM Branch k WHERE k.manager = :manager")})


@Entity
@Table(name = "branches")
public class Branch implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "branch_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "branch_name", unique = true, nullable = false)
	private String name;

	@Column(name = "branch_phone", unique = false, nullable = true)
	private String phoneNumber;

	@Column(name = "branch_address", unique = false, nullable = false)
	private String address;

	@ManyToOne
	@JoinColumn(name = "manager_id", referencedColumnName = "user_id", nullable = false)
	private Seller manager;

	public Branch() {
	}
	
	public Branch(String name, String phoneNumber, String address,
			Seller manager) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.manager = manager;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Seller getManager() {
		return manager;
	}

	public void setManager(Seller manager) {
		this.manager = manager;
	}

	private static final long serialVersionUID = -1583467827576674112L;
}
