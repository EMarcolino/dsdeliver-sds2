package com.devsuperior.sdsdeliver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.sdsdeliver.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

	
	
}