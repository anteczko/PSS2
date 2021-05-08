package com.example.demo.repository;

import com.example.demo.model.Delegation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DelegationRepository extends JpaRepository<Delegation,Integer> {
}
