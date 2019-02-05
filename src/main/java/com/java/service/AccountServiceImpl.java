package com.java.service;

import java.util.Collection;

import com.java.dao.AccountRepository;
import com.java.dto.Account;
import com.java.exception.InvalidAccountException;

public class AccountServiceImpl implements AccountService {

	 AccountRepository rep ; //setter/ constructor

	AccountServiceImpl(AccountRepository rep){
		this.rep= rep;
	}
	
	/*public void setRep(AccountRepository rep) {
		this.rep= rep;
	}
	*/
	@Override
	public void openAccount(Account account) {
		rep.openAccount(account);
	}

	@Override
	public void closeAccount(int accountNumber) {
		rep.closeAccount(accountNumber);

	}

	@Override
	public void updateAccount(Account account) throws InvalidAccountException {
		rep.updateAccount(account);

	}

	@Override
	public Account getAccountByNumber(int accountNumber) {
		return rep.getAccountByNumber(accountNumber);
	}

	@Override
	public Collection<Account> getAccounts() {
		return rep.getAccounts();
	}

	
}
