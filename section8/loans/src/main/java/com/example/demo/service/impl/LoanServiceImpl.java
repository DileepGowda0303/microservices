package com.example.demo.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.demo.constant.LoanConstant;
import com.example.demo.dto.LoansDto;
import com.example.demo.entity.Loans;
import com.example.demo.exception.LoanAlreadyExistsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.LoansMapper;
import com.example.demo.repository.LoanRepository;
import com.example.demo.service.ILoanService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService {
	private LoanRepository loansRepository;

	@Override
	public void createLoan(String mobileNumber) {
		Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);
		if (optionalLoans.isPresent()) {
			throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber " + mobileNumber);
		}
		loansRepository.save(createNewLoan(mobileNumber));
	}

	private Loans createNewLoan(String mobileNumber) {
		Loans newLoan = new Loans();
		long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
		newLoan.setLoanNumber(Long.toString(randomLoanNumber));
		newLoan.setMobileNumber(mobileNumber);
		newLoan.setLoanType(LoanConstant.HOME_LOAN);
		newLoan.setTotalLoan(LoanConstant.NEW_LOAN_LIMIT);
		newLoan.setAmountPaid(0);
		newLoan.setOutstandingAmount(LoanConstant.NEW_LOAN_LIMIT);
		return newLoan;
	}

	@Override
	public LoansDto fetchLoan(String mobileNumber) {
		Loans loans = loansRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));
		return LoansMapper.mapToLoansDto(loans, new LoansDto());
	}

	/**
	 *
	 * @param loansDto - LoansDto Object
	 * @return boolean indicating if the update of loan details is successful or not
	 */
	@Override
	public boolean updateLoan(LoansDto loansDto) {
		Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber())
				.orElseThrow(() -> new ResourceNotFoundException("Loan", "LoanNumber", loansDto.getLoanNumber()));
		LoansMapper.mapToLoans(loansDto, loans);
		loansRepository.save(loans);
		return true;
	}

	/**
	 * @param mobileNumber - Input MobileNumber
	 * @return boolean indicating if the delete of loan details is successful or not
	 */
	@Override
	public boolean deleteLoan(String mobileNumber) {
		Loans loans = loansRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));
		loansRepository.deleteById(loans.getLoanId());
		return true;
	}
}
