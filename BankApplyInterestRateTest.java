package junitTest;

import org.junit.jupiter.api.Test;
import main.Bank;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the applyInterestRate method in the Bank class.
 */
class BankApplyInterestRateTest {

	// Valid inputs
	@Test
	void testApplyInterestRateValidInputs() {
		Bank bank = new Bank(100);
		double result = bank.applyInterestRate(100, 10);
		assertEquals(10.0, result);
	}

	@Test
	void testApplyInterestRateZeroRate() {
		Bank bank = new Bank(100);
		double result = bank.applyInterestRate(100, 0);
		assertEquals(0.0, result);
	}

	@Test
	void testApplyInterestRateLargeRate() {
		Bank bank = new Bank(100);
		double result = bank.applyInterestRate(100, 10_000);
		assertEquals(10_000, result);
	}

	@Test
	void testApplyInterestRateLargeBalance() {
		Bank bank = new Bank(10_000_000);
		double result = bank.applyInterestRate(10_000_000, 5);
		assertEquals(500_000, result);
	}

	@Test
	void testApplyInterestRateHighPrecisionRate() {
		Bank bank = new Bank(100);
		double result = bank.applyInterestRate(100, 0.0001);
		assertEquals(0.0001, result, 1e-5);
	}

	// Edge cases
	@Test
	void testApplyInterestRateZeroBalance() {
		Bank bank = new Bank(0);
		double result = bank.applyInterestRate(0, 10);
		assertEquals(0.0, result);
	}

	@Test
	void testApplyInterestRateVerySmallBalance() {
		Bank bank = new Bank(0.0001);
		double result = bank.applyInterestRate(0.0001, 10);
		assertEquals(0.00001, result, 1e-5);
	}

	@Test
	void testApplyInterestRateZeroBalanceAndRate() {
		Bank bank = new Bank(0);
		double result = bank.applyInterestRate(0, 0);
		assertEquals(0.0, result);
	}

	@Test
	void testApplyInterestRateVeryLargeRateWithSmallBalance() {
		Bank bank = new Bank(0.01);
		double result = bank.applyInterestRate(0.01, 100_000);
		assertEquals(10.0, result, 1e-2);
	}

}