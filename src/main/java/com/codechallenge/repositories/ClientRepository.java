package com.codechallenge.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codechallenge.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	Optional<Client> findByNameAndLastName(String name, String lastName);
	
}