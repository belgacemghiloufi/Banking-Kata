package fr.tdd.kata.bankaccount;

import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class StatementPrinter {

	public static final String STATEMENT_HEADER = "OPERATION | DATE | AMOUNT | BALANCE";
	private Console console;

	public StatementPrinter(Console console) {
		this.console = console;
	}

	public void print(List<Transaction> transactions) {
		console.printLine(STATEMENT_HEADER);
		List<String> statementLines = statementLines(transactions);
		statementLines.forEach(console::printLine);
	}
	
	private List<String> statementLines(List<Transaction> transactions) {
		AtomicReference<BigDecimal> runningBalance = new AtomicReference<BigDecimal>(new BigDecimal(0.00));
		return transactions.stream()
									  .map( transaction -> statementLine(runningBalance, transaction))
									  .collect(toList());
		
	}
	
	private String statementLine(AtomicReference<BigDecimal> runningBalance , Transaction transaction) {
		var amount = (transaction.getOperation() == Operation.DEPOSIT) ? transaction.getAmount() : transaction.getAmount().negate();
		return transaction.getOperation() 
				   + " | " +
				   transaction.getDate()
				   + " | " +
				   formatDecimal(amount)
				   + " | " +
				   formatDecimal(runningBalance.accumulateAndGet(amount, (u,v) -> u.add(v)));
	}
	
	private String formatDecimal(BigDecimal amount) {
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		return decimalFormat.format(amount);
	}
	
	
}
