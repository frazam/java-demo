package io.github.frazam.java_demo.crud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@PersistenceContext
	private EntityManager entityManager;

	// Repository auto-implemented method: Using Spring Data JPA repository methods
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

	// Criteria API: Using CriteriaBuilder for dynamic query construction
	@Transactional(readOnly = true)
	public List<Product> findProductsByPriceRange(double minPrice, double maxPrice) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		Root<Product> product = cq.from(Product.class);
		cq.select(product).where(cb.between(product.get("price"), minPrice, maxPrice));
		return entityManager.createQuery(cq).getResultList();
	}

	// JPQL: Using Java Persistence Query Language for object-oriented queries
	@Transactional(readOnly = true)
	public List<Product> findProductsByNameStartsWith(String prefix) {
		String jpql = "SELECT p FROM Product p WHERE p.name like :prefix";
		return entityManager.createQuery(jpql, Product.class) //
				.setParameter("prefix", prefix + "%") //
				.getResultList();
	}
}
