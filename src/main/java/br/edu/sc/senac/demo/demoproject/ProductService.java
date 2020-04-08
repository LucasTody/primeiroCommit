package br.edu.sc.senac.demo.demoproject;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
public final class ProductService {

	private static final ProductDTO[] DEFAULT_PRODUCTS = new ProductDTO[] {
			new ProductDTO(Long.valueOf(0), "Phone XL", "A large phone with one of the best screens", Double.valueOf(799)),
			new ProductDTO(Long.valueOf(0), "Phone Mini", "A great phone with one of the best cameras", Double.valueOf(699)),
			new ProductDTO(Long.valueOf(0), "Phone Standard", "", Double.valueOf(299)) };

	private final ProductController productController;

	ProductService(final ProductController productController) {
		this.productController = productController;
		Arrays.asList(ProductService.DEFAULT_PRODUCTS).forEach(dto -> this.productController.insertProduct(dto));
	}

	@GetMapping("/list")
	public List<ProductDTO> list() {
		return this.productController.getAllProducts();
	}

	@GetMapping("/{id}/details")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable final Long id) {
		final ProductDTO product = this.productController.getProduct(id);
		if (product.equals(ProductDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ProductDTO> removeProduct(@PathVariable final Long id) {
		final ProductDTO removedProduct = this.productController.removeProduct(id);
		if (removedProduct.equals(ProductDTO.NULL_VALUE)) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(removedProduct, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> updateProduct(@PathVariable final Long id, @RequestBody final ProductDTO product) {
		final ProductDTO oldProduct = this.productController.updateProduct(id, product);
		if (oldProduct.equals(ProductDTO.NULL_VALUE)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(oldProduct, HttpStatus.OK);
	}

	@PostMapping
	public Long insertProduct(@RequestBody final ProductDTO product) {
		return this.productController.insertProduct(product);
	}

}
