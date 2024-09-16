package org.hacktn.initialr.app.repository;


import org.hacktn.initialr.app.model.GitRepoOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GitRequestRepository extends JpaRepository<GitRepoOrder, Long> {
}