package fr.tdd.kata.bankaccount;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TransactionRepositoryShould {
	
	private static final String TODAY = "22/10/2022";
	
	@Mock private DateHelper dateHelper;
	private TransactionRepository transactionRepository;
	
	@Before
	public void initialise() {
		given(dateHelper.getTodayAsString()).willReturn(TODAY);
		transactionRepository = new TransactionRepository(dateHelper);
	}
	
	@Test
	public void 
	create_a_deposit_transaction() {
		BigDecimal depositAmount = new BigDecimal(100.00);
		transactionRepository.addDepositTransaction(depositAmount);
		List<Transaction> transactions = transactionRepository.getTransactions();
		assertThat(transactions.size(), is(1));
		Transaction transaction = transactions.get(0);
		assertThat(transaction, is(transaction(TODAY, depositAmount, Operation.DEPOSIT)));
	}
	
	@Test
	public void 
	create_a_withdraw_transaction() {
		BigDecimal withdrawAmount = new BigDecimal(100.00);
		transactionRepository.addWithdrawTransaction(withdrawAmount);
		List<Transaction> transactions = transactionRepository.getTransactions();
		assertThat(transactions.size(), is(1));
		assertThat(transactions.get(0), is(transaction(TODAY, withdrawAmount, Operation.WITHDRAWAL)));
	}

	private Transaction transaction(String date, BigDecimal depositAmount, Operation operation) {
		return new Transaction(date, depositAmount, operation);
	}
}
