package de.dhbw.se.refactoring;
import java.util.*;

class Customer {
	private String name;
	private Vector<Rental> rentals = new Vector<Rental>();

	public Customer(String name) {
		this.name = name;
	};

	public void addRental(Rental rental) {
		rentals.addElement(rental);
	};

	public String getName() {
		return name;
	};

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Enumeration<Rental> enum_rentals = rentals.elements();
		String result = "Rental Record for " + this.getName() + "\n";
		result += "\t" + "Title" + "\t" + "\t" + "Days" + "\t" + "Amount" + "\n";

		while (enum_rentals.hasMoreElements()) {
			Rental rental = (Rental) enum_rentals.nextElement();
			double amountForRental = amountFor(rental);
			frequentRenterPoints = addFrequentRenterPoints(frequentRenterPoints);
			if (isTwoDayNewReleaseRental(rental))
				frequentRenterPoints = addFrequentRenterPoints(frequentRenterPoints);
			result += showFiguresForRental(result, rental, amountForRental);
			totalAmount += amountForRental;
		}
		result += addFooterLines(totalAmount, frequentRenterPoints, result);
		return result;
	}

	private String addFooterLines(double totalAmount, int frequentRenterPoints, String result) {
		return "Amount owed is " + String.valueOf(totalAmount) + "\n" + "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
	}

	private String showFiguresForRental(String result, Rental rental, double amountForRental) {
		return "\t" + rental.getMovie().getTitle() + "\t" + "\t" + rental.getDaysRented() + "\t"
				+ String.valueOf(amountForRental) + "\n";
	}

	private boolean isTwoDayNewReleaseRental(Rental rental) {
		return (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && rental.getDaysRented() > 1;
	}

	private int addFrequentRenterPoints(int frequentRenterPoints) {
		frequentRenterPoints++;
		return frequentRenterPoints;
	}

	public double amountFor(Rental rental) {
		if (rental.getMovie().getPriceCode() == Movie.REGULAR) {
			return amountForRegular(rental);
		}
		if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) {
			return amountForNewRelease(rental);
		}
		if (rental.getMovie().getPriceCode() == Movie.CHILDRENS) {
			return amountForChildren(rental);
		}
		return 0;
	}

	private double amountForChildren(Rental rental) {
		double amount = 1.5;
		if (rental.getDaysRented() > 3)
			amount += (rental.getDaysRented() - 3) * 1.5;
		return amount;
	}

	private double amountForNewRelease(Rental rental) {
		return rental.getDaysRented() * 3;
	}

	private double amountForRegular(Rental rental) {
		double amount = 2;
		if (rental.getDaysRented() > 2)
			amount += (rental.getDaysRented() - 2) * 1.5;
		return amount;
	}

}
