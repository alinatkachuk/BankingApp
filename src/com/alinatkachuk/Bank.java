package com.alinatkachuk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Bank {

	private ArrayList<User> users = deserializeUsers();
	private User user;
	BankMenu bankMenu;

	public ArrayList<User> getUsers() {
		return users;
	}
	public void setUsers(ArrayList<User> users) {
		this.users=users;
	}
	public void start() throws IOException {
		bankMenu = new BankMenu(null);
		bankMenu.showStartMenu();
	}
	
	public boolean doAuthorization (String email, String password) {
		boolean foundUser = false;
		for(User userDoAuthorization : users){ 
		    if((userDoAuthorization.getEmail().equals(email)) && (userDoAuthorization.getPassword().equals(password))){
		        foundUser = true;
		        this.user = userDoAuthorization;
		        break;
		    }
		}
		return foundUser;
	}
	
	public void doRegister(User user) {
		users.add(user);
		serializeUsers(getUsers());
	}
	
	public void serializeUsers(ArrayList<User> users) {
		File file = new File ("Users.dat");
		try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(users);
            objectOutputStream.close();
            fileOutputStream.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
	}
	
	public ArrayList<User> deserializeUsers() {
		ArrayList<User> localUsers = new ArrayList<>();
		try
        {
            FileInputStream fileInputStream = new FileInputStream("Users.dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            localUsers = (ArrayList<User>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        } 
        catch (ClassNotFoundException c) 
        {
            c.printStackTrace();
        }
        for (User user : localUsers) {
            System.out.println(user);
        }

        return localUsers;
	}
	
	public void showAccount () throws IOException {
		SimpleDateFormat formattedDate = new SimpleDateFormat("dd.MM.yyyy");
		String birthDate = formattedDate.format(this.user.getBirthDate().getTime());
		System.out.println ("First name: "+ this.user.getFirstName() + "\n" + 
		                    "Last name: "+ this.user.getLastName() + "\n" + 
				            "Birth date: "+ birthDate + "\n" + 
				            "Gender: "+ this.user.getGender() + "\n"+
				            "Email: "+ this.user.getEmail());
		bankMenu.showBankMenu();
	}
	
    public void showBalance () throws IOException {
    	System.out.println ("Account balance: "+this.user.getBankService());
    	bankMenu.showBankMenu();
	}
    
    public void showLoanData () throws IOException {
    	Loan loan = new Loan(); //error
    	SimpleDateFormat formattedDate = new SimpleDateFormat("dd.MM.yyyy");
		String registrationDate = formattedDate.format(loan.getRegistrationDate().getTime());
    	System.out.println ("Loan registration date: "+ registrationDate + "\n" + 
                            "Loan sum: "+ loan.getSum() + "\n" + 
	                        "Interest rate: "+ loan.getInterestRate() + "\n" + 
	                        "Loan term: "+ loan.getLoanTerm() + "\n"+
	                        "Monthly payment: "+ loan.getMonthlyPayment());
    	bankMenu.showBankMenu();
	}
    
    public void issueDebitCard(DebitCard debitCard) {
		users.add(debitCard);
		serializeUsers(getUsers());
	}
	
    public void issueLoan(Loan loan) {
    	users.add(loan);
		serializeUsers(getUsers());
	}

}

			
