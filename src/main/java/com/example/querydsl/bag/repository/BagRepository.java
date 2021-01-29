package com.example.querydsl.bag.repository;

import com.example.querydsl.bag.entity.Bag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BagRepository extends JpaRepository<Bag, Long>, BagQueryRepository {
}
