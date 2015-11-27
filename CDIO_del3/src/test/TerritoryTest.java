package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entity.Player;
import entity.Territory;

public class TerritoryTest {
	private Territory territory1;
	private Territory territory2;
	
	private Player spiller1;
	private Player spiller2;

	@Before
	// setUp metoden bliver kaldt F�R hver test metode
	public void setUp(){
		territory1 = new Territory("Territory1, leje: 1000, eje: 6000", 1000, 6000);
	
		territory2 = new Territory("Territory2, leje: 5000, eje: 11000", 5000, 11000);

		spiller1 = new Player("Obama", 0);
		spiller1.addToBalance(10000);
		
		spiller2 = new Player("Clinton", 1);
		spiller2.addToBalance(15000);
	}
	
	
	// En spiller lander på et frit felt
	@Test
	public void testNewOwner() {
		
		// en simpel test for at sikre at startbeløbet er rigtigt
		int saldo = spiller1.getBalance();
		Assert.assertEquals(40000, saldo);
		
		//spiller 1 lander på Territory1, den koster 6.000
		territory1.landOnField(spiller1);
		
		// hvis getInformation returnere 0, så er feltet ledigt.
		if(spiller1.getInformation() == 0) {
			spiller1.buyField(territory1);
		}
		
		// udregn det forventede resultat
		int expected = 34000;
		
		//f� saldo ud af spillerens konto
		int actual = spiller1.getBalance();
		
		//test om de tal er ens
		Assert.assertEquals(expected, actual);
	}
	
	//To spillere lander på samme felter efter hinanden
	// den første betaler for at eje, den næste betaler husleje
	@Test
	public void testAlreadyOwned() {
		
		// en simpel test for at sikre at startbeløbet er rigtigt
//		int saldo = spiller1.getBalance();
//		Assert.assertEquals(40000, saldo);
		
		//spiller 1 lander p� Territory1, den koster 6.000
		territory1.landOnField(spiller1);
		
		boolean bought = false;
		if(spiller1.getInformation() == 0) {
			bought = spiller1.buyField(territory1);
		}
		if(bought){
			Assert.assertTrue(spiller1.getInformation() == 1);
		}
		
		int saldo = spiller2.getBalance();
		Assert.assertEquals(45000, saldo);
		
		territory1.landOnField(spiller2);

		saldo = 45000 - 1000;
		
		if(bought){
			Assert.assertEquals(saldo, spiller2.getBalance());
		}


		
//		// udregn det forventede resultat
//		int expected = 40000 - 6000;
//		
//		//f� saldo ud af spillerens konto
//		int actual = spiller1.getBalance();
//		
//		//test om de tal er ens
//		Assert.assertEquals(expected, actual);
//		
//		// Nu lander spiller 2 p� samme felt.. dvs han skal betale husleje p� 1.000
//		territory1.landOnField(spiller2);
//		// udregn det forventede resultat
//		expected = 15000 - 1000;
//		
//		//f� saldo ud af spillerens konto
//		actual = spiller2.getBalance();
//		//test om de tal er ens
//		Assert.assertEquals(expected, actual);
	}

	// Vi tester om huslejlen passer med det forventede tal, som vi har
	// defineret h�jere oppe i setUp metoden
	public void testGetRent(){
		int expected = 5000;
		int actual = territory2.getRent();
		
		Assert.assertTrue(expected == actual);
	}

	//På samme måde tester vi prisen for feltet
	public void testGetPrice(){
		int expected = 11000;
		int actual = territory2.getPrice();
		
		Assert.assertTrue(expected == actual);
	}
}
