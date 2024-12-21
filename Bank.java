package main;

public class Bank {

	private double balance;

	public Bank(double initialBalance) {
		if (initialBalance < 0) {
			throw new IllegalArgumentException("Initial balance cannot be negative");
		}
		this.balance = initialBalance;
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Deposit amount must be positive");
		}
		balance += amount;
	}

	public void withdraw(double amount) {
		if (amount <= 0) {
			throw new IllegalArgumentException("Withdrawal amount must be positive");
		}
		if (amount > balance) {
			throw new IllegalStateException("Insufficient funds");
		}
		balance -= amount;
	}

	// Simple interest calculation
	public double calculateInterest(double interestRate) {
		if (interestRate < 0) {
			throw new IllegalArgumentException("Interest rate cannot be negative");
		}
		return applyInterestRate(balance, interestRate);
	}

    public double applyInterestRate(double balance, double interestRate) {
        return balance * (interestRate / 100);
    }
}