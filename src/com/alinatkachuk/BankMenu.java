package com.alinatkachuk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;

public class BankMenu {
	
	BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));

	private Bank bank;
	
	public BankMenu (Bank bank) {
	this.bank = new Bank();
	}
	
	public void showStartMenu() throws IOException {
		System.out.print ("1. Authorization"+"\n"+
			              "2. Register"+"\n"+
		                  "0. Exit"+"\n"+
		                  "Choose one of the proposed items: ");
		String answerStartMenu = reader.readLine();
		if (Integer.parseInt(answerStartMenu)==0) {
			System.exit(0);
		} else if (Integer.parseInt(answerStartMenu)==1) {
			this.showAuthorization();
		} else if (Integer.parseInt(answerStartMenu)==2) {
			this.showRegister();
		} else {
			System.out.print ("Error: choose from the proposed items");
		}
	}
	
	public void showAuthorization() throws IOException {
		System.out.print ("Enter your email: ");
		String emailEntered = reader.readLine();
		System.out.print ("Enter your password: ");
		String passwordEntered = reader.readLine();
		boolean isLoggedIn = bank.doAuthorization(emailEntered, passwordEntered);
		if (isLoggedIn==true) {
			this.showBankMenu();
		} else {
			System.out.println("Email or password is incorrect");
			this.showStartMenu();
		}
	}
	
	public void showRegister() throws IOException {
		User userCreation = new User();
		String gender;
		System.out.print ("Enter your first name: ");
		userCreation.setFirstName(reader.readLine());
		System.out.print ("Enter your last name: ");
		userCreation.setLastName(reader.readLine());
		System.out.print ("Enter your birth date: ");
		Calendar birthDate = Calendar.getInstance();
		SimpleDateFormat formattedDate = new SimpleDateFormat("dd.MM.yyyy");
		try {
			birthDate.setTime(formattedDate.parse(reader.readLine()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		userCreation.setBirthDate(birthDate);
		do {
		    do {
		    	System.out.print("What's your gender: "+"\n"+
		                         "1. Male 2. Female" + "\n" + 
		    			         "Please choose a number: ");
		    	gender = reader.readLine();
		    } while (Integer.parseInt(gender)<=0);
		} while (Integer.parseInt(gender)>3); 
		if (Integer.parseInt(gender)==1) {
			userCreation.setGender(Gender.MALE);
		} else {
			userCreation.setGender(Gender.FEMALE);
		}	
		System.out.print ("Enter your email: ");
		userCreation.setEmail(reader.readLine());
		System.out.print ("Enter your password: ");
		userCreation.setPassword(reader.readLine());

		bank.doRegister(userCreation);
	}
	
	public void showBankMenu() throws IOException {
		String answerForBankMenu;
		do {
			do {
				System.out.print ("Banking services catalog:"+"\n"+
			            "1. My account"+"\n"+
                        "2. Debit card"+"\n"+
                        "3. Balance on the card"+"\n"+
                        "4. Credit"+"\n"+
                        "5. My loan data"+"\n"+
                        "Please choose a number: ");
				answerForBankMenu = reader.readLine();
		    } while (Integer.parseInt(answerForBankMenu)<=0);
		} while (Integer.parseInt(answerForBankMenu)>6);
		if (Integer.parseInt(answerForBankMenu)==1) {
			bank.showAccount();
		} else if (Integer.parseInt(answerForBankMenu)==2) {
			this.showDebitCard();
		} else if (Integer.parseInt(answerForBankMenu)==3) {
			bank.showBalance();
		} else if (Integer.parseInt(answerForBankMenu)==4) {
			this.showLoan();;
		} else {
			bank.showLoanData();
		}
	}
	
	public void showDebitCard() throws IOException {
		DebitCard debitCardCreation = new DebitCard();
		String paymentSystem;
		String answerForPaymentSystem;
		String expirationDate;
		String answerForBalance;
		//choice of payment system
		do {
		    do {
		    	System.out.print("Which payment system do you choose: "+"\n"+
		                         "1. Visa 2. MasterCard 3. BELCARD" + "\n" + 
		    			         "Please choose a number: ");
		    	answerForPaymentSystem = reader.readLine();
		    } while (Integer.parseInt(answerForPaymentSystem)<=0);
		} while (Integer.parseInt(answerForPaymentSystem)>4); 
		if (Integer.parseInt(answerForPaymentSystem)==1) {
			paymentSystem = "      VISA";
			debitCardCreation.setPaymentSystem(PaymentSystem.VISA);
			long cardNumberVisa = (long) ((4*(Math.pow(10, 15)))+(Math.random()*(Math.pow(10, 15))));        //VISA card number 4### #### #### ####
			debitCardCreation.setCardNumber(new DecimalFormat("0000,0000,0000,0000").format(cardNumberVisa));
		} else if (Integer.parseInt(answerForPaymentSystem)==2) {
			paymentSystem = "MASTERCARD";
			debitCardCreation.setPaymentSystem(PaymentSystem.MASTERCARD);
			long cardNumberMasterCard = (long) ((5*(Math.pow(10, 15)))+(Math.random()*(Math.pow(10, 15))));  //MASTERCARD card number 5### #### #### ####
			debitCardCreation.setCardNumber(new DecimalFormat("0000,0000,0000,0000").format(cardNumberMasterCard));
		} else {
			paymentSystem = "   BELCARD";
			debitCardCreation.setPaymentSystem(PaymentSystem.BELCARD);
			long cardNumberBelCard = (long) ((6*(Math.pow(10, 15)))+(Math.random()*(Math.pow(10, 15))));     //BELCARD card number 6### #### #### ####
			debitCardCreation.setCardNumber(new DecimalFormat("0000,0000,0000,0000").format(cardNumberBelCard));
		}
		//CCV
		int ccv = (int) (Math.random()*100);
		debitCardCreation.setCcv(new DecimalFormat("000").format(ccv));
		//expiration date
		do {
		    do {
		    	System.out.print("What card expiry date do you choose: "+"\n"+
		                         "1. 1 year 2. 3 years 3. 5 years" + "\n" + 
		    			         "Please choose a number: ");
		    	expirationDate = reader.readLine();
		    } while (Integer.parseInt(expirationDate)<=0);
		} while (Integer.parseInt(expirationDate)>4); 
		Calendar calendar = Calendar.getInstance();
		calendar.getTime();
		if (Integer.parseInt(expirationDate)==1) {
			calendar.add(Calendar.YEAR, 1);
		} else if (Integer.parseInt(expirationDate)==2) {
			calendar.add(Calendar.YEAR, 3);
		} else {
			calendar.add(Calendar.YEAR, 5);
		}
		calendar.set(Calendar.HOUR, 11);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		YearMonth yearMonthObject = YearMonth.of(calendar.get(Calendar.YEAR), (calendar.get(Calendar.MONTH)+1));
		calendar.set(Calendar.DAY_OF_MONTH, yearMonthObject.lengthOfMonth());
		debitCardCreation.setExpirationDate(calendar);
		SimpleDateFormat formattedDate = new SimpleDateFormat("MM/yy");
		String formattedExpirationDate = formattedDate.format(calendar.getTime());
		//card issue
		System.out.println("YOUR CARD"+"\n"+
				           "Front side (CCV FROM BACK SIDE: "+debitCardCreation.getCcv()+")"+"\n"+
				           " _____________________________"+"\n"+
				           "|*DELTA-BANK                  |"+"\n"+
				           "|                             |"+"\n"+
				           "|                             |"+"\n"+
				           "|     "+debitCardCreation.getCardNumber()+"     |"+"\n"+
				           "|                             |"+"\n"+
				           "|          "+formattedExpirationDate+"              |"+"\n"+
				           "|                   "+paymentSystem+"|"+"\n"+
				           "|_____________________________|"+"\n");
		//account replenishment
		do {
		    do {
		    	System.out.print("Do you want to recharge the balance of your card: "+"\n"+
		                         "1. Yes 2. No" + "\n" + 
		    			         "Please choose a number: ");
		    	answerForBalance = reader.readLine();
		    } while (Integer.parseInt(answerForBalance)<=0);
		} while (Integer.parseInt(answerForBalance)>3);
		if (Integer.parseInt(answerForBalance)==1) {
			System.out.print("How much money do you want to recharge the balance of card: ");
			String amountOfMoney = reader.readLine();
			debitCardCreation.setAccountBalance(Integer.parseInt(amountOfMoney));
			System.out.print("Balance replenished! Thank you!");
		} else {
			debitCardCreation.setAccountBalance(0);
		}
		bank.issueDebitCard(debitCardCreation);
	}
	
    public void showLoan() throws IOException {
    	Loan loanIssue = new Loan();
    	String answerForInterest;
    	String answerForSum;
    	String answerForLoanTerm;
    	do {
		    do {
		    	System.out.print("What type of loan are you interested in?"+"\n"+
		                         "1. Consumer loan (to 30.000 BYN, interest - 28% per annum)"+"\n"+
    	                         "2. Real estate loan (from 30.000 to 150.000 BYN, interest - 16% per annum)"+"\n"+
    	                         "3. Preferential loan (from 30.000 to 200.000 BYN, interest - 8% per annum)" + "\n" + 
	    			             "Please choose a number: ");
		    	answerForInterest = reader.readLine();
		    } while (Integer.parseInt(answerForInterest)<=0);
		} while (Integer.parseInt(answerForInterest)>4);
    	if (Integer.parseInt(answerForInterest)==1) {
    		loanIssue.setInterestRate(0.28);
    		do {
    			do {
    				System.out.print("How much do you want to borrow (to 30.000 BYN)?" +"\n"+
    			                     "Enter sum, please: ");
    				answerForSum = reader.readLine();
    			}  while (Integer.parseInt(answerForSum)<=0);
    		} while (Integer.parseInt(answerForSum)>(30000+1));
    		loanIssue.setSum(Integer.parseInt(answerForSum));
		} else if (Integer.parseInt(answerForInterest)==2) {
			loanIssue.setInterestRate(0.16);
			do {
    			do {
    				System.out.print("How much do you want to borrow (from 30.000 to 150.000 BYN)?"+"\n"+
    			                     "Enter sum, please: ");
    				answerForSum = reader.readLine();
    			}  while (Integer.parseInt(answerForSum)<=(30000-1));
    		} while (Integer.parseInt(answerForSum)>(150000+1));
			loanIssue.setSum(Integer.parseInt(answerForSum));
		} else {
			loanIssue.setInterestRate(0.08);
			do {
    			do {
    				System.out.print("How much do you want to borrow (from 30.000 to 200.000 BYN)?"+"\n"+
    			                     "Enter sum, please: ");
    				answerForSum = reader.readLine();
    			}  while (Integer.parseInt(answerForSum)<=(30000-1));
    		} while (Integer.parseInt(answerForSum)>(200000+1));
			loanIssue.setSum(Integer.parseInt(answerForSum));
		}
    	do {
		    do {
		    	System.out.print("Credit term:"+"\n"+
		                         "1. 1 year 2. 3 year 3. 5 year 4. 10 years"+ "\n" + 
	    			             "Please choose a number: ");
		    	answerForLoanTerm = reader.readLine();
		    } while (Integer.parseInt(answerForLoanTerm)<=0);
		} while (Integer.parseInt(answerForLoanTerm)>5);
    	if (Integer.parseInt(answerForLoanTerm)==1) {
    		loanIssue.setLoanTerm(1);
		} else if (Integer.parseInt(answerForLoanTerm)==2) {
			loanIssue.setLoanTerm(3);
		} else if (Integer.parseInt(answerForLoanTerm)==3){
			loanIssue.setLoanTerm(5);
		} else {
			loanIssue.setLoanTerm(10);
		}
    	loanIssue.setMonthlyPayment(loanIssue.getSum()*(((loanIssue.getInterestRate()/12)*Math.pow((1+(loanIssue.getInterestRate()/12)), (loanIssue.getLoanTerm()*12)))/((Math.pow((1+(loanIssue.getInterestRate()/12)), (loanIssue.getLoanTerm()*12)))-1)));
    	Calendar calendar = Calendar.getInstance();
		calendar.getTime();
		loanIssue.setRegistrationDate(calendar);
		bank.issueLoan(loanIssue);
	}
}
