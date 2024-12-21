package junitTest;

import org.junit.jupiter.api.Test;
import main.Bank;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the deposit method in the Bank class.
 */
class BankDepositTest {

    // Valid deposit cases
    @Test
    void testDepositPositiveAmount() {
        Bank bank = new Bank(100);
        bank.deposit(50);
        assertEquals(150, bank.getBalance(), "Balance should increase correctly after a deposit");
    }

    @Test
    void testLargeDeposit() {
        Bank bank = new Bank(100);
        bank.deposit(1_000_000_000); // Large deposit
        assertEquals(1_000_000_100, bank.getBalance(), "Balance should handle large deposits correctly");
    }

    @Test
    void testDepositWithPrecision() {
        Bank bank = new Bank(100);
        bank.deposit(1234.56789); // High precision deposit
        assertEquals(1334.56789, bank.getBalance(), 1e-5, "Balance should handle high precision deposits correctly");
    }

    // Edge cases
    @Test
    void testDepositMinimumPositiveAmount() {
        Bank bank = new Bank(100);
        bank.deposit(Double.MIN_VALUE); // Smallest positive value greater than zero
        assertEquals(100 + Double.MIN_VALUE, bank.getBalance(), "Balance should handle minimum positive deposit correctly");
    }

    @Test
    void testDepositMaxDouble() {
        Bank bank = new Bank(0);
        bank.deposit(Double.MAX_VALUE); // Maximum possible deposit
        assertEquals(Double.MAX_VALUE, bank.getBalance(), "Balance should handle the maximum possible deposit correctly");
    }

    // Exception cases
    @Test
    void testDepositZeroThrowsException() {
        Bank bank = new Bank(100);
        assertThrows(IllegalArgumentException.class, () -> bank.deposit(0), "Depositing zero should throw an exception");
    }

    @Test
    void testDepositNegativeThrowsException() {
        Bank bank = new Bank(100);
        assertThrows(IllegalArgumentException.class, () -> bank.deposit(-50), "Depositing a negative amount should throw an exception");
    }

    @Test
    void testDepositNegativeInfinityThrowsException() {
        Bank bank = new Bank(100);
        assertThrows(IllegalArgumentException.class, () -> bank.deposit(Double.NEGATIVE_INFINITY), "Depositing negative infinity should throw an exception");
    }
}
