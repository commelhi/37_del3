package testcases;

import entity.Account;

public class balancetest {

	static Account acc = new Account();

	public static void main(String args[]){

		if(test1()){
			System.out.println("ok:" + test1());
		};

	}


	private static boolean test1(){
		int b = -8;
		b = acc.getBalance();

		return acc.addToBalance(4);


	}


}
