package testcases;

import entity.Account;

public class balancetest {

	static Account acc = new Account();

	public static void main(String args[]){

		
			System.out.println("test af addToBalance:" + testMetodenFraAccount());
		

	}


	private static boolean testMetodenFraAccount(){

		return acc.addToBalance(2);


	}


}
