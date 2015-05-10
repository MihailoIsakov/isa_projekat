package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQuery(name = "user.findUserWithCredentials", query = "SELECT k FROM User k WHERE k.username like :user AND k.password LIKE :pass")

@Entity
@Table(name="users")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class User {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private Integer id;

	// ovaj properti ce biti nasledjen
	@Column(name = "user_fname", unique = false, nullable = false)
	protected String firstName;

	@Column(name = "user_lname", unique = false, nullable = false)
	protected String lastName;

	@Column(name = "user_username", unique = true, nullable = false)
	protected String username;

	@JsonIgnore
	@Column(name = "user_password", unique = false, nullable = false)
	protected String password;
	
	public User(String firstName, String lastName, String username,
			String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}
	
	public User(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
