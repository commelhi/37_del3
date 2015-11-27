package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import entity.Field;
import entity.Player;
import entity.Territory;
import entity.Fleet;

public class FleetTest {
	private Player spiller1;
	private Player spiller2;
	
	private Fleet fleet1;
	private Fleet fleet2;
	
	@Before
	// setUp metoden bliver kaldt FøR hver test metode
	public void setUp(){
		fleet1 = new Fleet("Fleet1, pris: 16000", 16000);
		fleet2 = new Fleet("Fleet2, pris: 13000", 13000);


		spiller1 = new Player("Obama", 0);
		spiller1.addToBalance(10000);
		
		spiller2 = new Player("Clinton", 1);
		spiller2.addToBalance(15000);
	}
	// En spiller lander på et frit felt
	@Test
	public void testNewOwner() {
		int expected, actual;
		
		// en simpel test for at sikre at startbeløbet er rigtigt
		int saldo = spiller1.getBalance();
		Assert.assertEquals(40000, saldo);
		
		
		//spiller 1 lander på Fleet, den koster 16.000
		fleet1.landOnField(spiller1);
		
		boolean bought = false;
		
		//er feltet ledigt? hvis ja, køb det
		if(spiller1.getInformation() == 0) {
			bought = spiller1.buyField(fleet1);
		}
		
		// fik han købt feltet? hvis ja, test om prisen er fratrukket saldoen
		if(bought){
			Assert.assertEquals(1, spiller1.getInformation());
			
			expected = 40000 - 16000;
			actual = spiller1.getBalance();
			Assert.assertEquals(expected, actual);
			
		}else{
			//ellers burde beløbet ikke være fratrukket
			Assert.assertEquals(40000, spiller1.getBalance());
			
		}
	}
	
	@Test
	public void testAlreadyOwned() {
		int expected, actual;
		
		// en simpel test for at sikre at startbel�bet er rigtigt
		int saldo = spiller1.getBalance();
		Assert.assertEquals(40000, saldo);

		// en simpel test for at sikre at startbel�bet er rigtigt
		saldo = spiller2.getBalance();
		Assert.assertEquals(45000, saldo);
		
		//spiller 1 lander p� Fleet, den koster 16.000
		fleet1.landOnField(spiller1);
		
		boolean bought = false;
		
		//er feltet ledigt? hvis ja, køb det
		if(spiller1.getInformation() == 0) {
			bought = spiller1.buyField(fleet1);
		}
		
		// fik han købt feltet? hvis ja, test om prisen er fratrukket saldoen
		if(bought){
			expected = 40000 - 16000;
			actual = spiller1.getBalance();
			Assert.assertEquals(expected, actual);
		}else{
			//ellers burde beløbet ikke være fratrukket
			Assert.assertEquals(40000, spiller1.getBalance());
		}
		
		if(bought){
			
			fleet1.landOnField(spiller2);
			boolean rented = false;
			
			// hvis feltet ejes allerede, og vi kan leje det
			if(spiller1.getInformation() == -1){
				rented = spiller2.rentField(fleet1);
			}
			//hvis det lykkedes at leje feltet, test om lejepris er fratrukket saldo
			if(rented){
				expected = 45000 - 500;
				actual = spiller2.getBalance();
				Assert.assertEquals(expected, actual);
			}
		}
	}

	@Test
	public void testMyOwnFleet() {
		int expected, actual;
		
		// en simpel test for at sikre at startbeløbet er rigtigt
		int saldo = spiller1.getBalance();
		Assert.assertEquals(40000, saldo);
		
		// land på et frit felt
		fleet2.landOnField(spiller1);
		boolean bought = false;
		
		//er feltet ledigt? hvis ja, køb det
		if(spiller1.getInformation() == 0) {
			bought = spiller1.buyField(fleet2);
		}
		
		// fik han købt feltet? hvis ja, test om prisen er fratrukket saldoen
		if(bought){
			System.out.println(spiller1.getInformation());

			
			expected = 40000 - 13000;
			actual = spiller1.getBalance();
			Assert.assertEquals(expected, actual);
		}else{
			//ellers burde beløbet ikke være fratrukket
			Assert.assertEquals(40000, spiller1.getBalance());
		}
		System.out.println(spiller1.getInformation());

		// hvis man lander på eget felt igen, skal info være 1
		if(bought){
			Assert.assertEquals(1, spiller1.getInformation());
		}

		// hvis man lander på eget felt igen, skal der ikke trækkes noget
		if(bought){
			fleet2.landOnField(spiller1);
			
			//test om der er trukket beløb fra konto (FALSE)
			expected = 40000 - 13000;
			actual = spiller1.getBalance();
			Assert.assertEquals(expected, actual);
		}
	}
}
