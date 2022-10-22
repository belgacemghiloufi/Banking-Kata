package fr.tdd.kata.bankaccount;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateHelper {

	private static final DateTimeFormatter DD_MM_YYYY = DateTimeFormatter.ofPattern("dd/MM/YYYY");

	public String getTodayAsString() {
		return today().format(DD_MM_YYYY);
	}

	protected LocalDate today() {
		return LocalDate.now();
	}

}
