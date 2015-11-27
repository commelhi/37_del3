package testcases;

import entity.Account;

public class balancetest {

	private static Account acc = new Account();
	
	public balancetest(String args[]){
		
		
		acc.addToBalance(7);
		
		
		acc.getBalance();
			
	}
	
	
}
