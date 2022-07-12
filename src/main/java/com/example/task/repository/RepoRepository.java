package com.example.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.task.entity.Repo;

@Repository
public interface RepoRepository extends JpaRepository<Repo, Long> {

}


