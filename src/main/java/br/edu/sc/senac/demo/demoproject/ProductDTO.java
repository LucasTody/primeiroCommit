package br.edu.sc.senac.demo.demoproject;

public final class ProductDTO {

	public static final ProductDTO NULL_VALUE = new ProductDTO(Long.valueOf(0), "", "", Double.valueOf(0.0));

	private final Long id;
	private final String name;
	private final String description;
	private final Double price;
	
	public ProductDTO(final Long id, final String name, final String description, final Double price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public Double getPrice() {
		return this.price;
	}

}
