package fr.tdd.kata.bankaccount;

import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

public class StatementPrinter {

	public static final String STATEMENT_HEADER = "OPERATION | DATE | AMOUNT | BALANCE";
	private Console console;

	public StatementPrinter(Console console) {
		this.console = console;
	}

	public void print(List<Transaction> transactions) {
		
		console.printLine(STATEMENT_HEADER);
		
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		
		AtomicReference<BigDecimal> runningBalance = new AtomicReference<BigDecimal>(new BigDecimal(0.00));
		
		Function<Transaction, String> fn = transaction -> {
			
			var amount = (transaction.getOperation() == Operation.DEPOSIT) ? transaction.getAmount() : transaction.getAmount().negate();
			
			String statementLine = transaction.getOperation()
															   + " | " + 
															   transaction.getDate() 
															   + " | " + 
															   decimalFormat.format(amount)
															   + " | " + 
															   decimalFormat.format(runningBalance.accumulateAndGet(amount, (u,v) -> u.add(v))); 
			return statementLine;
			};
		
		
		
		
		
		List<String> statementLines = transactions.stream()
																		  .map(fn)
																		  .collect(toList());
		
		statementLines.forEach(console::printLine);
	}
}
