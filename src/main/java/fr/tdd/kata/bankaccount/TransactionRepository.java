package fr.tdd.kata.bankaccount;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TransactionRepository {
	
	private DateHelper dateHelper;
	private List<Transaction> transactions = new ArrayList<>();

	public TransactionRepository(DateHelper dateHelper) {
		this.dateHelper = dateHelper;
	}

	public List<Transaction> getTransactions() {
		return Collections.unmodifiableList(transactions);
	}

	public void addDepositTransaction(BigDecimal amount) {
		Transaction deposit = new Transaction(dateHelper.getTodayAsString(), amount, Operation.DEPOSIT);
		transactions.add(deposit);
	}

	public void addWithdrawTransaction(BigDecimal amount) {
		Transaction withdraw = new Transaction(dateHelper.getTodayAsString(), amount, Operation.WITHDRAWAL);
		transactions.add(withdraw);
	}

}
