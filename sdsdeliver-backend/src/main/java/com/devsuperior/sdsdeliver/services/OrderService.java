package com.devsuperior.sdsdeliver.services;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.sdsdeliver.dto.OrderDTO;
import com.devsuperior.sdsdeliver.dto.ProductDTO;
import com.devsuperior.sdsdeliver.entities.Order;
import com.devsuperior.sdsdeliver.entities.OrderStatus;
import com.devsuperior.sdsdeliver.entities.Product;
import com.devsuperior.sdsdeliver.repositories.OrderRepository;
import com.devsuperior.sdsdeliver.repositories.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;		//Injecao de dependencia:
	
	@Autowired
	private ProductRepository productRepository;		//Injecao de dependencia:
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll() {
		List<Order> list = orderRepository.findOrderWithProducts();	
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public OrderDTO insert(Order dto) {
		Order order = new Order(null, dto.getAddress(), dto.getLatitude(), dto.getLongitude(), 
				Instant.now(), OrderStatus.PENDING);
		for (ProductDTO p : dto.getProducts()) {
			Product product = productRepository.getOne(p.getId());
			order.getProducts().add(product);
		}
		order = orderRepository.save(order);
		return new OrderDTO(order);
	}
}
