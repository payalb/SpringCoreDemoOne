package com.java.dto;

import java.util.ArrayList;
import java.util.List;

import com.java.exception.InvalidAmountException;
import com.java.exception.InvalidTransaction;
import com.java.service.AccountService;

import lombok.Data;

@Data

public class Account {
	{
		count++;
		accountNumber = count;
	}
	private static int count;
	private int accountNumber;
	private List<Customer> customers = new ArrayList<>();
	private String bank = "MyBank";
	private double balance;
	private long version; //version, incremented everytime u change the value for this column
	static AccountService service ;

	public Account(List<Customer> customerList, double balance) throws CloneNotSupportedException, InvalidAmountException {
		for (Customer c : customerList) {
			customers.add((Customer) c.clone());
		}
		if(balance<0) {
			throw new InvalidAmountException("Invalid amount passed"+ balance);
		}
		this.balance = balance;
		
	}

	public boolean depositMoney(double amount, long v) throws InvalidAmountException {
		if (amount > 0) {
			synchronized (this) {
			balance += amount;
			version++;
			}
			return true;
		} else {
			throw new InvalidAmountException("Invalid amount, cannot deposit " + amount);
		}
	}

	public boolean withdrawMoney(double amount, long v) throws InvalidAmountException, InvalidTransaction {
		boolean isSuccess = false;
		synchronized (this) {
		if(v==version) {
		if (amount > 0 && balance > amount ) {
			balance -= amount;
			isSuccess = true;
			version++;
			return isSuccess;
		} else {
			isSuccess = false;
			throw new InvalidAmountException("Invalid amount, cannot withdraw " + amount);
		}}
		
		else {
			throw new InvalidTransaction("Data modified by some other user.");
		}
		}

	}

	public void transferMoney(double amount, int fromAccount, int toAccount) throws InvalidAmountException, InvalidTransaction {
		if (withdrawMoney(amount, fromAccount)) {
			try {
				depositMoney(amount, toAccount);
			} catch (InvalidAmountException e) {
				depositMoney(amount, fromAccount);// compensatory action
			}
		}
	}

	private static boolean depositMoney(double amount, int toAccount) throws InvalidAmountException {
		Account account = service.getAccountByNumber(toAccount); //500| 2 -200 | 3
		return account.depositMoney(amount, account.getVersion());//

	}

	private static boolean withdrawMoney(double amount, int fromAccount) throws InvalidAmountException, InvalidTransaction {
	//	synchronized (lock) { Pessimistic locking
			Account account = service.getAccountByNumber(fromAccount); //2 :500 A 	200	300, v1
			return account.withdrawMoney(amount, account.getVersion());//
	//	}
	
	}


}
