package fr.tdd.kata.bankaccount;

import static fr.tdd.kata.bankaccount.StatementPrinter.STATEMENT_HEADER;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StatementPrinterShould {
	
	private static final List<Transaction> NO_TRANSACIONS = Collections.emptyList();
	private StatementPrinter statementPrinter;
	@Mock private Console console;
	
	@Before
	public void initialise() {
		statementPrinter = new StatementPrinter(console);
	}
	
	@Test
	public void
	print_header_even_when_there_are_no_transactions() {
		statementPrinter.print(NO_TRANSACIONS);
		verify(console).printLine(STATEMENT_HEADER);
	}
	
	@Test
	public void 
	print_all_transactions() {
		
		Transaction deposit_1 = new Transaction("22/10/2022", new BigDecimal(1000.00), Operation.DEPOSIT);
		Transaction withdrawal  = new Transaction("22/10/2022", new BigDecimal(100.00), Operation.WITHDRAWAL);
		Transaction deposit_2 = new Transaction("22/10/2022", new BigDecimal(500.00), Operation.DEPOSIT);
		List<Transaction> transactionList = Arrays.asList(deposit_1, withdrawal, deposit_2);
		
		statementPrinter.print(transactionList);
		
		verify(console).printLine("OPERATION | DATE | AMOUNT | BALANCE");
		verify(console).printLine("DEPOSIT | 22/10/2022 | 1000.00 | 1000.00");
		verify(console).printLine("WITHDRAWAL | 22/10/2022 | -100.00 | 900.00");
		verify(console).printLine("DEPOSIT | 22/10/2022 | 500.00 | 1400.00");
	}

}
