package com.java.dao;

import java.util.Collection;

import com.java.dto.Account;
import com.java.exception.InvalidAccountException;

public class AccountRepositoryImplV2 implements AccountRepository{
//JDBC impl
	@Override
	public void openAccount(Account account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeAccount(int accountNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAccount(Account account) throws InvalidAccountException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account getAccountByNumber(int accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Account> getAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

}
