package de.dhbw.se.refactoring;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class CustomerTest {

	@Test
	public void amountFor_RegularMovie_1Day_is2() {
		Customer customer = new Customer("test");
		Rental rental = new Rental(new Movie("testMovie", Movie.REGULAR), 1);
		assertThat(customer.amountFor(rental), is(2.0));
	}
	
	@Test
	public void amountFor_NewRelease_1Day_is3() {
		Customer customer = new Customer("test");
		Rental rental = new Rental(new Movie("testMovie", Movie.NEW_RELEASE), 1);
		assertThat(customer.amountFor(rental), is(3.0));
	}
	
	@Test
	public void amountFor_ChildrenMovie_1Day_is1_5() {
		Customer customer = new Customer("test");
		Rental rental = new Rental(new Movie("testMovie", Movie.CHILDRENS), 1);
		assertThat(customer.amountFor(rental), is(1.5));
	}

}
