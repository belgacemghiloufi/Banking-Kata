package fr.tdd.kata.bankaccount;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountShould {
	
	private static final String TODAY = "22/10/2022";
	@Mock private TransactionRepository transactionRepository;
	@Mock private StatementPrinter statementPrinter;
	private Account account;
	
	@Before
	public void initialise() {
		account = new Account(transactionRepository, statementPrinter);
	}
	
	@Test 
	public void 
	increase_balance_when_deposit_is_made() {
		BigDecimal amount = new BigDecimal(100.00);
		List<Transaction> transactions = List.of(new Transaction(TODAY, amount, Operation.DEPOSIT));
		when(transactionRepository.getTransactions()).thenReturn(transactions);
		account.deposit(amount);
		verify(transactionRepository).addDepositTransaction(amount);
		assertThat(account.getBalance(), is(amount));
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void
	handle_negative_deposit_amount() {
		BigDecimal negativeAmount = new BigDecimal(-100.00);
		account.deposit(negativeAmount);
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void
	handle_negative_withdraw_amount() {
		BigDecimal negativeAmount = new BigDecimal(-100.00);
		account.withdraw(negativeAmount);
	}
	
	@Test
	public void
	decrease_balance_when_withdrawal_is_made() {
		BigDecimal depositAmount = new BigDecimal(100.00);
		BigDecimal withdrawAmount = new BigDecimal(50.00);
		BigDecimal leftAmount = new BigDecimal(50.00);
		List<Transaction> transactions = List.of(new Transaction(TODAY, depositAmount, Operation.DEPOSIT), new Transaction(TODAY, withdrawAmount, Operation.WITHDRAWAL));
		when(transactionRepository.getTransactions()).thenReturn(transactions);
		account.deposit(depositAmount);
		account.withdraw(withdrawAmount);
		verify(transactionRepository).addWithdrawTransaction(withdrawAmount);
		assertThat(account.getBalance(), is(leftAmount));
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void
	handle_withdrawing_more_than_balance() {
		BigDecimal depositAmount = new BigDecimal(100.00);
		BigDecimal withdrawAmount = new BigDecimal(150.00);
		account.deposit(depositAmount);
		account.withdraw(withdrawAmount);
	}
	
	@Test
	public void
	print_a_statement_containing_all_transactions() {
		List<Transaction> transactions = Arrays.asList(new Transaction(TODAY,  new BigDecimal(100.00), Operation.DEPOSIT));
		given(transactionRepository.getTransactions()).willReturn(transactions);
		account.printStatement();
		verify(statementPrinter).print(transactions);
	}
}
