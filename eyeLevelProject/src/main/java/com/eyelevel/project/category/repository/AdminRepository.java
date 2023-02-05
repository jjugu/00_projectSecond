package com.eyelevel.project.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyelevel.project.category.entity.Admin;


public interface AdminRepository extends JpaRepository<Admin, String>{
	Admin findByAdminId(String adminId);
}
