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
@Table(name = "images")
public class Image implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "image_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "image_location", unique = false, nullable = true)
	private String location;

	@Column(name = "image_title", unique = false, nullable = true)
	private String title;

	@Column(name = "image_width", unique = false, nullable = false)
	private int width;

	@Column(name = "image_height", unique = false, nullable = false)
	private int height;

	@Column(name = "content_type", unique = false, nullable = false)
	private String contentType;

	@Lob
	// large object
	@Basic(fetch = LAZY)
	// LAZY vs EAGER - da li se ceo atribut loaduje zajedno sa objektom (lazy:
	// attr=null, eager attr=loadedobj)
	@Column(name = "image_data", unique = false, nullable = true)
	private byte[] imageData;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "offer_id", referencedColumnName = "offer_id", nullable = false)
	private Offer offer;

	public Image() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	private static final long serialVersionUID = -1583467827576674112L;
}
