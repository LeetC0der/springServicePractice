package com.basicapp.basicapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.basicapp.basicapp.models.EmailTemplateModel;

public interface EmailTemplateRepo extends JpaRepository<EmailTemplateModel, Long>{
    @Query("SELECT et FROM EmailTemplateModel et WHERE LOWER(et.templateName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<EmailTemplateModel> findByPartialName(@Param("name") String name);
}
