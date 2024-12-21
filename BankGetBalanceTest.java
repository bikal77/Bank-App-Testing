package junitTest;

import org.junit.jupiter.api.Test;
import main.Bank;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the getBalance method in the Bank class.
 */
class BankGetBalanceTest {

    // Valid cases
    @Test
    void testGetBalanceAfterInitialization() {
        Bank bank = new Bank(100);
        assertEquals(100, bank.getBalance(), "getBalance should return the initial balance correctly");
    }

    @Test
    void testGetBalanceAfterDeposit() {
        Bank bank = new Bank(100);
        bank.deposit(50);
        assertEquals(150, bank.getBalance(), "getBalance should return the updated balance after a deposit");
    }

    @Test
    void testGetBalanceAfterWithdrawal() {
        Bank bank = new Bank(100);
        bank.withdraw(50);
        assertEquals(50, bank.getBalance(), "getBalance should return the updated balance after a withdrawal");
    }

    @Test
    void testGetBalanceAfterExactWithdrawal() {
        Bank bank = new Bank(100);
        bank.withdraw(100);
        assertEquals(0, bank.getBalance(), "getBalance should return zero after withdrawing the entire balance");
    }

    @Test
    void testGetBalanceWithHighPrecision() {
        Bank bank = new Bank(1234.56789);
        assertEquals(1234.56789, bank.getBalance(), 1e-5, "getBalance should return the balance with high precision");
    }

    @Test
    void testGetBalanceAfterTinyDeposit() {
        Bank bank = new Bank(100);
        bank.deposit(0.000001);
        assertEquals(100.000001, bank.getBalance(), 1e-6, "getBalance should return the correct balance after a tiny deposit");
    }

    // Edge cases
    @Test
    void testGetBalanceWithLargeInitialValue() {
        Bank bank = new Bank(Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, bank.getBalance(), "getBalance should handle the maximum double value correctly");
    }

    @Test
    void testGetBalanceWithSmallInitialValue() {
        Bank bank = new Bank(Double.MIN_VALUE);
        assertEquals(Double.MIN_VALUE, bank.getBalance(), "getBalance should handle the minimum positive double value correctly");
    }

    @Test
    void testGetBalanceAfterMultipleOperations() {
        Bank bank = new Bank(100);
        bank.deposit(50);
        bank.withdraw(30);
        bank.deposit(20);
        assertEquals(140, bank.getBalance(), "getBalance should return the correct balance after multiple operations");
    }

    // Special cases
    @Test
    void testGetBalanceZeroAfterInitialization() {
        Bank bank = new Bank(0);
        assertEquals(0, bank.getBalance(), "getBalance should return zero for a zero-initialized balance");
    }

    @Test
    void testGetBalanceWithFractionalOperations() {
        Bank bank = new Bank(100.50);
        bank.deposit(0.25);
        bank.withdraw(50.75);
        assertEquals(50.0, bank.getBalance(), "getBalance should handle fractional deposits and withdrawals correctly");
    }
}
