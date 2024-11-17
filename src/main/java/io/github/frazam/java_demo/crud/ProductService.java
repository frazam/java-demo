package io.github.frazam.java_demo.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Product findById(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
	}

	public Product save(Product product) {
		return productRepository.save(product);
	}

	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}
}
