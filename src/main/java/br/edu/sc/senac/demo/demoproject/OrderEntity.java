package br.edu.sc.senac.demo.demoproject;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name = "OrderTable")
final class OrderEntity implements Serializable {

	private static final long serialVersionUID = -8258539815626513970L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId;

	private String clientName;

	@ManyToMany
	@Column(insertable = false, updatable = false)
	@JoinTable(
        name = "OrderItem",
        joinColumns = @JoinColumn(name = "item_orderId", referencedColumnName = "orderId"),
        inverseJoinColumns = @JoinColumn(name = "item_productId", referencedColumnName = "productId")
	)
	private Set<ProductEntity> products;

	protected OrderEntity() {
	}

	public OrderEntity(final String clientName) {
		this.clientName = clientName;
		this.products = new HashSet<>();
	}

	@Override
	public String toString() {
		return "OrderEntity [orderId=" + this.orderId + ", clientName=" + this.clientName + ", products=" + this.products + "]";
	}

	public Long getOrderId() {
		return this.orderId;
	}

	public String getClientName() {
		return this.clientName;
	}

	public void setClientName(final String clientName) {
		this.clientName = clientName;
	}

	public Set<ProductEntity> getProducts() {
		return this.products;
	}

}
