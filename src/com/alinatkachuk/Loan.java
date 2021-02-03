package com.alinatkachuk;

import java.util.Calendar;

public class Loan extends BankService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Calendar registrationDate;
	private double sum;
	private double interestRate;
	//user
	private int loanTerm;
	private double monthlyPayment;
	
	
	public Calendar getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Calendar registrationDate) {
		this.registrationDate=registrationDate;
	}
		
	public double getSum() {
		return sum;
	}
	public void setSum (double sum) {
		this.sum=sum;
	}
		
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate (double interestRate) {
		this.interestRate=interestRate;
	}
			
	public int getLoanTerm() {
		return loanTerm;
	}
	public void setLoanTerm (int loanTerm) {
		this.loanTerm=loanTerm;
	}
	
	public double getMonthlyPayment() {
		return monthlyPayment;
	}
	public void setMonthlyPayment (double monthlyPayment) {
		this.monthlyPayment=monthlyPayment;
	}
	
	public String toString() {
		return "Registration date: "+registrationDate+"\n"+
			   "Credit amount: "+sum+"\n"+
			   "Interest rate: "+interestRate+"\n"+
			   "Loan term: "+loanTerm+"\n"+
			   "Monthly paymant: "+monthlyPayment+"\n";
	}
}
