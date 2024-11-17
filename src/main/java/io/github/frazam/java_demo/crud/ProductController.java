package io.github.frazam.java_demo.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/crud/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	@GetMapping
	public List<Product> getAllProducts() {
		return productService.findAll();
	}

	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Long id) {
		return productService.findById(id);
	}

	@PostMapping
	public Product createProduct(@RequestBody Product product) {
		return productService.save(product);
	}

	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
		Product existingProduct = productService.findById(id);
		existingProduct.setName(product.getName());
		existingProduct.setPrice(product.getPrice());
		return productService.save(existingProduct);
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		productService.deleteById(id);
	}

	@GetMapping("/price-range")
	public List<Product> findProductsByPriceRange(@RequestParam double min, @RequestParam double max) {
		return productService.findProductsByPriceRange(min, max);
	}

	@GetMapping("/start-with")
	public List<Product> findProductsByPriceRange(@RequestParam String prefix) {
		return productService.findProductsByNameStartsWith(prefix);
	}

}
