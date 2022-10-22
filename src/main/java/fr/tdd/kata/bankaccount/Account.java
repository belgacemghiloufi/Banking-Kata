package fr.tdd.kata.bankaccount;

import java.math.BigDecimal;

public class Account {
	
	private BigDecimal balance = BigDecimal.ZERO;

	public void deposit(BigDecimal amount) {
		balance = balance.add(amount);
	}

	public BigDecimal getBalance() {
		return balance;
	}

}
