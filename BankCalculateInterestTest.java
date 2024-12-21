package junitTest;

import org.junit.jupiter.api.Test;
import main.Bank;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for the calculateInterest method in the Bank class.
 */
class BankCalculateInterestTest {

    // Valid cases
    @Test
    void testCalculateInterestValidRate() {
        // Arrange
        Bank bank = spy(new Bank(100)); // Spy on the Bank class
        doReturn(10.0).when(bank).applyInterestRate(100, 10); // Mock the dependent method

        // Act
        double interest = bank.calculateInterest(10);

        // Assert
        assertEquals(10.0, interest, "Interest should be calculated correctly for a valid rate");
        verify(bank).applyInterestRate(100, 10); // Verify the mocked method was called
    }

    @Test
    void testCalculateInterestZeroRate() {
        // Arrange
        Bank bank = spy(new Bank(100));
        doReturn(0.0).when(bank).applyInterestRate(100, 0);

        // Act
        double interest = bank.calculateInterest(0);

        // Assert
        assertEquals(0.0, interest, "Interest should be 0.0 when the rate is zero");
        verify(bank).applyInterestRate(100, 0);
    }

    @Test
    void testCalculateInterestSmallRate() {
        // Arrange
        Bank bank = spy(new Bank(100));
        doReturn(0.01).when(bank).applyInterestRate(100, 0.01);

        // Act
        double interest = bank.calculateInterest(0.01);

        // Assert
        assertEquals(0.01, interest, 0.0001, "Interest should be calculated correctly for a small rate");
        verify(bank).applyInterestRate(100, 0.01);
    }

    @Test
    void testCalculateInterestLargeBalance() {
        // Arrange
        Bank bank = spy(new Bank(1_000_000));
        doReturn(100_000.0).when(bank).applyInterestRate(1_000_000, 10);

        // Act
        double interest = bank.calculateInterest(10);

        // Assert
        assertEquals(100_000.0, interest, "Interest should be calculated correctly for a large balance");
        verify(bank).applyInterestRate(1_000_000, 10);
    }

    @Test
    void testCalculateInterestHighPrecisionRate() {
        // Arrange
        Bank bank = spy(new Bank(100));
        doReturn(0.0001).when(bank).applyInterestRate(100, 0.0001);

        // Act
        double interest = bank.calculateInterest(0.0001);

        // Assert
        assertEquals(0.0001, interest, 1e-5, "Interest should be calculated correctly for a high-precision rate");
        verify(bank).applyInterestRate(100, 0.0001);
    }

    // Edge cases
    @Test
    void testCalculateInterestNegativeRateThrowsException() {
        // Arrange
        Bank bank = new Bank(100); // No mocking needed for exception cases

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> bank.calculateInterest(-5),
                "Negative interest rate should throw an exception");
    }

    // Exception handling
    @Test
    void testCalculateInterestMockThrowsException() {
        // Arrange
        Bank bank = spy(new Bank(100));
        doThrow(new RuntimeException("Mock exception")).when(bank).applyInterestRate(anyDouble(), anyDouble());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> bank.calculateInterest(10),
                "Should throw a mocked exception when applyInterestRate is invoked");
    }

    // Additional test: Verify method chaining
    @Test
    void testCalculateInterestVerifiesMethodCall() {
        // Arrange
        Bank bank = spy(new Bank(100));
        doReturn(10.0).when(bank).applyInterestRate(100, 10);

        // Act
        double interest = bank.calculateInterest(10);

        // Assert
        verify(bank).applyInterestRate(100, 10); // Verify the interaction with applyInterestRate
        assertEquals(10.0, interest, "Interest calculation should delegate to applyInterestRate");
    }
}
