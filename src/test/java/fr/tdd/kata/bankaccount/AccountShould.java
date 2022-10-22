package fr.tdd.kata.bankaccount;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class AccountShould {
	
	private Account account;
	
	@Before
	public void initialise() {
		account = new Account();
	}
	
	
	@Test public void 
	increase_balance_when_deposit_is_made() {
		BigDecimal amount = new BigDecimal(100.00);
		account.deposit(amount);
		assertThat(account.getBalance(), is(amount));
	}
	
}
