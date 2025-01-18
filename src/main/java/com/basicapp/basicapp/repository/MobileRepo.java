package com.basicapp.basicapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.basicapp.basicapp.models.MobileModel;

public interface MobileRepo extends JpaRepository<MobileModel, Long> {

}
