package com.alinatkachuk;

import java.util.Calendar;

public class DebitCard extends BankService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//user
	private double accountBalance;
	private String cardNumber;
	private Calendar expirationDate;
	private String ccv;
    private PaymentSystem paymentSystem;
		
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance (double accountBalance) {
		this.accountBalance=accountBalance;
	}
		
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber=cardNumber;
	}
	
	public Calendar getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Calendar expirationDate) {
		this.expirationDate=expirationDate;
	}
		
	public String getCcv() {
		return ccv;
	}
	public void setCcv (String ccv) {
		this.ccv=ccv;
	}
	
	public PaymentSystem getPaymentSystem() {
		return paymentSystem;
	}
	public void setPaymentSystem (PaymentSystem paymentSystem) {
		this.paymentSystem=paymentSystem;
	}
	
	public String toString() {
		return "Account balaòce: "+accountBalance+"\n"+
			   "Card number: "+cardNumber+"\n"+
			   "Expiration date: "+expirationDate+"\n"+
			   "CCV: "+ccv+"\n";
	}
}
