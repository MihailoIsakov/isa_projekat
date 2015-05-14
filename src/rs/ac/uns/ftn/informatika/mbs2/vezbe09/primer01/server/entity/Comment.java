package rs.ac.uns.ftn.informatika.mbs2.vezbe09.primer01.server.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//import org.apache.openjpa.persistence.criteria.Expressions.Product;

@Entity
@Table(name = "comments")
public class Comment implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "comment_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "comment_msg", unique = false, nullable = true)
	private String message;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "offer_id", referencedColumnName = "offer_id", nullable = false)
	private Offer offer;

	@ManyToOne
	@JoinColumn(name = "buyer_id", referencedColumnName = "user_id", nullable = false)
	private Buyer buyer;

	public Comment() {
	}

	public Comment(String message, Offer offer, Buyer buyer) {
		this.message = message;
		this.offer = offer;
		this.buyer = buyer;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
