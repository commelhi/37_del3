package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entity.Player;
import entity.LaborCamp;

public class LaborCampTest {
	private LaborCamp laborcamp1;
	private LaborCamp laborcamp2;
	
	private Player spiller1;
	private Player spiller2;

	@Before
	// setUp metoden bliver kaldt FøR hver test metode
	public void setUp(){
		laborcamp1 = new LaborCamp("laborcamp1, leje: 1000, pris: 6000", 1000, 6000);
	
		laborcamp2 = new LaborCamp("laborcamp2, leje: 5000, pris: 11000", 5000, 11000);

		spiller1 = new Player("Obama", 1);
		spiller1.addToBalance(10000);
		
		spiller2 = new Player("Clinton", 2);
		spiller2.addToBalance(15000);
	}
	// En spiller lander p� et frit felt
	@Test
	public void testNewOwner() {
		
		// en simpel test for at sikre at startbeløbet er rigtigt
		int saldo = spiller1.getBalance();
		Assert.assertEquals(40000, saldo);
		
		//spiller 1 lander på Territory1, den koster 6.000
		laborcamp1.landOnField(spiller1);
		
		boolean bought = false;
		
		// hvis getInformation returnere 0, så er feltet ledigt.
		if(spiller1.getInformation() == 0) {
			bought = spiller1.buyField(laborcamp1);
		}
		
		//hvis han fik lov til at købe feltet, så test saldo - pris
		if(bought){
			// udregn det forventede resultat
			int expected = 40000 - 6000;
			
			//få saldo ud af spillerens konto
			int actual = spiller1.getBalance();
			//test om de tal er ens
			Assert.assertEquals(expected, actual);
		}else{
			// hvis ikke han kunne købe feltet, så skal saldoen være startbeløbet
			saldo = spiller1.getBalance();
			Assert.assertEquals(40000, saldo);
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
		laborcamp1.landOnField(spiller1);
		
		boolean bought = false;
		
		//er feltet ledigt? hvis ja, køb det
		if(spiller1.getInformation() == 0) {
			bought = spiller1.buyField(laborcamp1);
		}
		
		// fik han købt feltet? hvis ja, test om prisen er fratrukket saldoen
		if(bought){
			expected = 40000 - 6000;
			actual = spiller1.getBalance();
			Assert.assertEquals(expected, actual);
		}else{
			//ellers burde beløbet ikke være fratrukket
			Assert.assertEquals(40000, spiller1.getBalance());
		}
		
		// test om spiller 2 har det rigtige startbeløb
		saldo = spiller2.getBalance();
		Assert.assertEquals(45000, saldo);
		
		// hvis spiller 1 havde købt feltet, så må spiller 2 betale husleje
		if(bought){
			
			//lad spiller 2 lande på feltet
			laborcamp1.landOnField(spiller2);
			boolean rented = false;
			
			
			// hvis feltet ejes allerede, og vi kan leje det
			if(spiller2.getInformation() == -1){
				rented = spiller2.rentField(laborcamp1);
			}
			//hvis det lykkedes at leje feltet, test om lejepris er fratrukket saldo
			if(rented){
				expected = 45000 - 1000;
				actual = spiller2.getBalance();
				Assert.assertEquals(expected, actual);
			}
		}
	}

}