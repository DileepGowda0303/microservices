package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Loans;

@Repository
public interface LoanRepository extends JpaRepository<Loans, Long> {
	Optional<Loans> findByMobileNumber(String mobileNumber);

	Optional<Loans> findByLoanNumber(String loanNumber);

}
