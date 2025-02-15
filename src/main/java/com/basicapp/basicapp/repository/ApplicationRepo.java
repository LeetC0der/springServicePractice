package com.basicapp.basicapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.basicapp.basicapp.models.ApplicationModle;

public interface ApplicationRepo extends JpaRepository<ApplicationModle, Long> {

    List<ApplicationModle> findByStatusOrderByNameAsc(String status);

    @Query("SELECT a FROM ApplicationModle a WHERE a.name LIKE %:name%")
    List<ApplicationModle> findByPartialName(@Param("name") String name);
}