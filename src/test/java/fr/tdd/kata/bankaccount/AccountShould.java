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
	
	
	@Test 
	public void 
	increase_balance_when_deposit_is_made() {
		BigDecimal amount = new BigDecimal(100.00);
		account.deposit(amount);
		assertThat(account.getBalance(), is(amount));
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void
	handle_negative_deposit_amount() {
		BigDecimal negativeAmount = new BigDecimal(-100.00);
		account.deposit(negativeAmount);
	}
	
	@Test
	public void
	decrease_balance_when_withdrawal_is_made() {
		BigDecimal depositAmount = new BigDecimal(100.00);
		BigDecimal withdrawAmount = new BigDecimal(50.00);
		BigDecimal leftAmount = new BigDecimal(50.00);
		account.deposit(depositAmount);
		account.withdraw(withdrawAmount);
		assertThat(account.getBalance(), is(leftAmount));
	}
	
}
