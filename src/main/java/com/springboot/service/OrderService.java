package com.springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.entity.Order1;
import com.springboot.entity.OrderWrapper;
import com.springboot.entity.Product;
import com.springboot.repository.OrderRepository;
import com.springboot.repository.ProductRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
    private ProductRepository productRepository;
	public ResponseEntity<OrderWrapper> placeOrder(int id, int qty) {
		//check product is available
		
		Optional<Product> p1=productRepository.findById(id);
		if(p1.isPresent()){
			//check the quantity in product
			Product p=p1.get();
			if(p.getQty()>=qty) {
				//create a new order and set the values
				//product,qty,total price,status
				Order1 o= new Order1();
				o.setProduct(p);
				o.setQty(qty);
				o.setStatus("placed");
				o.setTotalprice(p.getPrice() * qty);
				p.setQty(p.getQty() - qty);
				
				orderRepository.save(o);
				OrderWrapper ow= new OrderWrapper(o.getOid(),o.getTotalprice(),o.getQty(),o.getStatus(),o.getProduct().getPname());
				//return the string that order is placed.
				return ResponseEntity.ok(ow);
			}
			else {
			    throw new RuntimeException("stock unavailble");
			}
			
		}
		else {
			throw new RuntimeException("product is not available");
		}
		
	}
	public String cancelOrder(int oid) {
		Optional<Order1> existingOrder = orderRepository.findById(oid);
		if(existingOrder.isPresent()) {
			Product p=existingOrder.get().getProduct();
			p.setQty(p.getQty() + existingOrder.get().getQty());
			productRepository.save(p);
			orderRepository.deleteById(oid);
			return "order cancelled successfully";
		}
		else {
			return "Invalid Order Id";
		}
		
	}
}
