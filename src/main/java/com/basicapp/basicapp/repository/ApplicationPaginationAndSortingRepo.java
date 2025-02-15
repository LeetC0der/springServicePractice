package com.basicapp.basicapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.basicapp.basicapp.models.ApplicationModle;

public interface ApplicationPaginationAndSortingRepo extends PagingAndSortingRepository<ApplicationModle, Long> {

}
