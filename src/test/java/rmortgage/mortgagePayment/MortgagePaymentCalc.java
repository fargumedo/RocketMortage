package rmortgage.mortgagePayment;

public class MortgagePaymentCalc {

	private double quote;
	private double amount;
	private double rate;
	private int term;
	private double initialPayment;

	public MortgagePaymentCalc() {

	}

	public MortgagePaymentCalc(double amount, double rate, int term, double initialPayment) {

		this.amount = amount;
		this.rate = rate;
		this.term = term;
		this.initialPayment = initialPayment;

	}
	
	public double finalTotal() {
		
		double m = amount*(initialPayment / 100);

		double r = (rate / 100) / 12;

		double i = 1 + r;

		double res = Math.pow(i, 360);
		
		quote = (m * (r * res)) / ((res) * term - 1);
		
		return quote;
		
	}

}
