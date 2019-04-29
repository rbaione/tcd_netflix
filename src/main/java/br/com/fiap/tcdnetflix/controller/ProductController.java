package br.com.fiap.tcdnetflix.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.tcdnetflix.model.Product;
import br.com.fiap.tcdnetflix.repository.ProductRepository;

@RestController
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@GetMapping(value= "/loadMovies")
	public List<Product> populaBase() {
		List<Product> products = (List<Product>) productRepository.findAll();
		
		if(products.isEmpty()) {
			products = Arrays.asList(new Product("Filme1", "filmes"),
					new Product("Filme2", "filmes"),
					new Product("Filme3", "filmes"),
					new Product("Filme4", "filmes"));

			products.forEach(productRepository::save);
		}

		return products;

	}

	@GetMapping(value= "/movies")
	public List<Product> getProductsByCategory(@RequestParam(value="category") String category) {

		return productRepository.findByCategory(category);
	}

	@GetMapping(value= "/movie/{id}/details")
	public Optional<Product> getProductById(@PathVariable(value="id") Long id) {

		Optional<Product> product = productRepository.findById(id);
		
		return product;
	}
	

	@GetMapping(value= "/products/tags")
	public List<Product> getProductByTag(@RequestParam(value="tag") String tag) {

		return productRepository.findByTag(tag);
	}

}
