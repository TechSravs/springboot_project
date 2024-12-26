package com.springboot.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.entity.Product;
import com.springboot.repository.ProductRepository;

@Service
public class ProductService {
	private ProductRepository repository;
	
	@Autowired
	public ProductService(ProductRepository repository) {
		this .repository = repository;
	}
	//i want to add a new product into a table
	public Product addProduct(Product p1) {
		return repository.save(p1);
		
	}
	
	public List<Product> viewProduct() {
		return repository.findAll();
		
		
	}
	public Optional<Product> getProductById(int id) {
		Optional<Product>p1= repository.findById(id);
		if(p1.isPresent()) {
			return p1;
		}
		else {
			return null;
		}
	}
	public List<Product> getProductByPrice(int price) {
		return repository.getProductByPrice(price);
		
	}
	public String deleteProductById(int id) {
		Optional<Product> p1=repository.findById(id);
		if(p1.isPresent()) {
	       repository.deleteById(id);
	       return "delete id "+ id + " successfully" ;
		}
		else {
			return id+" is not exist";
			
		}
	}
	public Product updateProduct(int id,Product newProduct) {
		//chick product id is available
		//if available get the product from db
	    //modify the date using stters
		Optional<Product> p= repository.findById(id);
		if(p.isPresent()) {
			Product productFromDB = p.get();
			productFromDB.setPname(newProduct.getPname());
			productFromDB.setPrice(newProduct.getPrice());
			productFromDB.setQty(newProduct.getQty());
			return repository.save(productFromDB);
			
		}else
		{
			throw new RuntimeException("Invalid product id");
		}
	}
	
		
	}
	


