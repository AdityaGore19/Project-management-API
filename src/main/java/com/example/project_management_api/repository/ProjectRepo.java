package com.example.project_management_api.repository;

import com.example.project_management_api.model.Project_Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepo extends JpaRepository<Project_Model,Long> {
}
