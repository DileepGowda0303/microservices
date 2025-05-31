package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Account;

import jakarta.transaction.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findByCustomerId(Long customerId);

	@Transactional
	@Modifying
	void deleteByCustomerId(Long customerId);
}
