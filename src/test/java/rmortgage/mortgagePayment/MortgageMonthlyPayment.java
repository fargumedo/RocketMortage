package rmortgage.mortgagePayment;

import java.text.DecimalFormat;

public class MortgageMonthlyPayment {

	private double quote;
	private double amount;
	private double rate;
	private int term;
	private double initialPayment;

	public MortgageMonthlyPayment() {

	}

	public MortgageMonthlyPayment(double amount, double rate, int term, double initialPayment) {

		this.amount = amount;
		this.rate = rate;
		this.term = term;
		this.initialPayment = initialPayment;

	}
	
	public double finalTotalPerc() {
		
		double percPi = initialPayment/100;
		double pi = amount*percPi;
		double m1 = amount-pi;
		double r1 = rate / 100;
		double r2 = r1/12;
		double i = 1 + r2;
		double res1 = Math.pow(i, term);
		double r3 = r2*res1;
		double res = res1-1;
		double r = r3/res;
		quote = m1*r;
		
		DecimalFormat format = new DecimalFormat("#.##");
		String formatQuote = format.format(quote);
		double quote2 = Double.parseDouble(formatQuote);
		
		return quote2;
		
	}
	
	public double finalTotalCash() {
		
		double m = amount - initialPayment;
		double r = (rate / 100) / 12;
		double i = 1 + r;
		double res = Math.pow(i, 360);		
		quote = (m * (r * res)) / ((res) * term - 1);
		
		return quote;
		
	}

}
