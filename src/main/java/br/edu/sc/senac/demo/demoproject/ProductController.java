package br.edu.sc.senac.demo.demoproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;

@Controller
final class ProductController {

	private final ProductRepository productRepository;

	ProductController(final ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	private static void updateEntityFromDTO(final ProductDTO productDTO, final ProductEntity productEntity) {
		productEntity.setName(productDTO.getName());
		productEntity.setDescription(productDTO.getDescription());
		productEntity.setPrice(productDTO.getPrice());
	}

	private static ProductEntity toEntity(final ProductDTO productDTO) {
		final String name = productDTO.getName();
		final String description = productDTO.getDescription();
		final Double price = productDTO.getPrice();
		return new ProductEntity(name, description, price);
	}

	private static ProductDTO toDTO(final ProductEntity productEntity) {
		final Long id = productEntity.getProdctId();
		final String name = productEntity.getName();
		final String description = productEntity.getDescription();
		final Double price = productEntity.getPrice();
		return new ProductDTO(id, name, description, price);
	}

	List<ProductDTO> getAllProducts() {
		final List<ProductDTO> products = new ArrayList<>();
		this.productRepository.findAll().forEach(productEntity -> products.add(ProductController.toDTO(productEntity)));
		/*
		 * final Iterable<ProductEntity> entities = this.productRepository.findAll();
		 * for (final ProductEntity productEntity : entities) {
		 * products.add(ProductController.toDTO(productEntity)); }
		 */
		/*
		 * for (final Iterator<ProductEntity> iterator = entities.iterator();
		 * iterator.hasNext();) { final ProductEntity productEntity = iterator.next();
		 * products.add(ProductController.toDTO(productEntity)); }
		 */
		return products;
	}

	ProductDTO getProduct(final Long id) {
		final Optional<ProductEntity> optionalProduct = this.productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			return ProductController.toDTO(optionalProduct.get());
		}
		return ProductDTO.NULL_VALUE;
	}

	ProductDTO removeProduct(final Long id) {
		final Optional<ProductEntity> optionalProduct = this.productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			final ProductEntity productEntity = optionalProduct.get();
			this.productRepository.delete(productEntity);
			return ProductController.toDTO(productEntity);
		}
		return ProductDTO.NULL_VALUE;
	}

	Long insertProduct(final ProductDTO productDTO) {
		final ProductEntity productEntity = ProductController.toEntity(productDTO);
		this.productRepository.save(productEntity);
		return productEntity.getProdctId();
	}

	ProductDTO updateProduct(final Long id, final ProductDTO productDTO) {
		final Optional<ProductEntity> optionalProduct = this.productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			final ProductEntity productEntity = optionalProduct.get();
			final ProductDTO oldProductDTO = ProductController.toDTO(productEntity);
			ProductController.updateEntityFromDTO(productDTO, productEntity);
			this.productRepository.save(productEntity);
			return oldProductDTO;
		}
		return ProductDTO.NULL_VALUE;
	}

}
