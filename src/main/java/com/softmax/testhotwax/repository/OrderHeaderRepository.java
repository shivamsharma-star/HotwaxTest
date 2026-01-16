package com.softmax.testhotwax.repository;

import com.softmax.testhotwax.entity.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Integer> {
}
