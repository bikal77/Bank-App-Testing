package junitTest;

import org.junit.jupiter.api.Test;
import main.Bank;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the constructor of the Bank class.
 */
class BankConstructorTest {

    // Valid initial balances
    @Test
    void testInitialBalancePositive() {
        Bank bank = new Bank(100);
        assertEquals(100, bank.getBalance(), "Balance should match the initial positive value");
    }

    @Test
    void testInitialBalanceZero() {
        Bank bank = new Bank(0);
        assertEquals(0, bank.getBalance(), "Balance should be zero for a zero initial balance");
    }

    @Test
    void testInitialBalanceLargePositive() {
        Bank bank = new Bank(Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, bank.getBalance(), "Balance should match the maximum double value");
    }

    @Test
    void testInitialBalanceMinimumPositive() {
        Bank bank = new Bank(Double.MIN_VALUE);
        assertEquals(Double.MIN_VALUE, bank.getBalance(), "Balance should match the smallest positive double value");
    }

    @Test
    void testInitialBalanceHighPrecision() {
        Bank bank = new Bank(1234.56789);
        assertEquals(1234.56789, bank.getBalance(), 1e-5, "Balance should match the high precision value");
    }

    // Exception cases
    @Test
    void testInitialBalanceNegativeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Bank(-100), "Negative balance should throw an exception");
    }

    @Test
    void testInitialBalanceNegativeInfinityThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Bank(Double.NEGATIVE_INFINITY), "Negative infinity should throw an exception");
    }

    // Special values
    @Test
    void testInitialBalancePositiveInfinity() {
        Bank bank = new Bank(Double.POSITIVE_INFINITY);
        assertEquals(Double.POSITIVE_INFINITY, bank.getBalance(), "Balance should match positive infinity");
    }
}
