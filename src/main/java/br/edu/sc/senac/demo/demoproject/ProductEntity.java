package br.edu.sc.senac.demo.demoproject;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Product")
final class ProductEntity implements Serializable {

	private static final long serialVersionUID = 4391164751402917205L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;

	private String name;

	private String description;

	private Double price;
	
	protected ProductEntity() {
	}

	public ProductEntity(final String name, final String description, final Double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

	@Override
	public String toString() {
		return "ProductEntity [productId=" + this.productId + ", name=" + this.name + ", description=" + this.description + ", price=" + this.price + "]";
	}

	public Long getProdctId() {
		return this.productId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		if (name != null) {
			this.name = name;
		}
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		if (description != null) {
			this.description = description;
		}
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(final Double price) {
		if (price != null) {
			this.price = price;
		}
	}

}
