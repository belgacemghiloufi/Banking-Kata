package fr.tdd.kata.bankaccount;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;

import org.junit.Test;

public class DateHelperShould {

	private static final String TODAY = "22/10/2022";

	@Test
	public void 
	return_todays_date_in_dd_MM_YYY_format() {
		DateHelper dateHelper = new TestableDateHelper();
		String today = dateHelper.getTodayAsString();
		assertThat(today, is(TODAY));
	}

	private class TestableDateHelper extends DateHelper {
		@Override
		public LocalDate today() {
			return LocalDate.of(2022, 10, 22);
		}
	}

}
