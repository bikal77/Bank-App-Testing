package junitTest;

import org.junit.jupiter.api.Test;
import main.Bank;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class for the withdraw method in the Bank class.
 */
class BankWithdrawTest {

    // Valid withdrawal cases
    @Test
    void testWithdrawValidAmount() {
        Bank bank = new Bank(100);
        bank.withdraw(50);
        assertEquals(50, bank.getBalance(), "Balance should decrease correctly after a withdrawal");
    }

    @Test
    void testWithdrawExactBalance() {
        Bank bank = new Bank(100);
        bank.withdraw(100);
        assertEquals(0, bank.getBalance(), "Balance should be zero after withdrawing the exact balance");
    }

    @Test
    void testWithdrawSmallBalance() {
        Bank bank = new Bank(1);
        bank.withdraw(1); // Withdraw entire balance
        assertEquals(0, bank.getBalance(), "Balance should be zero after withdrawing the exact balance of 1");
    }

    @Test
    void testWithdrawTinyAmount() {
        Bank bank = new Bank(5);
        bank.withdraw(1); // Withdrawal smaller than balance
        assertEquals(4, bank.getBalance(), "Balance should decrease correctly after a small withdrawal");
    }

    @Test
    void testWithdrawMinValidAmount() {
        Bank bank = new Bank(100);
        bank.withdraw(0.01); // Withdraw the smallest valid amount
        assertEquals(99.99, bank.getBalance(), "Balance should decrease correctly after withdrawing the smallest valid amount");
    }

    @Test
    void testWithdrawWithFractionalBalance() {
        Bank bank = new Bank(0.05); // Small balance
        bank.withdraw(0.01); // Withdraw a fractional amount
        assertEquals(0.04, bank.getBalance(), "Balance should decrease correctly after withdrawing a fractional amount");
    }

    @Test
    void testWithdrawPrecisionEdgeCase() {
        Bank bank = new Bank(100);
        bank.withdraw(0.0000001); // A very small withdrawal amount
        assertEquals(99.9999999, bank.getBalance(), 1e-7, "Balance should decrease correctly after a very small withdrawal");
    }

    @Test
    void testWithdrawSmallFraction() {
        Bank bank = new Bank(0.0000001);
        bank.withdraw(0.0000001); // Withdraw the exact balance of the smallest fraction
        assertEquals(0, bank.getBalance(), "Balance should be zero after withdrawing the smallest fractional balance");
    }

    // Exception cases
    @Test
    void testWithdrawZeroThrowsException() {
        Bank bank = new Bank(100);
        assertThrows(IllegalArgumentException.class, () -> bank.withdraw(0), "Withdrawing zero should throw an exception");
    }

    @Test
    void testWithdrawNegativeThrowsException() {
        Bank bank = new Bank(100);
        assertThrows(IllegalArgumentException.class, () -> bank.withdraw(-50), "Withdrawing a negative amount should throw an exception");
    }

    @Test
    void testWithdrawExceedsBalanceThrowsException() {
        Bank bank = new Bank(100);
        assertThrows(IllegalStateException.class, () -> bank.withdraw(150), "Withdrawing more than the balance should throw an exception");
    }

    @Test
    void testWithdrawNegativeInfinityThrowsException() {
        Bank bank = new Bank(100);
        assertThrows(IllegalArgumentException.class, () -> bank.withdraw(Double.NEGATIVE_INFINITY), "Withdrawing negative infinity should throw an exception");
    }

    // Edge cases
    @Test
    void testWithdrawMaxDouble() {
        Bank bank = new Bank(Double.MAX_VALUE);
        bank.withdraw(100); // Withdraw a small amount from the largest balance
        assertEquals(Double.MAX_VALUE - 100, bank.getBalance(), "Balance should decrease correctly after withdrawing from the maximum balance");
    }

    @Test
    void testWithdrawMinimumPositiveBalance() {
        Bank bank = new Bank(Double.MIN_VALUE);
        bank.withdraw(Double.MIN_VALUE); // Withdraw the smallest positive balance
        assertEquals(0, bank.getBalance(), "Balance should be zero after withdrawing the smallest positive balance");
    }
}
