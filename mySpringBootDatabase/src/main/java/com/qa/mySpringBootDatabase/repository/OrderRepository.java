package com.qa.mySpringBootDatabase.repository;

import org.springframework.stereotype.Repository;

import com.qa.mySpringBootDatabase.model.Order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	Page<Order> findByPersonId(Long personId, Pageable pageable);
}
