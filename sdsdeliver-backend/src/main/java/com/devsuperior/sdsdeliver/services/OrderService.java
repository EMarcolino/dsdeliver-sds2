package com.devsuperior.sdsdeliver.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.sdsdeliver.dto.OrderDTO;
import com.devsuperior.sdsdeliver.entities.Order;
import com.devsuperior.sdsdeliver.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;		//Injecao de dependencia:
	
	@Transactional(readOnly = true)
	public List<OrderDTO> findAll() {
		List<Order> list = repository.findOrderWithProducts();	
		return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
	}
}
