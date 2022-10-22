package fr.tdd.kata.bankaccount;

import java.math.BigDecimal;

public class Account {

	private StatementPrinter statementPrinter;
	private TransactionRepository transactionRepository;

	public Account(TransactionRepository transactionRepository, StatementPrinter statementPrinter) {
		this.statementPrinter = statementPrinter;
		this.transactionRepository = transactionRepository;
	}

	public void deposit(BigDecimal amount) {
		if (amount.signum() == -1)
			throw new IllegalArgumentException(String.format("Should not deposit a negative amount: %s", amount));
		transactionRepository.addDepositTransaction(amount);
	}

	public BigDecimal getBalance() {
		BigDecimal  balance = transactionRepository.getTransactions()
										  .stream()
										  .map(transaction -> (transaction.getOperation() == Operation.DEPOSIT) ? transaction.getAmount() : transaction.getAmount().negate())
										  .reduce(BigDecimal.ZERO, BigDecimal::add);
		return balance;
	}

	public void withdraw(BigDecimal amount) {
		if (amount.signum() == -1)
			throw new IllegalArgumentException(String.format("Should not withdraw a negative amount: %s", amount));
		BigDecimal  balance = getBalance();
		if (balance.compareTo(amount) < 0)
			throw new IllegalArgumentException(String.format("Should not withdraw amount %s more than balance %s", amount, balance));
		transactionRepository.addWithdrawTransaction(amount);
	}

	public void printStatement() {
		statementPrinter.print(transactionRepository.getTransactions());
	}

}
