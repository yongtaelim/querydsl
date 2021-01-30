package com.example.querydsl.house.repository;

import com.example.querydsl.house.entity.House;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HouseRepository extends JpaRepository<House, Long>, HouseQueryRepository {

    @Query("select h from House h join fetch h.staffs")
    List<House> findAllByJoinFetch();

    @EntityGraph(attributePaths = {"staffs"})
    @Query("select h from House h")
    List<House> findAllEntityGrapeWithStaff();
}
