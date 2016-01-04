package test;

import static org.junit.Assert.*;
import org.junit.After;
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
	public void setUp(){
		fleet1 = new Fleet("Fleet1, pris: 16000", 16000);
		fleet2 = new Fleet("Fleet2, pris: 13000", 13000);


		spiller1 = new Player("Obama", 0);
		spiller1.addToBalance(10000);
		
		spiller2 = new Player("Clinton", 1);
		spiller2.addToBalance(15000);
	}
	@After
	
	public void tearDownd(){
		fleet1=null;
		fleet2=null;
		
		spiller1=null;
		spiller2=null;

	}
	
	@Test
	public void testNewOwner() {
		int expected, actual;
		
		int saldo = spiller1.getBalance();
		Assert.assertEquals(40000, saldo);
		
		fleet1.landOnField(spiller1);
		
		boolean bought = false;
		
		if(spiller1.getInformation() == 0) {
			bought = spiller1.buyField(fleet1);
		}
		
		if(bought){
			Assert.assertEquals(1, spiller1.getInformation());
			
			expected = 40000 - 16000;
			actual = spiller1.getBalance();
			Assert.assertEquals(expected, actual);
			
		}else{
		
			Assert.assertEquals(40000, spiller1.getBalance());
			
		}
		
		}
	
	@Test
	public void testAlreadyOwned() {
		int expected, actual;
		
		int saldo = spiller1.getBalance();
		Assert.assertEquals(40000, saldo);

		saldo = spiller2.getBalance();
		Assert.assertEquals(45000, saldo);
		
		fleet1.landOnField(spiller1);
		
		boolean bought = false;
		
		if(spiller1.getInformation() == 0) {
			bought = spiller1.buyField(fleet1);
		}
		
		if(bought){
			expected = 40000 - 16000;
			actual = spiller1.getBalance();
			Assert.assertEquals(expected, actual);
		}else{
		
			Assert.assertEquals(40000, spiller1.getBalance());
		}
		
		if(bought){
			fleet1.landOnField(spiller2);
			
				expected = 45000-500;
				actual = spiller2.getBalance();
				Assert.assertEquals(expected, actual);
			}
		
			}
					
	@Test
	public void testMyOwnFleet() {
		int expected, actual;
		
		int saldo = spiller1.getBalance();
		Assert.assertEquals(40000, saldo);
		
		fleet2.landOnField(spiller1);
		boolean bought = false;
		
		if(spiller1.getInformation() == 0) {
			bought = spiller1.buyField(fleet2);
		}
		
		if(bought){
		//	System.out.println(spiller1.getInformation());

			
			expected = 40000 - 13000;
			actual = spiller1.getBalance();
			Assert.assertEquals(expected, actual);
		}else{
			
			Assert.assertEquals(40000, spiller1.getBalance());
		}
		
		// System.out.println(spiller1.getInformation());

		if(bought){
			Assert.assertEquals(1, spiller1.getInformation());
		}

		if(bought){
			fleet2.landOnField(spiller1);
			
			expected = 40000 - 13000;
			actual = spiller1.getBalance();
			Assert.assertEquals(expected, actual);
		}
	}
}
